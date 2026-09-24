package com.tatf.adminces.modules.accesos.task;

import com.tatf.adminces.modules.accesos.pom.AccesosPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class AccesosTask {
    private final AccesosPO accesos;

    public AccesosTask(IBrowser browser) {
        this.accesos = new AccesosPO(browser);
    }

    public void verifySessionStarted() {
        IVerify.create().verifyTrue(accesos.isViewUsersLinkDisplayed(),
                "Debería verse el menú de administrador logueado.");
    }

    public void goToCreateUser() {
        accesos.clickCreateUser();
    }

    public void goToViewUsers() {
        accesos.clickViewUsers();
    }
}
