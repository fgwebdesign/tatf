package com.tatf.adminces.suites;

import org.junit.platform.suite.api.*;

/**
 * Escenarios de cuentas de Administrador: crear cuenta y reiniciar contraseña.
 */
@Suite
@SuiteDisplayName("AdminCES - Cuentas de Administrador")
@SelectPackages("com.tatf.adminces.modules")
@IncludeTags("administrador")
public class SuiteCuentasAdministrador {
}
