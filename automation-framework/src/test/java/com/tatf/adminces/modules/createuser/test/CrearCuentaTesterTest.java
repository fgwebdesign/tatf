package com.tatf.adminces.modules.createuser.test;

import com.tatf.adminces.modules.accesos.task.AccesosTask;
import com.tatf.adminces.modules.base.BaseTest;
import com.tatf.adminces.modules.createuser.data.TesterProfile;
import com.tatf.adminces.modules.createuser.task.CreateUserTask;
import com.tatf.adminces.modules.home.task.HomeTask;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.sitegate.task.SiteGateTask;
import com.tatf.adminces.modules.viewusers.task.ViewUsersTask;
import com.tatf.adminces.support.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Escenario: Crear cuenta Tester.
 */
public class CrearCuentaTesterTest extends BaseTest {

    private HomeTask home;
    private LoginTask login;
    private AccesosTask accesos;
    private CreateUserTask createUser;
    private ViewUsersTask viewUsers;

    @BeforeEach
    void configurar() {
        home = new HomeTask(browser);
        login = new LoginTask(browser);
        accesos = new AccesosTask(browser);
        createUser = new CreateUserTask(browser);
        viewUsers = new ViewUsersTask(browser);
    }

    @Test
    @DisplayName("Crea una cuenta de Tester y queda visible en Ver usuarios con el perfil elegido")
    void crearCuentaTester() {
        String email = TestDataFactory.uniqueEmail("nuevo.tester");
        TesterProfile profile = TesterProfile.JUNIOR;

        new SiteGateTask(browser).unlock(baseUrl, sitePassword);

        home.goToLogin();
        login.loginAndVerify(adminEmail, adminPassword);

        accesos.goToCreateUser();
        createUser.createTesterAndVerify("Nuevo", "Tester", email, "Uruguay", "Test$1234", profile);

        accesos.goToViewUsers();
        viewUsers.verifyUserListedWithProfile(email, profile.displayLabel());
    }
}
