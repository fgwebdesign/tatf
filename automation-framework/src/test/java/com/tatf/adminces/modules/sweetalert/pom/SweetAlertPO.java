package com.tatf.adminces.modules.sweetalert.pom;

import com.tatf.core.browser.IBrowser;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * AdminCES confirma cada acción (login, alta, reinicio de contraseña, eliminación)
 * con un diálogo de SweetAlert2. Aparece en distintas pantallas de la aplicación,
 * por lo que se modela como su propio Page Object en lugar de duplicar sus
 * localizadores en cada pantalla que lo dispara.
 */
public class SweetAlertPO {
    private static final String TITLE_CSS = ".swal2-title";
    private static final String BODY_CSS = ".swal2-html-container";
    private static final String CONFIRM_BUTTON_CSS = ".swal2-confirm";

    private final IBrowser browser;

    public SweetAlertPO(IBrowser browser) {
        this.browser = browser;
    }

    public boolean isPresent() {
        return !browser.find().cssList(CONFIRM_BUTTON_CSS).isEmpty();
    }

    public String getTitle() {
        return browser.find().css(TITLE_CSS).getText();
    }

    public String getBody() {
        return browser.find().css(BODY_CSS).getText();
    }

    public void confirm() {
        // SweetAlert2 puede reemplazar el diálogo (por ejemplo, la confirmación
        // "¿Eliminar usuario?" da paso al aviso de éxito) justo mientras se ubica
        // el botón, dejando la referencia obtenida obsoleta. Un reintento alcanza.
        try {
            browser.find().css(CONFIRM_BUTTON_CSS).click();
        } catch (StaleElementReferenceException e) {
            browser.find().css(CONFIRM_BUTTON_CSS).click();
        }
    }
}
