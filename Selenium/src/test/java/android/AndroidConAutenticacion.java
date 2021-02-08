package android;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;


@Test
public class AndroidConAutenticacion {

  private String reportDirectory = "reports";
  private String reportFormat = "xml";
  private String testName = "Untitled";
  protected AndroidDriver<WebElement> driver = null;
  DesiredCapabilities dc;

  @BeforeSuite
  /**
   * Carga todo lo necesario para conectarse con el emulador de Android
   * 
   * @throws MalformedURLException
   */
  public void setUp() throws MalformedURLException {

    dc = new DesiredCapabilities();
    dc.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
    dc.setCapability("reportDirectory", reportDirectory);
    dc.setCapability("reportFormat", reportFormat);
    dc.setCapability("testName", testName);
    dc.setCapability("name", "Pixel 4 API 24");

    driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
    driver.setLogLevel(Level.INFO);

    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
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
   * Arranca la prueba. Inicio de sesión
   */
  @Test(priority = 1, groups = "Inicia sesión")
  public void Chrome_realizarAutenticacion() {    
    
    driver.findElement(By.xpath("//*[contains(@text,'IDENTIFICARME')]")).click();
    driver.findElement(By.xpath("//*[contains(@text,'ACEPTAR')]")).click();

    Set<String> contextNames = driver.getContextHandles();
    for (String context : contextNames) {
      System.out.println(context);
    }
    System.out.println("Estamos en contexto " + driver.getContext());
    driver.context("WEBVIEW_chrome");
    
    List<WebElement> botonesAcceder = driver.findElements(By.partialLinkText("Acce"));
    botonesAcceder.get(1).click();         
    
    Set<String> contextNames2 = driver.getContextHandles();
    for (String context : contextNames2) {
      System.out.println(context);
    }
    System.out.println("Estamos en contexto " + driver.getContext());
    
    Set<String> contextNames4 = driver.getContextHandles();
    for (String context : contextNames4) {
      System.out.println(context);
    }
    System.out.println("Estamos en contexto " + driver.getContext());

//    NATIVE_APP
//    WEBVIEW_com.sescam.app
//    WEBVIEW_chrome
    
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'DNI')]")));   
    
    Set<String> contextNames3 = driver.getContextHandles();
    for (String context : contextNames3) {
      System.out.println(context);
    }
    System.out.println("Estamos en contexto " + driver.getContext());
    
    driver.findElement(By.xpath("//*[contains(@text,'DNI')]")).sendKeys("fdsfsd");
    driver.findElement(By.xpath("//*[contains(@text,'Contra')]")).sendKeys("fdsfsd");
    driver.findElement(By.xpath("//*[contains(@text,'Contra')]")).submit();
    

//    driver.findElement(By.xpath("//*[@text='PEDIR CITA']")).click();
//    driver.findElement(By.xpath("//*[@text='CITA ATENCIÓN PRIMARIA']")).click();
//    Assert.assertTrue(
//        driver.findElement(By.xpath("//*[@resource-id='tarjetaportada']")).isDisplayed());
//    driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));

    /*
     * 
     * Set<String> contextNames = driver.getContextHandles();

     * 
     * driver.context("WEBVIEW_chrome"); Assert.assertTrue(
     * driver.findElement(By.xpath("//span[contains(.,'Consejería de Sanidad')]")).isDisplayed());
     * driver.context("NATIVE_APP");
     */
//    driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));

  }


  @AfterTest
  public void tearDown() {
//    driver.findElement(By.xpath("//*[@text='Inicio']")).click();
//    driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
    driver.quit();
  }
}
