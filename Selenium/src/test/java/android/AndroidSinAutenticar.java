package android;

import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;


@Test
public class AndroidSinAutenticar {

  protected AndroidDriver<WebElement> driver = null;

  @BeforeClass
  /**
   * Carga todo lo necesario para conectarse con el emulador de Android
   * 
   * @throws MalformedURLException
   */
  public void setUp() throws MalformedURLException {
    driver = AndroidConAutenticacion.driver;

    driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));

    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='MyApp']")));
    driver.findElement(By.xpath("//*[@text='MyApp']")).click();
  }

  /**
   * Pulsa en el botón Inicio de la aplicación por si estábamos en otro lado
   */
  @BeforeMethod
  public void pulsarTeclaInicio() {
    driver.findElement(By.xpath("//*[@text='Inicio']")).click();
  }

  /**
   * Comprueba entrar en cita previa de atención primaria
   * 
   */
  @Test(priority = 5, groups = "Comprobaciones sin iniciar sesión")
  public void paginaInicioCitaPreviaAtencionPrimaria() {
    driver.findElement(By.xpath("//*[@text='PEDIR CITA']")).click();
    driver.findElement(By.xpath("//*[@text='CITA ATENCIÓN PRIMARIA']")).click();
    Assert.assertTrue(
        driver.findElement(By.xpath("//*[@resource-id='tarjetaportada']")).isDisplayed());
    driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
  }

  /**
   * Comprueba entrar en cita previa de atención hospitalaria
   * 
   */
  @Test(priority = 5, groups = "Comprobaciones sin iniciar sesión")
  public void paginaInicioCitaPreviaAtencionHospitalaria() {
    driver.findElement(By.xpath("//*[@text='PEDIR CITA']")).click();
    driver.findElement(By.xpath("//*[@text='CITA ATENCIÓN HOSPITALARIA']")).click();
    Assert.assertEquals(driver.findElement(By.xpath("//*[@resource-id='ot1']")).getText(),
        "Bienvenido a Citación Especializada");
    driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
  }



  @Test(priority = 4, groups = "Comprobaciones sin iniciar sesión")
  public void encuentraFarmacia() {

    // Esperar a que cargue completa
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='ENCONTRAR FARMACIA']")));

    driver.findElement(By.xpath("//*[@text='ENCONTRAR FARMACIA']")).click();

    driver.findElement(By.xpath("//*[@text='Provincia']")).click();
    driver.findElement(By.xpath("//*[@text='ALBACETE']")).click();
    driver.findElement(By.xpath("//*[@text='OK']")).click();

    driver.findElement(By.xpath("//*[@text='Localidad']")).click();
    driver.findElement(By.xpath("//*[@text='ABEJUELA']")).click();
    driver.findElement(By.xpath("//*[@text='OK']")).click();

    driver.findElement(By.xpath("//*[@text='Fecha']")).click();

    driver.findElement(By.xpath("//*[@text='09']")).click();
    // driver.findElement(By.xpath("//*[@text='03']")).click();
    driver.findElement(By.xpath("//*[@text='2020']")).click();
    driver.findElement(By.xpath("//*[@text='OK']")).click();

    driver.findElement(By.xpath("//*[@text='FARMACIAS']")).click();

    Assert.assertTrue(
        driver.findElement(By.xpath("//*[@text='FERNANDO LUIS ORTUÑO TOMAS']")).isDisplayed());
    driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
  }



  /**
   * Comprueba el popup del teléfono en la página principal
   */
  @Test(priority = 2, groups = "Comprobaciones sin iniciar sesión")
  public void comprobarClickTelefono() {
    driver.findElement(By.xpath("//*[contains(@text,'TELÉFONOS DE ATENCIÓN')]")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//*[@text='900 12 21 12']")).isDisplayed(),
        "La pantalla es incorrecta");
    driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
  }



  /**
   * Comprueba el enlace del coronavirus en la página principal
   */
  @Test(priority = 1, groups = "Comprobaciones sin iniciar sesión")
  public void comprobarEnlaceCoronavirus() {
    driver.findElement(By.xpath("//*[contains(@text,'ASISTENTE VIRTUAL')]")).click();
    driver.findElement(By.xpath("//*[contains(@text,'SÍ')]")).click();
    Assert.assertTrue(
        driver.findElement(By.xpath("//*[contains(@text,'Asistencia COVID-19')]")).isDisplayed());
    // Esperar a que cargue completa
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions
        .visibilityOfElementLocated(By.xpath("//*[contains(@text,'ABRIR EN LA APP')]")));
    driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
  }

  /**
   * Comprueba pulsar más y entrar en privacidad
   */
  @Test(priority = 3, groups = "Comprobaciones sin iniciar sesión")
  public void masPrivacidad() {
    driver.findElement(By.xpath("//*[@text='menu']")).click();
    driver.findElement(By.xpath("//*[contains(@text,'Privacidad')]")).click();
    Assert.assertTrue(
        driver.findElement(By.xpath("//*[contains(@text,'Privacidad')]")).isDisplayed());
    driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
  }

  /**
   * Comprueba pulsar más y entrar en ayuda
   */
  @Test(priority = 3, groups = "Comprobaciones sin iniciar sesión")
  public void masAyuda() {
    driver.findElement(By.xpath("//*[@text='menu']")).click();
    driver.findElement(By.xpath("//*[@text='Ayuda']")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@text,'Ayuda')]")).isDisplayed());
    driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
  }

  /**
   * Comprueba pulsar más y entrar en notificaciones
   */
  @Test(priority = 3, groups = "Comprobaciones sin iniciar sesión")
  public void mostarNotificaciones() {
    driver.findElement(By.xpath("//*[@text='menu']")).click();
    driver.findElement(By.xpath("//*[@text='Notificaciones']")).click();
    Assert.assertTrue(
        driver.findElement(By.xpath("//*[contains(@text,'Notificaciones')]")).isDisplayed());
    driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
    driver.findElement(By.xpath("//*[@text='MyApp']")).click();
    driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
  }

  /**
   * Comprueba pulsar más y entrar en contacto
   */
  @Test(priority = 4, groups = "Comprobaciones sin iniciar sesión")
  public void masContacto() {
    driver.findElement(By.xpath("//*[@text='menu']")).click();
    driver.findElement(By.xpath("//*[@text='Contacto']")).click();
    driver.findElement(By.xpath("//*[contains(@text,'Chrome')]")).click();
    driver.findElement(By.xpath("//*[@resource-id='android:id/button_once']")).click();
    Assert.assertEquals(
        driver.findElement(By.xpath("//*[@resource-id='com.android.chrome:id/url_bar']")).getText(),
        "sanidad.castillalamancha.es/ciudadanos/reclamaciones");
    driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
  }
}
