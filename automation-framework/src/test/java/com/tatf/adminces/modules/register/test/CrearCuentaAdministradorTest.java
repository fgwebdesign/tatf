package com.tatf.adminces.modules.register.test;

import com.tatf.adminces.modules.accesos.task.AccesosTask;
import com.tatf.adminces.modules.base.BaseTest;
import com.tatf.adminces.modules.home.task.HomeTask;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.register.task.RegisterTask;
import com.tatf.adminces.modules.sitegate.task.SiteGateTask;
import com.tatf.adminces.modules.viewusers.data.ViewUsersData;
import com.tatf.adminces.modules.viewusers.task.ViewUsersTask;
import com.tatf.adminces.support.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Escenario: Crear cuenta Administrador.
 */
public class CrearCuentaAdministradorTest extends BaseTest {

    private HomeTask home;
    private RegisterTask register;
    private LoginTask login;
    private AccesosTask accesos;
    private ViewUsersTask viewUsers;

    @BeforeEach
    void configurar() {
        home = new HomeTask(browser);
        register = new RegisterTask(browser);
        login = new LoginTask(browser);
        accesos = new AccesosTask(browser);
        viewUsers = new ViewUsersTask(browser);
    }

    @Test
    @DisplayName("Crea una cuenta de Administrador y queda visible en Ver usuarios")
    void crearCuentaAdministrador() {
        String email = TestDataFactory.uniqueEmail("nuevo.admin");
        String password = "Admin$1234";

        new SiteGateTask(browser).unlock(baseUrl, sitePassword);

        home.goToRegister();
        register.registerAdminAndVerify("Nuevo", "Administrador", email, password, "Uruguay");

        home.goToLogin();
        login.loginAndVerify(adminEmail, adminPassword);

        accesos.goToViewUsers();
        viewUsers.verifyUserListedWithProfile(email, ViewUsersData.ADMIN_PROFILE_LABEL);
    }
}
