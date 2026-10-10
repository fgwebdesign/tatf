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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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

    @ParameterizedTest(name = "[{index}] {1} {2}")
    @CsvFileSource(resources = "/datos/crear_cuenta_administrador.csv", numLinesToSkip = 1)
    @DisplayName("Crea una cuenta de Administrador y queda visible en Ver usuarios")
    void crearCuentaAdministrador(String prefijoEmail, String nombre, String apellido, String password,
                                  String paisNacimiento) {
        String email = TestDataFactory.uniqueEmail(prefijoEmail);

        new SiteGateTask(browser).unlock(baseUrl, sitePassword);

        home.goToRegister();
        register.registerAdminAndVerify(nombre, apellido, email, password, paisNacimiento);

        home.goToLogin();
        login.loginAndVerify(adminEmail, adminPassword);

        accesos.goToViewUsers();
        viewUsers.verifyUserListedWithProfile(email, ViewUsersData.ADMIN_PROFILE_LABEL);
    }
}
