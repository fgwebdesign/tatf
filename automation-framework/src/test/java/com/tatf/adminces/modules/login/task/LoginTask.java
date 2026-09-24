package com.tatf.adminces.modules.login.task;

import com.tatf.adminces.modules.accesos.task.AccesosTask;
import com.tatf.adminces.modules.login.data.LoginData;
import com.tatf.adminces.modules.login.pom.LoginPO;
import com.tatf.adminces.modules.sweetalert.task.SweetAlertTask;
import com.tatf.core.browser.IBrowser;

public class LoginTask {
    private final IBrowser browser;
    private final LoginPO login;

    public LoginTask(IBrowser browser) {
        this.browser = browser;
        this.login = new LoginPO(browser);
    }

    /**
     * Inicia sesión, verifica el diálogo de confirmación y que la sesión haya quedado iniciada.
     */
    public void loginAndVerify(String email, String password) {
        login.enterEmail(email);
        login.enterPassword(password);
        login.clickLogin();
        new SweetAlertTask(browser).verifyMessageAndConfirm(LoginData.SUCCESS_TITLE, LoginData.SUCCESS_BODY);
        new AccesosTask(browser).verifySessionStarted();
    }
}
