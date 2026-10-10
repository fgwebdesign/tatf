package com.tatf.adminces.modules.base;

import com.tatf.adminces.support.AdminCesConfig;
import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * Base común a las pruebas de AdminCES: crea el {@link IBrowser} (Factory + Singleton
 * del core) y expone la configuración de la aplicación a las pruebas.
 */
public class BaseTest {
    protected static IBrowser browser;
    protected static String baseUrl;
    protected static String sitePassword;
    protected static String adminEmail;
    protected static String adminPassword;

    @BeforeAll
    static void configuration() {
        browser = BrowserFactory.getBrowser();
        baseUrl = AdminCesConfig.baseUrl();
        sitePassword = AdminCesConfig.sitePassword();
        adminEmail = AdminCesConfig.adminEmail();
        adminPassword = AdminCesConfig.adminPassword();
    }

    @AfterAll
    static void close() {
        BrowserFactory.quitBrowser();
    }
}
