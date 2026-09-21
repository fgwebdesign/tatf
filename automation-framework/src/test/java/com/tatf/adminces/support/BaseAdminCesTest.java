package com.tatf.adminces.support;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * Clase base para las pruebas de AdminCES: expone el {@link IBrowser} (Factory + Singleton)
 * y un {@link IVerify} listos para usar, y se encarga de abrir y cerrar el navegador.
 */
public abstract class BaseAdminCesTest {
    protected static IBrowser browser;
    protected static IVerify verify;

    @BeforeAll
    static void setUpBrowser() {
        browser = BrowserFactory.getBrowser(true);
        verify = IVerify.create();
    }

    @AfterAll
    static void tearDownBrowser() {
        BrowserFactory.quitBrowser();
    }
}
