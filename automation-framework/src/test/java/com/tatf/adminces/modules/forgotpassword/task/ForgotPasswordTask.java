package com.tatf.adminces.modules.forgotpassword.task;

import com.tatf.adminces.modules.forgotpassword.data.ForgotPasswordData;
import com.tatf.adminces.modules.forgotpassword.pom.ForgotPasswordPO;
import com.tatf.adminces.modules.sweetalert.task.SweetAlertTask;
import com.tatf.core.browser.IBrowser;

public class ForgotPasswordTask {
    private final IBrowser browser;
    private final ForgotPasswordPO forgotPassword;

    public ForgotPasswordTask(IBrowser browser) {
        this.browser = browser;
        this.forgotPassword = new ForgotPasswordPO(browser);
    }

    /**
     * Reinicia la contraseña de la cuenta indicada y verifica el diálogo de confirmación.
     */
    public void resetPasswordAndVerify(String email, String newPassword) {
        forgotPassword.enterEmail(email);
        forgotPassword.enterNewPassword(newPassword);
        forgotPassword.enterRepeatPassword(newPassword);
        forgotPassword.clickReiniciar();
        new SweetAlertTask(browser).verifyMessageAndConfirm(ForgotPasswordData.SUCCESS_TITLE, ForgotPasswordData.SUCCESS_BODY);
    }
}
