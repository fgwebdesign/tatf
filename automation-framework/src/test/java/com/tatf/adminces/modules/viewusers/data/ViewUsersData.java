package com.tatf.adminces.modules.viewusers.data;

public class ViewUsersData {
    public static final String ADMIN_PROFILE_LABEL = "Administrador";
    public static final String DELETE_CONFIRM_TITLE = "Pregunta!";
    public static final String DELETE_SUCCESS_TITLE = "Correcto!";
    public static final String DELETE_SUCCESS_BODY = "Usuario eliminado.";

    public static String deleteConfirmBody(String email) {
        return "¿Eliminar usuario: " + email + "?";
    }
}
