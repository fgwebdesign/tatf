package com.tatf.adminces.modules.sitegate.pom;

import com.tatf.core.browser.IBrowser;

/**
 * Pantalla de acceso al sitio (previa a AdminCES en sí), común a las apps del
 * Centro de Ensayos de Software: pide una contraseña de sitio antes de mostrar
 * cualquier contenido de la aplicación.
 */
public class SiteGatePO {
    private static final String PASSWORD_CSS = "#pass";
    private static final String SUBMIT_BUTTON_CSS = "button[type='submit']";

    private final IBrowser browser;

    public SiteGatePO(IBrowser browser) {
        this.browser = browser;
    }

    public void enterPassword(String password) {
        browser.find().css(PASSWORD_CSS).write(password);
    }

    public void clickSubmit() {
        browser.find().css(SUBMIT_BUTTON_CSS).click();
    }
}
