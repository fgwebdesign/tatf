package com.tatf.adminces;

import com.tatf.adminces.support.AdminCesConfig;
import com.tatf.adminces.support.BaseAdminCesTest;
import com.tatf.adminces.support.TestDataFactory;
import com.tatf.pages.adminces.AccesosPage;
import com.tatf.pages.adminces.HomePage;
import com.tatf.pages.adminces.RegisterAdminPage;
import com.tatf.pages.adminces.SiteGate;
import org.junit.jupiter.api.Test;

/**
 * Escenario: Reiniciar contraseña.
 * <p>
 * Para no depender de ni alterar cuentas ya existentes, el escenario primero crea
 * una cuenta de administrador propia, le reinicia la contraseña desde
 * {@code /adminces/forgot-password} y confirma el cambio logueándose con la nueva clave.
 */
public class ReiniciarContrasenaTest extends BaseAdminCesTest {

    @Test
    void reiniciarContrasena() {
        String baseUrl = AdminCesConfig.baseUrl();
        String email = TestDataFactory.uniqueEmail("reset.password");
        String passwordOriginal = "Original$1234";
        String passwordNueva = "Nueva$5678";

        HomePage homePage = new SiteGate(browser, baseUrl).unlock(AdminCesConfig.sitePassword());
        RegisterAdminPage registerPage = homePage.goToRegister();
        registerPage.completeForm("Reset", "Password", email, passwordOriginal, "Uruguay");
        homePage = registerPage.register();

        homePage = homePage.goToForgotPassword()
                .completeForm(email, passwordNueva)
                .resetPassword();

        AccesosPage accesosPage = homePage
                .goToLogin()
                .completeForm(email, passwordNueva)
                .login();

        verify.verifyTrue(accesosPage.isDisplayed(),
                "Con la nueva contraseña debería poder iniciar sesión correctamente.");
    }
}
