package docker;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Conjunto de métodos y pruebas que chequean la parte pública de la aplicación web. Se tiene la
 * opción de probar la búsqueda de farmacias con un DataProvider para ejecutar distintas pruebas en
 * la misma ejecución.
 */
public class SinAutenticarOpera {
  /**
   * Será el controlador de Chrome/Firefox/Opera
   */
  public static WebDriver currentDriver;


  /**
   * Se ejecuta antes de comenzar las pruebas de esta clase
   */
  @BeforeClass
  public void cargaPropiedadesMásIdentificarSiNoSeHaHecho() {
    currentDriver = AutentificacionRequeridaFunciones.driverO;
  }

  /**
   * Se ejecuta antes de comenzar cada una de las pruebas
   */
  @BeforeMethod
  public void cargarPaginaInicial() {
    if (currentDriver != null) {
      currentDriver.get("https://sescampre.jccm.es/portalsalud/app/inicio");
    }
  }

  /**
   * Ejecuta la prueba
   */
  @Test(groups = "Comprobaciones sin iniciar sesión",
      dependsOnMethods = "Opera_comprobarEnlaceCoronavirus")
  public void Opera_comprobarClickTelefono() {
    FuncionesSinAutenticar.comprobarClickTelefono(currentDriver);
  }

  /**
   * Ejecuta la prueba
   */
  @Test(groups = "Comprobaciones sin iniciar sesión")
  public void Opera_comprobarEnlaceCoronavirus() {
    if (currentDriver == null)
      throw new SkipException("Sin pruebas con Firefox");
    FuncionesSinAutenticar.comprobarEnlaceCoronavirus(currentDriver);
  }

  /**
   * Ejecuta la prueba
   */
  @Test(groups = "Comprobaciones sin iniciar sesión",
      dependsOnMethods = "Opera_comprobarEnlaceCoronavirus")
  public void Opera_masPrivacidad() {
    FuncionesSinAutenticar.masPrivacidad(currentDriver);
  }

  /**
   * Ejecuta la prueba
   */
  @Test(groups = "Comprobaciones sin iniciar sesión",
      dependsOnMethods = "Opera_comprobarEnlaceCoronavirus")
  public void Opera_masContacto() {
    FuncionesSinAutenticar.masContacto(currentDriver);
  }

  /**
   * Ejecuta la prueba
   */
  @Test(groups = "Comprobaciones sin iniciar sesión",
      dependsOnMethods = "Opera_comprobarEnlaceCoronavirus")
  public void Opera_masAyuda() {
    FuncionesSinAutenticar.masAyuda(currentDriver);
  }

  /**
   * Ejecuta la prueba
   */
  @Test(groups = "Comprobaciones sin iniciar sesión",
      dependsOnMethods = "Opera_comprobarEnlaceCoronavirus")
  public void Opera_mostarNotificaciones() {
    FuncionesSinAutenticar.mostarNotificaciones(currentDriver);
  }

  /**
   * Ejecuta la prueba
   */
  @Test(groups = "Comprobaciones sin iniciar sesión",
      dependsOnMethods = "Opera_comprobarEnlaceCoronavirus")
  public void Opera_paginaInicioCitaPreviaAtencionHospitalaria() {
    FuncionesSinAutenticar.paginaInicioCitaPreviaAtencionHospitalaria(currentDriver);
  }

  /**
   * Ejecuta la prueba
   */
  @Test(groups = "Comprobaciones sin iniciar sesión",
      dependsOnMethods = "Opera_comprobarEnlaceCoronavirus")
  public void Opera_paginaInicioCitaPreviaAtencionPrimaria() {
    FuncionesSinAutenticar.paginaInicioCitaPreviaAtencionPrimaria(currentDriver);
  }

  /**
   * Ejecuta la prueba
   */
  @Test(groups = "Búsqueda de farmacias", dataProvider = "EncoFarmProvider",
      dataProviderClass = FuncionesSinAutenticar.class,
      dependsOnMethods = "Opera_comprobarEnlaceCoronavirus")
  public void Opera_encuentraFarmaciaParametros(String provincia, String pueblo, String year,
      String mes, String dia) {
    FuncionesSinAutenticar.encuentraFarmaciaParametros(provincia, pueblo, year, mes, dia,
        currentDriver);
  }
}
