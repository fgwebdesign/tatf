package com.tatf.adminces;

import com.tatf.adminces.support.AdminCesConfig;
import com.tatf.adminces.support.BaseAdminCesTest;
import com.tatf.adminces.support.TestDataFactory;
import com.tatf.pages.adminces.AccesosPage;
import com.tatf.pages.adminces.HomePage;
import com.tatf.pages.adminces.SiteGate;
import com.tatf.pages.adminces.TesterProfile;
import com.tatf.pages.adminces.ViewUsersPage;
import org.junit.jupiter.api.Test;

/**
 * Escenario: Crear cuenta Tester.
 * <p>
 * Inicia sesión como administrador, da de alta un Tester desde
 * {@code /adminces/create-user} y confirma que aparece en {@code /adminces/view-users}
 * con el perfil elegido.
 */
public class CrearCuentaTesterTest extends BaseAdminCesTest {

    @Test
    void crearCuentaTester() {
        String baseUrl = AdminCesConfig.baseUrl();
        String email = TestDataFactory.uniqueEmail("nuevo.tester");
        TesterProfile profile = TesterProfile.JUNIOR;

        HomePage homePage = new SiteGate(browser, baseUrl).unlock(AdminCesConfig.sitePassword());
        AccesosPage accesosPage = homePage
                .goToLogin()
                .completeForm(AdminCesConfig.adminEmail(), AdminCesConfig.adminPassword())
                .login();

        accesosPage.goToCreateUser()
                .completeForm("Nuevo", "Tester", email, "Uruguay", "Test$1234", profile)
                .createAccount();

        ViewUsersPage viewUsersPage = accesosPage.goToViewUsers();

        verify.verifyTrue(viewUsersPage.isUserListed(email),
                "El nuevo Tester debería aparecer en Ver usuarios.");
        verify.verify(profile.displayLabel(), viewUsersPage.profileOf(email),
                "El perfil del Tester creado debería coincidir con el elegido en el formulario.");
    }
}
