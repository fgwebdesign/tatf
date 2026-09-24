package com.tatf.adminces.modules.sweetalert.task;

import com.tatf.adminces.modules.sweetalert.pom.SweetAlertPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class SweetAlertTask {
    private final IBrowser browser;
    private final SweetAlertPO sweetAlert;

    public SweetAlertTask(IBrowser browser) {
        this.browser = browser;
        this.sweetAlert = new SweetAlertPO(browser);
    }

    /**
     * Confirma el diálogo si está presente, sin verificar su contenido. Útil cuando
     * la aparición del diálogo es opcional.
     */
    public void confirmIfPresent() {
        waitForDialog();
        if (sweetAlert.isPresent()) {
            sweetAlert.confirm();
        }
    }

    /**
     * Verifica el título y el mensaje del diálogo contra los valores esperados y lo confirma.
     */
    public void verifyMessageAndConfirm(String expectedTitle, String expectedBody) {
        waitForDialog();
        IVerify.create().verify(expectedTitle, sweetAlert.getTitle(), "El título del diálogo no es el esperado.");
        IVerify.create().verify(expectedBody, sweetAlert.getBody(), "El mensaje del diálogo no es el esperado.");
        sweetAlert.confirm();
    }

    private void waitForDialog() {
        browser.wait(1).sleep();
    }
}
