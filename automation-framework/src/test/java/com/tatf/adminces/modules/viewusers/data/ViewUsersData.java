package com.tatf.adminces.modules.viewusers.data;

import com.tatf.adminces.support.Messages;

public class ViewUsersData {
    public static final String ADMIN_PROFILE_LABEL = Messages.get("viewusers.admin.profile");
    public static final String DELETE_CONFIRM_TITLE = Messages.get("viewusers.delete.confirm.title");
    public static final String DELETE_SUCCESS_TITLE = Messages.get("viewusers.delete.success.title");
    public static final String DELETE_SUCCESS_BODY = Messages.get("viewusers.delete.success.body");

    public static String deleteConfirmBody(String email) {
        return Messages.get("viewusers.delete.confirm.body").replace("{email}", email);
    }
}
