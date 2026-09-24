package com.tatf.adminces.modules.home.pom;

import com.tatf.core.browser.IBrowser;

/**
 * Página de inicio de AdminCES sin sesión iniciada ({@code /adminces}), con
 * accesos a Iniciar sesión, Registrarse y Reiniciar contraseña.
 * <p>
 * AdminCES es una SPA: sus rutas internas solo son alcanzables navegando por
 * los enlaces de la aplicación, no cargando la URL directamente.
 */
public class HomePO {
    private static final String LOGIN_LINK_CSS = "a[href='/adminces/login']";
    private static final String REGISTER_LINK_CSS = "a[href='/adminces/register']";
    private static final String FORGOT_PASSWORD_LINK_CSS = "a[href='/adminces/forgot-password']";

    private final IBrowser browser;

    public HomePO(IBrowser browser) {
        this.browser = browser;
    }

    public void clickLogin() {
        browser.find().css(LOGIN_LINK_CSS).click();
    }

    public void clickRegister() {
        browser.find().css(REGISTER_LINK_CSS).click();
    }

    public void clickForgotPassword() {
        browser.find().css(FORGOT_PASSWORD_LINK_CSS).click();
    }
}
