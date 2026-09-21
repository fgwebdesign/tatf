package com.tatf.pages.adminces;

/**
 * Perfiles de Tester disponibles al dar de alta un usuario en {@code /adminces/create-user}.
 */
public enum TesterProfile {
    JUNIOR("testerJunior", "Tester Junior"),
    SENIOR("testerSenior", "Tester Senior"),
    LIDER("testerLead", "Tester Líder");

    private final String radioId;
    private final String displayLabel;

    TesterProfile(String radioId, String displayLabel) {
        this.radioId = radioId;
        this.displayLabel = displayLabel;
    }

    /**
     * Id del radio button correspondiente en el formulario de alta.
     */
    public String radioId() {
        return radioId;
    }

    /**
     * Texto del perfil tal cual se muestra en la columna "Perfil" de Ver usuarios.
     */
    public String displayLabel() {
        return displayLabel;
    }
}
