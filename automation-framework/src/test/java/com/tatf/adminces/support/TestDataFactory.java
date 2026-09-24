package com.tatf.adminces.support;

/**
 * Genera datos de prueba únicos por ejecución, para que las pruebas de alta de
 * cuentas se puedan correr repetidas veces sin chocar con emails ya registrados.
 */
public final class TestDataFactory {
    private TestDataFactory() {
    }

    public static String uniqueEmail(String prefix) {
        return prefix + "." + System.currentTimeMillis() + "@tatf-tests.com";
    }
}
