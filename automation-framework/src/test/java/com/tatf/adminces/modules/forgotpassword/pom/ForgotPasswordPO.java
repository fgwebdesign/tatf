package com.tatf.adminces.modules.forgotpassword.pom;

import com.tatf.core.browser.IBrowser;

/**
 * Formulario de reinicio de contraseña ({@code /adminces/forgot-password}).
 */
public class ForgotPasswordPO {
    private static final String EMAIL_CSS = "input[name='inputEmail']";
    private static final String CONTRASENA_CSS = "input[name='inputPassword']";
    private static final String REPETIR_CONTRASENA_CSS = "input[name='inputRepeatPassword']";
    private static final String REINICIAR_BUTTON_CSS = "#btnReset";

    private final IBrowser browser;

    public ForgotPasswordPO(IBrowser browser) {
        this.browser = browser;
    }

    public void enterEmail(String email) {
        browser.find().css(EMAIL_CSS).write(email);
    }

    public void enterNewPassword(String password) {
        browser.find().css(CONTRASENA_CSS).write(password);
    }

    public void enterRepeatPassword(String password) {
        browser.find().css(REPETIR_CONTRASENA_CSS).write(password);
    }

    public void clickReiniciar() {
        browser.find().css(REINICIAR_BUTTON_CSS).click();
    }
}
