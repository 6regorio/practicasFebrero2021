package android;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

  private String dni;
  private String password;

  private AndroidDriver<WebElement> driver = null;
  private DesiredCapabilities dc;

  @BeforeSuite
  /**
   * Carga todo lo necesario para conectarse con el emulador de Android
   * 
   * @throws MalformedURLException
   */
  public void setUp() throws MalformedURLException {

    dc = new DesiredCapabilities();
    dc.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
    dc.setCapability("name", "Pixel 4 API 24");


    driver = new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), dc);
    driver.setLogLevel(Level.INFO);

    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    pedirDniYPassword();

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

    driver.getContextHandles();
    driver.context("WEBVIEW_chrome");

    List<WebElement> botonesAcceder = driver.findElements(By.partialLinkText("Acce"));
    botonesAcceder.get(1).click();

    driver.getContextHandles();
    driver.context("WEBVIEW_chrome");

    driver.findElement(By.xpath("//*[contains(@name,'id_owner')]")).sendKeys(dni);
    driver.findElement(By.xpath("//*[contains(@name,'pin')]")).sendKeys(password);
    driver.findElement(By.xpath("//*[contains(@id,'btnFirmar')]")).click();
    
    driver.getContextHandles();
    driver.context("NATIVE_APP");
    
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions
        .visibilityOfElementLocated(By.xpath("//*[contains(@text,'GREGORIO')]")));
    Assert
        .assertTrue(driver.findElement(By.xpath("//*[contains(@text,'GREGORIO')]")).isDisplayed());
  }

  @Test(priority = 2)
  public void carpetaSaludAlergia() {
    driver.findElement(By.xpath("//*[@text='Carpeta de Salud']")).click();
    driver.findElement(By.xpath("//*[@text='ALERGIAS']")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//*[@text='METALES']")).isDisplayed());

  }

  @Test(priority = 2)
  public void carpetaSaludVacunas() {
    driver.findElement(By.xpath("//*[@text='Carpeta de Salud']")).click();
    driver.findElement(By.xpath("//*[@text='VACUNAS']")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//*[@text='Vacunas']")).isDisplayed());
  }


  @AfterTest
  public void tearDown() {
    // driver.findElement(By.xpath("//*[@text='Inicio']")).click();
    // driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
    driver.quit();
  }



  /**
   * Muestra una ventana que pide un DNI y una clave permanenente para la autenticación en el
   * sistema
   */
  private void pedirDniYPassword() {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel(new BorderLayout(5, 5));
    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
    label.add(new JLabel("DNI (12345678Z)", SwingConstants.RIGHT));
    label.add(new JLabel("Clave permanente", SwingConstants.RIGHT));
    panel.add(label, BorderLayout.WEST);
    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
    JTextField txtDni = new JTextField();
    controls.add(txtDni);
    JPasswordField txtPassword = new JPasswordField();
    controls.add(txtPassword);
    panel.add(controls, BorderLayout.CENTER);
    JOptionPane.showMessageDialog(frame, panel, "Iniciar sesión", JOptionPane.QUESTION_MESSAGE);
    dni = txtDni.getText();
    password = "";
    for (char caracter : txtPassword.getPassword()) {
      password += caracter;
    }
    if (dni.isEmpty() || (password.isEmpty())) {
      System.out.println("Cancelado por el usuario");
      System.exit(1);
    }
  }
}
