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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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

    @ParameterizedTest(name = "[{index}] {1} {2}")
    @CsvFileSource(resources = "/datos/reiniciar_contrasena.csv", numLinesToSkip = 1)
    @DisplayName("Reinicia la contraseña de una cuenta y permite iniciar sesión con la nueva")
    void reiniciarContrasena(String prefijoEmail, String nombre, String apellido, String paisNacimiento,
                             String passwordOriginal, String passwordNueva) {
        String email = TestDataFactory.uniqueEmail(prefijoEmail);

        new SiteGateTask(browser).unlock(baseUrl, sitePassword);

        home.goToRegister();
        register.registerAdminAndVerify(nombre, apellido, email, passwordOriginal, paisNacimiento);

        home.goToForgotPassword();
        forgotPassword.resetPasswordAndVerify(email, passwordNueva);

        home.goToLogin();
        login.loginAndVerify(email, passwordNueva);
    }
}
