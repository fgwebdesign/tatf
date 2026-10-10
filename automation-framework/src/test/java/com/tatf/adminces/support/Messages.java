package com.tatf.adminces.support;

import com.tatf.core.util.ConfigReader;

/**
 * Textos esperados de la aplicación, separados del código en {@code mensajes.properties}.
 */
public final class Messages {
    private static final ConfigReader FILE = new ConfigReader("mensajes.properties");

    private Messages() {
    }

    public static String get(String key) {
        return FILE.asString(key);
    }
}
