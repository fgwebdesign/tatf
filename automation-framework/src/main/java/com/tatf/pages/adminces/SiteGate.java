package com.tatf.pages.adminces;

import com.tatf.core.browser.IBrowser;

/**
 * Pantalla de acceso al sitio (previa a AdminCES en sí), común a las apps del
 * Centro de Ensayos de Software. Pide una contraseña de sitio antes de mostrar
 * cualquier contenido de la aplicación.
 */
public class SiteGate {
    private static final String PASSWORD_CSS = "#pass";
    private static final String SUBMIT_BUTTON_CSS = "button[type='submit']";

    private final IBrowser browser;
    private final String baseUrl;

    public SiteGate(IBrowser browser, String baseUrl) {
        this.browser = browser;
        this.baseUrl = baseUrl;
    }

    /**
     * Navega a la raíz de AdminCES, pasa la contraseña de sitio y devuelve la
     * página de inicio (sin sesión) de la aplicación.
     */
    public HomePage unlock(String sitePassword) {
        String rootUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        browser.interaction().navigateTo(rootUrl);
        browser.find().css(PASSWORD_CSS).write(sitePassword);
        browser.find().css(SUBMIT_BUTTON_CSS).click();
        return new HomePage(browser, baseUrl);
    }
}
