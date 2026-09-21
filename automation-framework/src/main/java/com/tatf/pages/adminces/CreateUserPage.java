package com.tatf.pages.adminces;

import com.tatf.core.browser.IBrowser;

/**
 * Formulario de alta de cuenta de Tester ({@code /adminces/create-user}).
 * Requiere haber iniciado sesión previamente como administrador.
 */
public class CreateUserPage {
    private static final String NOMBRE_CSS = "input[name='inputFirstName']";
    private static final String APELLIDO_CSS = "input[name='inputLastName']";
    private static final String EMAIL_CSS = "input[name='inputEmail']";
    private static final String PAIS_NACIMIENTO_SELECT_CSS = "select[name='inputCountry']";
    private static final String CONTRASENA_POR_DEFECTO_CSS = "input[name='inputPassword']";
    private static final String CREAR_CUENTA_BUTTON_CSS = "#btnRegister";

    private final IBrowser browser;
    private final String baseUrl;

    public CreateUserPage(IBrowser browser, String baseUrl) {
        this.browser = browser;
        this.baseUrl = baseUrl;
    }

    public CreateUserPage completeForm(String nombre, String apellido, String email, String paisNacimiento,
                                        String defaultPassword, TesterProfile profile) {
        browser.find().css(NOMBRE_CSS).write(nombre);
        browser.find().css(APELLIDO_CSS).write(apellido);
        browser.find().css(EMAIL_CSS).write(email);
        browser.find().css(PAIS_NACIMIENTO_SELECT_CSS).selectValue(paisNacimiento);
        browser.find().css(CONTRASENA_POR_DEFECTO_CSS).write(defaultPassword);
        browser.find().id(profile.radioId()).click();
        return this;
    }

    public AccesosPage createAccount() {
        browser.find().css(CREAR_CUENTA_BUTTON_CSS).click();
        SweetAlert.confirmIfPresent(browser);
        return new AccesosPage(browser, baseUrl);
    }
}
