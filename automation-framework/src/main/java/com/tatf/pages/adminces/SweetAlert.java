package com.tatf.pages.adminces;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;

import java.util.List;

/**
 * AdminCES confirma cada acción (login, alta, reinicio de contraseña, eliminación)
 * con un diálogo de SweetAlert2. El botón de confirmación de esos diálogos siempre
 * tiene la clase {@code swal2-confirm} (sea su texto "OK", "Sí", etc.), por lo que
 * alcanza con esa única clase para cerrarlos.
 */
final class SweetAlert {
    private static final String CONFIRM_BUTTON_CSS = ".swal2-confirm";

    private SweetAlert() {
    }

    /**
     * Si hay un diálogo de SweetAlert2 visible, lo confirma. No falla si no hay ninguno.
     */
    static void confirmIfPresent(IBrowser browser) {
        browser.wait(1).sleep();
        List<Element> confirmButtons = browser.find().cssList(CONFIRM_BUTTON_CSS);
        if (!confirmButtons.isEmpty()) {
            confirmButtons.get(0).click();
        }
    }
}
