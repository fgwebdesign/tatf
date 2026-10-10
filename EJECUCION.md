# Ejecución de suites y reporte

Todos los comandos se corren desde `automation-framework/` con el archivo `.env` configurado
(ver `.env.example`).

## Suites (`src/test/java/com/tatf/adminces/suites`)

| Suite | Qué agrupa |
|-------|-----------|
| `SuiteCompleta` | Todas las pruebas de AdminCES (regresión) |
| `SuiteHumo` | Pruebas con tag `humo`: crear cuenta Administrador y crear cuenta Tester |
| `SuiteCuentasAdministrador` | Tag `administrador`: crear cuenta Administrador y reiniciar contraseña |
| `SuiteCuentasTester` | Tag `tester`: crear y eliminar cuenta Tester |

## Navegadores

El navegador se elige con `browserfactory.driver_type` (`CHROME` por defecto, ver
`src/main/resources/config.properties`) y se puede cambiar por línea de comandos:

```
mvn test -Dtest=SuiteCompleta -Dbrowserfactory.driver_type=CHROME
mvn test -Dtest=SuiteCompleta -Dbrowserfactory.driver_type=FIREFOX
```

## Reporte (Allure)

```
mvn allure:report      # genera target/site/allure-maven-plugin
mvn allure:serve       # abre el reporte en el navegador
```
