package com.tatf.adminces.modules.viewusers.test;

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
 * Escenario: Eliminar cuenta Tester.
 * <p>
 * Para no depender de datos ya cargados, primero da de alta un Tester propio y
 * luego lo elimina.
 */
public class EliminarCuentaTesterTest extends BaseTest {

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
    @DisplayName("Elimina una cuenta de Tester y deja de estar en Ver usuarios")
    void eliminarCuentaTester() {
        String email = TestDataFactory.uniqueEmail("tester.a.eliminar");

        new SiteGateTask(browser).unlock(baseUrl, sitePassword);

        home.goToLogin();
        login.loginAndVerify(adminEmail, adminPassword);

        accesos.goToCreateUser();
        createUser.createTesterAndVerify("Tester", "AEliminar", email, "Uruguay", "Test$1234", TesterProfile.SENIOR);

        accesos.goToViewUsers();
        viewUsers.verifyUserListedWithProfile(email, TesterProfile.SENIOR.displayLabel());

        viewUsers.deleteUserAndVerify(email);
        viewUsers.refresh();
        viewUsers.verifyUserNotListed(email);
    }
}
