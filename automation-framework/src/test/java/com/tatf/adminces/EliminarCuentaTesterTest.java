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
 * Escenario: Eliminar cuenta Tester.
 * <p>
 * Inicia sesión como administrador y, para no depender de datos ya cargados,
 * primero da de alta un Tester propio en {@code /adminces/create-user}. Luego lo
 * elimina desde {@code /adminces/view-users} (confirmando el diálogo "¿Eliminar
 * usuario?") y verifica que ya no aparece en el listado.
 */
public class EliminarCuentaTesterTest extends BaseAdminCesTest {

    @Test
    void eliminarCuentaTester() {
        String baseUrl = AdminCesConfig.baseUrl();
        String email = TestDataFactory.uniqueEmail("tester.a.eliminar");

        HomePage homePage = new SiteGate(browser, baseUrl).unlock(AdminCesConfig.sitePassword());
        AccesosPage accesosPage = homePage
                .goToLogin()
                .completeForm(AdminCesConfig.adminEmail(), AdminCesConfig.adminPassword())
                .login();

        accesosPage.goToCreateUser()
                .completeForm("Tester", "AEliminar", email, "Uruguay", "Test$1234", TesterProfile.SENIOR)
                .createAccount();

        ViewUsersPage viewUsersPage = accesosPage.goToViewUsers();

        verify.verifyTrue(viewUsersPage.isUserListed(email),
                "El Tester debería existir en Ver usuarios antes de eliminarlo.");

        viewUsersPage.deleteUser(email).refresh();

        verify.verifyFalse(viewUsersPage.isUserListed(email),
                "El Tester eliminado ya no debería aparecer en Ver usuarios.");
    }
}
