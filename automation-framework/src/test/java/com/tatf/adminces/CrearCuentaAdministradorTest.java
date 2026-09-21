package com.tatf.adminces;

import com.tatf.adminces.support.AdminCesConfig;
import com.tatf.adminces.support.BaseAdminCesTest;
import com.tatf.adminces.support.TestDataFactory;
import com.tatf.pages.adminces.AccesosPage;
import com.tatf.pages.adminces.HomePage;
import com.tatf.pages.adminces.RegisterAdminPage;
import com.tatf.pages.adminces.SiteGate;
import com.tatf.pages.adminces.ViewUsersPage;
import org.junit.jupiter.api.Test;

/**
 * Escenario: Crear cuenta Administrador.
 * <p>
 * Da de alta una cuenta de administrador desde {@code /adminces/register} y confirma
 * que quedó registrada, logueándose con la cuenta de administrador conocida y
 * buscándola en {@code /adminces/view-users} con perfil "Administrador".
 */
public class CrearCuentaAdministradorTest extends BaseAdminCesTest {

    @Test
    void crearCuentaAdministrador() {
        String baseUrl = AdminCesConfig.baseUrl();
        String email = TestDataFactory.uniqueEmail("nuevo.admin");
        String password = "Admin$1234";

        HomePage homePage = new SiteGate(browser, baseUrl).unlock(AdminCesConfig.sitePassword());
        RegisterAdminPage registerPage = homePage.goToRegister();

        registerPage.completeForm("Nuevo", "Administrador", email, password, "Uruguay");
        homePage = registerPage.register();

        AccesosPage accesosPage = homePage
                .goToLogin()
                .completeForm(AdminCesConfig.adminEmail(), AdminCesConfig.adminPassword())
                .login();

        verify.verifyTrue(accesosPage.isDisplayed(),
                "Debería poder iniciar sesión con la cuenta de administrador conocida.");

        ViewUsersPage viewUsersPage = accesosPage.goToViewUsers();

        verify.verifyTrue(viewUsersPage.isUserListed(email),
                "La nueva cuenta de administrador debería aparecer en Ver usuarios.");
        verify.verify("Administrador", viewUsersPage.profileOf(email),
                "El perfil de la cuenta creada debería ser Administrador.");
    }
}
