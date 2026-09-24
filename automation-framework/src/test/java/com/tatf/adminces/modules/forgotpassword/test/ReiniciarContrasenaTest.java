package com.tatf.adminces.modules.forgotpassword.test;

import com.tatf.adminces.modules.base.BaseTest;
import com.tatf.adminces.modules.forgotpassword.task.ForgotPasswordTask;
import com.tatf.adminces.modules.home.task.HomeTask;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.register.task.RegisterTask;
import com.tatf.adminces.modules.sitegate.task.SiteGateTask;
import com.tatf.adminces.support.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Escenario: Reiniciar contraseña.
 * <p>
 * Para no depender de ni alterar cuentas ya existentes, el escenario primero crea
 * una cuenta de administrador propia y luego le reinicia la contraseña.
 */
public class ReiniciarContrasenaTest extends BaseTest {

    private HomeTask home;
    private RegisterTask register;
    private ForgotPasswordTask forgotPassword;
    private LoginTask login;

    @BeforeEach
    void configurar() {
        home = new HomeTask(browser);
        register = new RegisterTask(browser);
        forgotPassword = new ForgotPasswordTask(browser);
        login = new LoginTask(browser);
    }

    @Test
    @DisplayName("Reinicia la contraseña de una cuenta y permite iniciar sesión con la nueva")
    void reiniciarContrasena() {
        String email = TestDataFactory.uniqueEmail("reset.password");
        String passwordOriginal = "Original$1234";
        String passwordNueva = "Nueva$5678";

        new SiteGateTask(browser).unlock(baseUrl, sitePassword);

        home.goToRegister();
        register.registerAdminAndVerify("Reset", "Password", email, passwordOriginal, "Uruguay");

        home.goToForgotPassword();
        forgotPassword.resetPasswordAndVerify(email, passwordNueva);

        home.goToLogin();
        login.loginAndVerify(email, passwordNueva);
    }
}
