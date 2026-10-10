package com.tatf.adminces.suites;

import org.junit.platform.suite.api.*;

/**
 * Todas las pruebas de AdminCES (regresión completa).
 */
@Suite
@SuiteDisplayName("AdminCES - Suite completa")
@SelectPackages("com.tatf.adminces.modules")
public class SuiteCompleta {
}
