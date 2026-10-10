package com.tatf.adminces.support;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Resuelve la configuración de las pruebas de AdminCES.
 * <p>
 * Orden de resolución para cada clave: propiedad del sistema ({@code -Dclave=valor})
 * → variable de entorno → archivo {@code .env} (no versionado) → {@code config.properties}
 * (valores no secretos versionados). Si no se encuentra, falla indicando la clave.
 */
public final class AdminCesConfig {
    private static final Dotenv DOTENV = Dotenv.configure().ignoreIfMissing().load();
    private static final PropertiesFile FILE = new PropertiesFile("config.properties");

    private AdminCesConfig() {
    }

    public static String baseUrl() {
        return get("ADMINCES_BASE_URL", "adminces.base_url");
    }

    public static String sitePassword() {
        return get("ADMINCES_SITE_PASSWORD", null);
    }

    public static String adminEmail() {
        return get("ADMINCES_ADMIN_EMAIL", null);
    }

    public static String adminPassword() {
        return get("ADMINCES_ADMIN_PASSWORD", null);
    }

    public static String emailDomain() {
        return get("TESTDATA_EMAIL_DOMAIN", "testdata.email_domain");
    }

    private static String get(String key, String propertiesKey) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null && !systemProperty.isBlank()) {
            return systemProperty;
        }

        String environmentVariable = System.getenv(key);
        if (environmentVariable != null && !environmentVariable.isBlank()) {
            return environmentVariable;
        }

        String dotenvValue = DOTENV.get(key);
        if (dotenvValue != null && !dotenvValue.isBlank()) {
            return dotenvValue;
        }

        String fileValue = propertiesKey == null ? null : FILE.getOrNull(propertiesKey);
        if (fileValue != null && !fileValue.isBlank()) {
            return fileValue;
        }

        throw new IllegalStateException("Falta configurar la clave " + key
                + " (propiedad de sistema, variable de entorno, archivo .env o config.properties)");
    }
}
