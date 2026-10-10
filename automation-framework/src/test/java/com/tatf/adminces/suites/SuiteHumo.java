package com.tatf.adminces.suites;

import org.junit.platform.suite.api.*;

/**
 * Pruebas mínimas que confirman que las funciones principales de alta de cuentas funcionan.
 */
@Suite
@SuiteDisplayName("AdminCES - Suite de humo")
@SelectPackages("com.tatf.adminces.modules")
@IncludeTags("humo")
public class SuiteHumo {
}
