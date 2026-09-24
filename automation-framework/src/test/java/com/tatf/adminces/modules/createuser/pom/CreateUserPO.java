package com.tatf.adminces.modules.createuser.pom;

import com.tatf.adminces.modules.createuser.data.TesterProfile;
import com.tatf.core.browser.IBrowser;

/**
 * Formulario de alta de cuenta de Tester ({@code /adminces/create-user}).
 * Requiere haber iniciado sesión previamente como administrador.
 */
public class CreateUserPO {
    private static final String NOMBRE_CSS = "input[name='inputFirstName']";
    private static final String APELLIDO_CSS = "input[name='inputLastName']";
    private static final String EMAIL_CSS = "input[name='inputEmail']";
    private static final String PAIS_NACIMIENTO_SELECT_CSS = "select[name='inputCountry']";
    private static final String CONTRASENA_POR_DEFECTO_CSS = "input[name='inputPassword']";
    private static final String CREAR_CUENTA_BUTTON_CSS = "#btnRegister";

    private final IBrowser browser;

    public CreateUserPO(IBrowser browser) {
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

    public void selectPaisNacimiento(String pais) {
        browser.find().css(PAIS_NACIMIENTO_SELECT_CSS).selectValue(pais);
    }

    public void enterContrasenaPorDefecto(String password) {
        browser.find().css(CONTRASENA_POR_DEFECTO_CSS).write(password);
    }

    public void selectProfile(TesterProfile profile) {
        browser.find().id(profile.radioId()).click();
    }

    public void clickCrearCuenta() {
        browser.find().css(CREAR_CUENTA_BUTTON_CSS).click();
    }
}
