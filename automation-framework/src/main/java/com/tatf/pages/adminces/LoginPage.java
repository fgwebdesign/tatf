package com.tatf.pages.adminces;

import com.tatf.core.browser.IBrowser;

/**
 * Formulario de inicio de sesión de administrador ({@code /adminces/login}).
 */
public class LoginPage {
    private static final String EMAIL_CSS = "input[name='inputEmail']";
    private static final String PASSWORD_CSS = "input[name='inputPassword']";
    private static final String LOGIN_BUTTON_XPATH = "//button[contains(normalize-space(.),'Iniciar Sesión')]";

    private final IBrowser browser;
    private final String baseUrl;

    public LoginPage(IBrowser browser, String baseUrl) {
        this.browser = browser;
        this.baseUrl = baseUrl;
    }

    public LoginPage completeForm(String email, String password) {
        browser.find().css(EMAIL_CSS).write(email);
        browser.find().css(PASSWORD_CSS).write(password);
        return this;
    }

    public AccesosPage login() {
        browser.find().xpath(LOGIN_BUTTON_XPATH).click();
        SweetAlert.confirmIfPresent(browser);
        return new AccesosPage(browser, baseUrl);
    }
}
