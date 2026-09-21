package com.tatf.pages.adminces;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;

import java.util.List;

/**
 * Página de listado de usuarios ({@code /adminces/view-users}).
 * Requiere haber iniciado sesión previamente como administrador.
 */
public class ViewUsersPage {
    private static final String VIEW_USERS_LINK_CSS = "a[href='/adminces/view-users']";

    private final IBrowser browser;
    private final String baseUrl;

    public ViewUsersPage(IBrowser browser, String baseUrl) {
        this.browser = browser;
        this.baseUrl = baseUrl;
    }

    /**
     * Vuelve a entrar a Ver usuarios desde el menú lateral, para refrescar el listado.
     */
    public ViewUsersPage refresh() {
        browser.find().css(VIEW_USERS_LINK_CSS).click();
        return this;
    }

    public boolean isUserListed(String email) {
        return !rowByEmail(email).isEmpty();
    }

    public String profileOf(String email) {
        return rowByEmail(email).get(0)
                .xpath(".//td[5]")
                .getText();
    }

    /**
     * Elimina al usuario indicado, confirmando el diálogo "¿Eliminar usuario?".
     * El botón de acción tiene como {@code id} el email del usuario de esa fila.
     */
    public ViewUsersPage deleteUser(String email) {
        browser.find().id(email).click();
        SweetAlert.confirmIfPresent(browser);
        SweetAlert.confirmIfPresent(browser);
        return this;
    }

    private List<Element> rowByEmail(String email) {
        return browser.find().xpathList(rowXpath(email));
    }

    private String rowXpath(String email) {
        return "//td[normalize-space(text())='" + email + "']/parent::tr";
    }
}
