package com.tatf.adminces.modules.viewusers.task;

import com.tatf.adminces.modules.accesos.task.AccesosTask;
import com.tatf.adminces.modules.sweetalert.task.SweetAlertTask;
import com.tatf.adminces.modules.viewusers.data.ViewUsersData;
import com.tatf.adminces.modules.viewusers.pom.ViewUsersPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class ViewUsersTask {
    private final IBrowser browser;
    private final ViewUsersPO viewUsers;

    public ViewUsersTask(IBrowser browser) {
        this.browser = browser;
        this.viewUsers = new ViewUsersPO(browser);
    }

    public void verifyUserListedWithProfile(String email, String expectedProfile) {
        IVerify.create().verifyTrue(viewUsers.isUserListed(email),
                "El usuario debería aparecer en Ver usuarios.");
        IVerify.create().verify(expectedProfile, viewUsers.getProfile(email),
                "El perfil del usuario no es el esperado.");
    }

    public void verifyUserNotListed(String email) {
        IVerify.create().verifyFalse(viewUsers.isUserListed(email),
                "El usuario eliminado no debería aparecer en Ver usuarios.");
    }

    /**
     * Elimina al usuario indicado, verificando tanto el diálogo de confirmación
     * ("¿Eliminar usuario?") como el de éxito posterior.
     */
    public void deleteUserAndVerify(String email) {
        viewUsers.clickDelete(email);
        SweetAlertTask sweetAlert = new SweetAlertTask(browser);
        sweetAlert.verifyMessageAndConfirm(ViewUsersData.DELETE_CONFIRM_TITLE, ViewUsersData.deleteConfirmBody(email));
        sweetAlert.verifyMessageAndConfirm(ViewUsersData.DELETE_SUCCESS_TITLE, ViewUsersData.DELETE_SUCCESS_BODY);
    }

    /**
     * Vuelve a entrar a Ver usuarios desde el menú lateral, para refrescar el listado.
     */
    public void refresh() {
        new AccesosTask(browser).goToViewUsers();
    }
}
