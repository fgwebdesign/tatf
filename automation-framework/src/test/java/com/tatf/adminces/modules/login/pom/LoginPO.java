package com.tatf.adminces.modules.login.pom;

import com.tatf.core.browser.IBrowser;

/**
 * Formulario de inicio de sesión de administrador ({@code /adminces/login}).
 */
public class LoginPO {
    private static final String EMAIL_CSS = "input[name='inputEmail']";
    private static final String PASSWORD_CSS = "input[name='inputPassword']";
    private static final String LOGIN_BUTTON_XPATH = "//button[contains(normalize-space(.),'Iniciar Sesión')]";

    private final IBrowser browser;

    public LoginPO(IBrowser browser) {
        this.browser = browser;
    }

    public void enterEmail(String email) {
        browser.find().css(EMAIL_CSS).write(email);
    }

    public void enterPassword(String password) {
        browser.find().css(PASSWORD_CSS).write(password);
    }

    public void clickLogin() {
        browser.find().xpath(LOGIN_BUTTON_XPATH).click();
    }
}
