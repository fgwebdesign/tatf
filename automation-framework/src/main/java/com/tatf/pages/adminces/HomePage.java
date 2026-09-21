package com.tatf.pages.adminces;

import com.tatf.core.browser.IBrowser;

/**
 * Página de inicio de AdminCES sin sesión iniciada ({@code /adminces}), con
 * accesos a Iniciar sesión, Registrarse y Reiniciar contraseña.
 * <p>
 * AdminCES es una SPA: sus rutas internas ({@code /login}, {@code /register}, etc.)
 * solo son alcanzables navegando por los enlaces de la aplicación, no cargando la
 * URL directamente.
 */
public class HomePage {
    private static final String LOGIN_LINK_CSS = "a[href='/adminces/login']";
    private static final String REGISTER_LINK_CSS = "a[href='/adminces/register']";
    private static final String FORGOT_PASSWORD_LINK_CSS = "a[href='/adminces/forgot-password']";

    private final IBrowser browser;
    private final String baseUrl;

    public HomePage(IBrowser browser, String baseUrl) {
        this.browser = browser;
        this.baseUrl = baseUrl;
    }

    public LoginPage goToLogin() {
        browser.find().css(LOGIN_LINK_CSS).click();
        return new LoginPage(browser, baseUrl);
    }

    public RegisterAdminPage goToRegister() {
        browser.find().css(REGISTER_LINK_CSS).click();
        return new RegisterAdminPage(browser, baseUrl);
    }

    public ForgotPasswordPage goToForgotPassword() {
        browser.find().css(FORGOT_PASSWORD_LINK_CSS).click();
        return new ForgotPasswordPage(browser, baseUrl);
    }
}
