package com.tatf.adminces.modules.register.pom;

import com.tatf.core.browser.IBrowser;

/**
 * Formulario de alta de cuenta de administrador ({@code /adminces/register}).
 * Al registrarse, la cuenta queda creada pero sin sesión iniciada.
 */
public class RegisterPO {
    private static final String NOMBRE_CSS = "input[name='inputFirstName']";
    private static final String APELLIDO_CSS = "input[name='inputLastName']";
    private static final String EMAIL_CSS = "input[name='inputEmail']";
    private static final String CONTRASENA_CSS = "input[name='inputPassword']";
    private static final String REPETIR_CONTRASENA_CSS = "input[name='inputRepeatPassword']";
    private static final String PAIS_NACIMIENTO_CSS = "input[name='inputCountry']";
    private static final String REGISTRARSE_BUTTON_CSS = "#btnRegister";

    private final IBrowser browser;

    public RegisterPO(IBrowser browser) {
        this.browser = browser;
    }

    public void enterNombre(String nombre) {
        browser.find().css(NOMBRE_CSS).write(nombre);
    }

    public void enterApellido(String apellido) {
        browser.find().css(APELLIDO_CSS).write(apellido);
    }

    public void enterEmail(String email) {
        browser.find().css(EMAIL_CSS).write(email);
    }

    public void enterPassword(String password) {
        browser.find().css(CONTRASENA_CSS).write(password);
    }

    public void enterRepeatPassword(String password) {
        browser.find().css(REPETIR_CONTRASENA_CSS).write(password);
    }

    public void enterPaisNacimiento(String pais) {
        browser.find().css(PAIS_NACIMIENTO_CSS).write(pais);
    }

    public void clickRegistrarse() {
        browser.find().css(REGISTRARSE_BUTTON_CSS).click();
    }
}
