# Análisis de parametrización – AdminCES

Datos de las pruebas de AdminCES (crear cuenta Administrador, reiniciar contraseña,
crear cuenta Tester y eliminar cuenta Tester) y dónde quedó cada uno.

| Dato | Tipo | Dónde está ahora |
|------|------|------------------|
| Nombre, apellido, país, contraseñas, perfil de cada cuenta | Valores de prueba | `src/test/resources/datos/*.csv` (`@CsvFileSource`, una ejecución por fila) |
| Prefijo del email de cada cuenta | Valor de prueba | CSV; se le agrega un sufijo único por ejecución (`TestDataFactory`) |
| Dominio de los emails generados | Entorno | `src/test/resources/adminces.properties` |
| URL base de AdminCES | Entorno | `src/test/resources/adminces.properties` |
| Contraseña del sitio, email y contraseña del administrador | Entorno / secreto | `.env` o variables de entorno (no se versionan; ver `.env.example`) |
| Títulos y mensajes de los diálogos, etiqueta de perfil | Resultados esperados | `src/test/resources/mensajes.properties` |
| Navegador, esperas implícita y explícita, resaltado de elementos | Configuración del framework | `src/main/resources/config.properties` (leído con `ConfigReader`) |

Orden de resolución de la configuración de AdminCES: propiedad de sistema (`-Dclave=valor`)
→ variable de entorno → `.env` → `adminces.properties`.

No se parametrizan los localizadores (viven en las clases `PO`, por el patrón POM)
ni los ids de los perfiles de Tester (`TesterProfile`), porque dependen de la interfaz.
