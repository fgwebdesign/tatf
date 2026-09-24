package com.tatf.adminces.support;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Resuelve la configuración de las pruebas de AdminCES.
 * <p>
 * Orden de resolución para cada clave: propiedad del sistema ({@code -Dclave=valor})
 * → variable de entorno → archivo {@code .env} (no versionado) → valor por defecto.
 */
public final class AdminCesConfig {
    private static final Dotenv DOTENV = Dotenv.configure().ignoreIfMissing().load();

    private AdminCesConfig() {
    }

    public static String baseUrl() {
        return get("ADMINCES_BASE_URL", "http://cestore.ces.com.uy/adminces");
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

    private static String get(String key, String defaultValue) {
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

        if (defaultValue == null) {
            throw new IllegalStateException("Falta configurar la clave " + key
                    + " (propiedad de sistema, variable de entorno o archivo .env)");
        }
        return defaultValue;
    }
}
