package com.tatf.adminces.modules.accesos.pom;

import com.tatf.core.browser.IBrowser;

/**
 * Página principal luego de iniciar sesión ({@code /adminces}), con el menú
 * lateral de administrador (Crear usuario, Reiniciar contraseña, Ver usuarios).
 */
public class AccesosPO {
    private static final String CREATE_USER_LINK_CSS = "a[href='/adminces/create-user']";
    private static final String VIEW_USERS_LINK_CSS = "a[href='/adminces/view-users']";

    private final IBrowser browser;

    public AccesosPO(IBrowser browser) {
        this.browser = browser;
    }

    /**
     * El enlace a Ver usuarios solo está presente en el menú de administrador
     * logueado, por lo que sirve como señal de que la sesión está iniciada.
     */
    public boolean isViewUsersLinkDisplayed() {
        return !browser.find().cssList(VIEW_USERS_LINK_CSS).isEmpty();
    }

    public void clickCreateUser() {
        browser.find().css(CREATE_USER_LINK_CSS).click();
    }

    public void clickViewUsers() {
        browser.find().css(VIEW_USERS_LINK_CSS).click();
    }
}
