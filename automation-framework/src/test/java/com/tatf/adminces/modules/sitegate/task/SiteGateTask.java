package com.tatf.adminces.modules.sitegate.task;

import com.tatf.adminces.modules.sitegate.pom.SiteGatePO;
import com.tatf.core.browser.IBrowser;

public class SiteGateTask {
    private final IBrowser browser;
    private final SiteGatePO siteGate;

    public SiteGateTask(IBrowser browser) {
        this.browser = browser;
        this.siteGate = new SiteGatePO(browser);
    }

    /**
     * Navega a la raíz de AdminCES y pasa la contraseña de sitio.
     */
    public void unlock(String baseUrl, String sitePassword) {
        String rootUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        browser.interaction().navigateTo(rootUrl);
        siteGate.enterPassword(sitePassword);
        siteGate.clickSubmit();
    }
}
