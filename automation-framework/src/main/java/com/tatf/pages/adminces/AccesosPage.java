package com.tatf.pages.adminces;

import com.tatf.core.browser.IBrowser;

/**
 * Página principal luego de iniciar sesión ({@code /adminces}), con accesos a
 * Crear usuario y Ver usuarios en el menú lateral.
 */
public class AccesosPage {
    private static final String CREATE_USER_LINK_CSS = "a[href='/adminces/create-user']";
    private static final String VIEW_USERS_LINK_CSS = "a[href='/adminces/view-users']";

    private final IBrowser browser;
    private final String baseUrl;

    public AccesosPage(IBrowser browser, String baseUrl) {
        this.browser = browser;
        this.baseUrl = baseUrl;
    }

    /**
     * Indica si la sesión está iniciada, verificando que el menú de administrador
     * (solo visible logueado) esté presente.
     */
    public boolean isDisplayed() {
        return !browser.find().cssList(VIEW_USERS_LINK_CSS).isEmpty();
    }

    public CreateUserPage goToCreateUser() {
        browser.find().css(CREATE_USER_LINK_CSS).click();
        return new CreateUserPage(browser, baseUrl);
    }

    public ViewUsersPage goToViewUsers() {
        browser.find().css(VIEW_USERS_LINK_CSS).click();
        return new ViewUsersPage(browser, baseUrl);
    }
}
