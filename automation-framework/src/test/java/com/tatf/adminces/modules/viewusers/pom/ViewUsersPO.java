package com.tatf.adminces.modules.viewusers.pom;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;

import java.util.List;

/**
 * Página de listado de usuarios ({@code /adminces/view-users}).
 * Requiere haber iniciado sesión previamente como administrador.
 */
public class ViewUsersPO {
    private final IBrowser browser;

    public ViewUsersPO(IBrowser browser) {
        this.browser = browser;
    }

    public boolean isUserListed(String email) {
        return !rowByEmail(email).isEmpty();
    }

    public String getProfile(String email) {
        return rowByEmail(email).get(0)
                .xpath(".//td[5]")
                .getText();
    }

    /**
     * Hace click en la acción de eliminar de la fila del usuario indicado.
     * El botón de acción tiene como {@code id} el email del usuario de esa fila.
     */
    public void clickDelete(String email) {
        browser.find().id(email).click();
    }

    private List<Element> rowByEmail(String email) {
        return browser.find().xpathList(rowXpath(email));
    }

    private String rowXpath(String email) {
        return "//td[normalize-space(text())='" + email + "']/parent::tr";
    }
}
