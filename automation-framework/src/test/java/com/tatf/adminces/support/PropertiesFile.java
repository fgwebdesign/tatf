package com.tatf.adminces.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Carga un archivo {@code .properties} del classpath de pruebas (leído como UTF-8,
 * para soportar tildes y signos de apertura como "¿").
 */
public final class PropertiesFile {
    private final String resource;
    private final Properties properties = new Properties();

    public PropertiesFile(String resource) {
        this.resource = resource;
        try (InputStream stream = PropertiesFile.class.getClassLoader().getResourceAsStream(resource)) {
            if (stream == null) {
                throw new IllegalStateException("No se encontró el archivo " + resource + " en el classpath");
            }
            properties.load(new InputStreamReader(stream, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer " + resource, e);
        }
    }

    public String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalStateException("Falta la clave " + key + " en " + resource);
        }
        return value;
    }

    public String getOrNull(String key) {
        return properties.getProperty(key);
    }
}
