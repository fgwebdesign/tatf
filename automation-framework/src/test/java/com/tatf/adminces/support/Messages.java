package com.tatf.adminces.support;

/**
 * Textos esperados de la aplicación, separados del código en {@code mensajes.properties}.
 */
public final class Messages {
    private static final PropertiesFile FILE = new PropertiesFile("mensajes.properties");

    private Messages() {
    }

    public static String get(String key) {
        return FILE.get(key);
    }
}
