#!/usr/bin/env python3
"""Genera informe/informe.html (y el PDF con Chrome headless) a partir de los reportes
de surefire de cada navegador. Uso: generar_informe.py <dir_chrome> <dir_firefox> <salida.html>"""
import sys, re, html, xml.etree.ElementTree as ET, datetime

SUITES = {
 "Crear cuenta Administrador": ("CrearCuentaAdministradorTest", ["nuevo.admin", "otro.admin"]),
 "Reiniciar contraseña": ("ReiniciarContrasenaTest", ["reset.password", "reset.password.dos"]),
 "Crear cuenta Tester": ("CrearCuentaTesterTest", ["Junior", "Senior", "Líder"]),
 "Eliminar cuenta Tester": ("EliminarCuentaTesterTest", ["Senior", "Junior"]),
}
CASOS = {
 "CrearCuentaAdministradorTest": ["Administrador (Uruguay)", "Administradora (Argentina)"],
 "ReiniciarContrasenaTest": ["Reset Password", "Reset Password Dos"],
 "CrearCuentaTesterTest": ["Perfil Junior", "Perfil Senior", "Perfil Líder"],
 "EliminarCuentaTesterTest": ["Perfil Senior", "Perfil Junior"],
}
def leer(d):
    t = ET.parse(f"{d}/TEST-com.tatf.adminces.suites.SuiteCompleta.xml").getroot()
    r = {}
    for c in t.iter("testcase"):
        cls = c.get("classname").split(".")[-1]
        idx = int(re.search(r"\[(\d+)\]$", c.get("name")).group(1))
        bad = c.find("failure") is not None or c.find("error") is not None
        r[(cls, idx)] = ("Fallo" if bad else "OK", float(c.get("time")))
    return r, t.find("properties")
def prop(p, k, d="-"):
    for x in p.iter("property"):
        if x.get("name") == k: return x.get("value")
    return d

chrome, pc = leer(sys.argv[1]); ff, pf = leer(sys.argv[2])
tot = lambda r: (sum(v[0]=="OK" for v in r.values()), len(r))
def bar(ok, n, color):
    return f'<div class="bar"><div style="width:{100*ok/n:.0f}%;background:{color}"></div></div>'
filas = ""; ident = 0
for nombre, (cls, _) in SUITES.items():
    filas += f'<h3>Suite: {nombre}</h3><table><tr><th>ID</th><th>Caso de prueba</th><th>Chrome</th><th>Firefox</th><th>Comentarios</th></tr>'
    for i, caso in enumerate(CASOS[cls], 1):
        ident += 1
        a = chrome.get((cls, i), ("-", 0)); b = ff.get((cls, i), ("-", 0))
        cl = lambda s: "ok" if s == "OK" else "ko"
        filas += f'<tr><td>{ident}</td><td>{caso}</td><td class="{cl(a[0])}">{a[0]} ({a[1]:.0f}s)</td><td class="{cl(b[0])}">{b[0]} ({b[1]:.0f}s)</td><td>-</td></tr>'
    filas += "</table>"
