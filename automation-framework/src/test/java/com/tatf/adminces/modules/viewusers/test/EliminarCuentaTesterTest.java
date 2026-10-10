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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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

    @ParameterizedTest(name = "[{index}] perfil {5}")
    @CsvFileSource(resources = "/datos/eliminar_cuenta_tester.csv", numLinesToSkip = 1)
    @DisplayName("Elimina una cuenta de Tester y deja de estar en Ver usuarios")
    void eliminarCuentaTester(String prefijoEmail, String nombre, String apellido, String paisNacimiento,
                              String passwordPorDefecto, TesterProfile profile) {
        String email = TestDataFactory.uniqueEmail(prefijoEmail);

        new SiteGateTask(browser).unlock(baseUrl, sitePassword);

        home.goToLogin();
        login.loginAndVerify(adminEmail, adminPassword);

        accesos.goToCreateUser();
        createUser.createTesterAndVerify(nombre, apellido, email, paisNacimiento, passwordPorDefecto, profile);

        accesos.goToViewUsers();
        viewUsers.verifyUserListedWithProfile(email, profile.displayLabel());

        viewUsers.deleteUserAndVerify(email);
        viewUsers.refresh();
        viewUsers.verifyUserNotListed(email);
    }
}
