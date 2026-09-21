package com.tatf.pages.adminces;

import com.tatf.core.browser.IBrowser;

/**
 * Formulario de alta de cuenta de administrador ({@code /adminces/register}).
 * Al registrarse la cuenta queda creada pero sin sesión iniciada.
 */
public class RegisterAdminPage {
    private static final String NOMBRE_CSS = "input[name='inputFirstName']";
    private static final String APELLIDO_CSS = "input[name='inputLastName']";
    private static final String EMAIL_CSS = "input[name='inputEmail']";
    private static final String CONTRASENA_CSS = "input[name='inputPassword']";
    private static final String REPETIR_CONTRASENA_CSS = "input[name='inputRepeatPassword']";
    private static final String PAIS_NACIMIENTO_CSS = "input[name='inputCountry']";
    private static final String REGISTRARSE_BUTTON_CSS = "#btnRegister";

    private final IBrowser browser;
    private final String baseUrl;

    public RegisterAdminPage(IBrowser browser, String baseUrl) {
        this.browser = browser;
        this.baseUrl = baseUrl;
    }

    public RegisterAdminPage completeForm(String nombre, String apellido, String email, String password, String paisNacimiento) {
        browser.find().css(NOMBRE_CSS).write(nombre);
        browser.find().css(APELLIDO_CSS).write(apellido);
        browser.find().css(EMAIL_CSS).write(email);
        browser.find().css(CONTRASENA_CSS).write(password);
        browser.find().css(REPETIR_CONTRASENA_CSS).write(password);
        browser.find().css(PAIS_NACIMIENTO_CSS).write(paisNacimiento);
        return this;
    }

    public HomePage register() {
        browser.find().css(REGISTRARSE_BUTTON_CSS).click();
        SweetAlert.confirmIfPresent(browser);
        return new HomePage(browser, baseUrl);
    }
}