co, cn = tot(chrome); fo, fn = tot(ff)
hoy = datetime.date.today().strftime("%d/%m/%Y")
doc = f"""<!doctype html><html lang="es"><meta charset="utf-8"><title>Informe Final</title>
<style>
@page {{ size: A4; margin: 18mm 16mm }}
body {{ font-family: Helvetica, Arial, sans-serif; font-size: 11pt; color:#222 }}
h1 {{ font-size: 26pt; text-align:center; margin: 80px 0 4px }} .sub {{ text-align:center; font-size:15pt }}
.portada {{ page-break-after: always; text-align:center }} h2 {{ color:#1f3a5f; border-bottom:2px solid #1f3a5f; padding-bottom:3px }}
table {{ border-collapse: collapse; width:100%; margin:8px 0 14px }} td,th {{ border:1px solid #999; padding:5px 8px; text-align:left; font-size:10pt }}
th {{ background:#e8edf3 }} .ok {{ color:#14692b; font-weight:bold }} .ko {{ color:#b00020; font-weight:bold }}
.bar {{ background:#eee; height:18px; border-radius:3px; overflow:hidden; margin:3px 0 10px }} .bar div {{ height:100% }}
.pg {{ page-break-before: always }} li {{ margin-bottom:4px }}
</style>
<div class="portada"><h1>INFORME FINAL</h1><div class="sub">Testing Funcional Automatizado</div>
<div class="sub" style="margin-top:40px"><b>AdminCES</b></div>
<div class="sub" style="margin-top:80px">Autor<br><b>Felipe Gutierrez Romero</b></div>
<div class="sub" style="margin-top:30px">Ejecución: {hoy}</div></div>

<h2>Introducción</h2>
<p>Este informe presenta los resultados de la ejecución automatizada de las pruebas funcionales de la aplicación
AdminCES, agrupadas en suites y ejecutadas en dos navegadores (Chrome y Firefox).</p>
<ul>
<li><b>Documento de parametrización:</b> los datos de las pruebas (nombres, países, perfiles, contraseñas) están en archivos
CSV y los textos esperados y la configuración en archivos <i>properties</i>; las credenciales viven en un archivo <i>.env</i>
no versionado. Cada fila de un CSV es una ejecución de la prueba.</li>
<li><b>Verificación/Assert:</b> acción que realiza el script para corroborar si hubo un error funcional (interfaz IVerify).</li>
<li><b>Framework:</b> automation-framework, con Factory y Singleton para el navegador (interfaz IBrowser) y Page Object Model
(paquetes <i>pom</i>, <i>task</i> y <i>data</i> por pantalla).</li>
<li><b>JUnit 5:</b> ejecuta las pruebas y las agrupa en suites mediante etiquetas (<i>@Tag</i>).</li>
<li><b>Allure:</b> registra los resultados de cada ejecución para generar reportes con métricas.</li>
<li><b>Selenium:</b> permite manipular el navegador como lo haría un usuario manual.</li>
<li><b>Drivers (Chrome y Firefox):</b> crean la instancia del navegador usada por Selenium.</li>
</ul>

<h2>Suites ejecutadas</h2>
<ul><li>Suite completa (regresión): las 4 suites siguientes</li><li>Crear cuenta Administrador</li><li>Reiniciar contraseña</li>
<li>Crear cuenta Tester</li><li>Eliminar cuenta Tester</li></ul>
<p>También existen las suites <i>SuiteHumo</i> (pruebas mínimas, etiqueta <i>humo</i>), <i>SuiteCuentasAdministrador</i> y
<i>SuiteCuentasTester</i>, que agrupan por etiqueta.</p>

<h2 class="pg">Datos de ambiente</h2>
<table><tr><th colspan="2">SISTEMA BAJO PRUEBA</th></tr>
<tr><td>Nombre de ambiente</td><td>AdminCES (pruebas)</td></tr><tr><td>URL</td><td>http://cestore.ces.com.uy/adminces</td></tr>
<tr><td>Usuario</td><td>Cuenta compartida del grupo (administrador)</td></tr><tr><td>Fecha del sistema</td><td>{hoy}</td></tr></table>
<table><tr><th colspan="2">PC EN LA QUE SE EJECUTAN PRUEBAS</th></tr>
<tr><td>Sistema operativo</td><td>macOS 26.6.2 (Apple Silicon)</td></tr>
<tr><td>Navegadores web</td><td>Google Chrome 154.0.8037.99 · Mozilla Firefox 157.0.1</td></tr>
<tr><td>IDE</td><td>Claude Code / terminal con Maven</td></tr>
<tr><td>Java</td><td>OpenJDK 26.0.2.1</td></tr><tr><td>Versión JUnit</td><td>6.1.3</td></tr>
<tr><td>Versión Allure</td><td>allure-jupiter 3.0.0</td></tr><tr><td>Versión Selenium</td><td>4.49.0</td></tr>
<tr><td>Versión driver</td><td>ChromeDriver 154.0.8037.92 · geckodriver gestionado por Selenium Manager</td></tr></table>

<h2 class="pg">Resultados de la ejecución</h2>
<p><b>Chrome:</b> {co} de {cn} pruebas OK</p>{bar(co,cn,'#14692b')}
<p><b>Firefox:</b> {fo} de {fn} pruebas OK</p>{bar(fo,fn,'#14692b')}
{filas}
<h2>Gráficas</h2>
<table><tr><th>Navegador</th><th>Total</th><th>OK</th><th>Fallo</th><th>% OK</th></tr>
<tr><td>Chrome</td><td>{cn}</td><td class="ok">{co}</td><td class="ko">{cn-co}</td><td>{100*co/cn:.0f}%</td></tr>
<tr><td>Firefox</td><td>{fn}</td><td class="ok">{fo}</td><td class="ko">{fn-fo}</td><td>{100*fo/fn:.0f}%</td></tr></table>
<p>Observación: durante la preparación se corrigió el dato esperado del perfil "Tester Lider", que la aplicación muestra sin tilde;
fue un error del dato de prueba y no de la aplicación.</p>
</html>"""
open(sys.argv[3], "w").write(doc)
print("Chrome", co, "/", cn, "Firefox", fo, "/", fn)
