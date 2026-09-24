package com.tatf.adminces.modules.register.task;

import com.tatf.adminces.modules.register.data.RegisterData;
import com.tatf.adminces.modules.register.pom.RegisterPO;
import com.tatf.adminces.modules.sweetalert.task.SweetAlertTask;
import com.tatf.core.browser.IBrowser;

public class RegisterTask {
    private final IBrowser browser;
    private final RegisterPO register;

    public RegisterTask(IBrowser browser) {
        this.browser = browser;
        this.register = new RegisterPO(browser);
    }

    /**
     * Da de alta una cuenta de administrador y verifica el diálogo de confirmación.
     */
    public void registerAdminAndVerify(String nombre, String apellido, String email, String password, String paisNacimiento) {
        register.enterNombre(nombre);
        register.enterApellido(apellido);
        register.enterEmail(email);
        register.enterPassword(password);
        register.enterRepeatPassword(password);
        register.enterPaisNacimiento(paisNacimiento);
        register.clickRegistrarse();
        new SweetAlertTask(browser).verifyMessageAndConfirm(RegisterData.SUCCESS_TITLE, RegisterData.SUCCESS_BODY);
    }
}
