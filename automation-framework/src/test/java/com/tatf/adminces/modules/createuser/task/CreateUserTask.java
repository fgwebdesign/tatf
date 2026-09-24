package com.tatf.adminces.modules.createuser.task;

import com.tatf.adminces.modules.createuser.data.CreateUserData;
import com.tatf.adminces.modules.createuser.data.TesterProfile;
import com.tatf.adminces.modules.createuser.pom.CreateUserPO;
import com.tatf.adminces.modules.sweetalert.task.SweetAlertTask;
import com.tatf.core.browser.IBrowser;

public class CreateUserTask {
    private final IBrowser browser;
    private final CreateUserPO createUser;

    public CreateUserTask(IBrowser browser) {
        this.browser = browser;
        this.createUser = new CreateUserPO(browser);
    }

    /**
     * Da de alta una cuenta de Tester y verifica el diálogo de confirmación.
     */
    public void createTesterAndVerify(String nombre, String apellido, String email, String paisNacimiento,
                                       String defaultPassword, TesterProfile profile) {
        createUser.enterNombre(nombre);
        createUser.enterApellido(apellido);
        createUser.enterEmail(email);
        createUser.selectPaisNacimiento(paisNacimiento);
        createUser.enterContrasenaPorDefecto(defaultPassword);
        createUser.selectProfile(profile);
        createUser.clickCrearCuenta();
        new SweetAlertTask(browser).verifyMessageAndConfirm(CreateUserData.SUCCESS_TITLE, CreateUserData.SUCCESS_BODY);
    }
}
