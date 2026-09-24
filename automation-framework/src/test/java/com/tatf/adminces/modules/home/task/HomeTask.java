package com.tatf.adminces.modules.home.task;

import com.tatf.adminces.modules.home.pom.HomePO;
import com.tatf.core.browser.IBrowser;

public class HomeTask {
    private final HomePO home;

    public HomeTask(IBrowser browser) {
        this.home = new HomePO(browser);
    }

    public void goToLogin() {
        home.clickLogin();
    }

    public void goToRegister() {
        home.clickRegister();
    }

    public void goToForgotPassword() {
        home.clickForgotPassword();
    }
}
