package com.tatf.pages.adminces;

import com.tatf.core.browser.IBrowser;

/**
 * Formulario de reinicio de contraseña ({@code /adminces/forgot-password}).
 */
public class ForgotPasswordPage {
    private static final String EMAIL_CSS = "input[name='inputEmail']";
    private static final String CONTRASENA_CSS = "input[name='inputPassword']";
    private static final String REPETIR_CONTRASENA_CSS = "input[name='inputRepeatPassword']";
    private static final String REINICIAR_BUTTON_CSS = "#btnReset";

    private final IBrowser browser;
    private final String baseUrl;

    public ForgotPasswordPage(IBrowser browser, String baseUrl) {
        this.browser = browser;
        this.baseUrl = baseUrl;
    }

    public ForgotPasswordPage completeForm(String email, String newPassword) {
        browser.find().css(EMAIL_CSS).write(email);
        browser.find().css(CONTRASENA_CSS).write(newPassword);
        browser.find().css(REPETIR_CONTRASENA_CSS).write(newPassword);
        return this;
    }

    public HomePage resetPassword() {
        browser.find().css(REINICIAR_BUTTON_CSS).click();
        SweetAlert.confirmIfPresent(browser);
        return new HomePage(browser, baseUrl);
    }
}
