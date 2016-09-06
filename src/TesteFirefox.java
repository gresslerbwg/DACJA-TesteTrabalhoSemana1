
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TesteFirefox {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  // aqui importa o driver externo
	  System.setProperty("webdriver.gecko.driver","/home/gressler/Downloads/selenium-java-3.0.0-beta2/geckodriver");
	  DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	  capabilities.setCapability("marionette", true);
	  driver = new FirefoxDriver(capabilities);

	  baseUrl = "http://localhost:8080/";
	  driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
  }

  @Test
  public void testE1() throws Exception {
    driver.get(baseUrl + "/DACJA-TrabalhoSemana1/");
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("document.getElementById('tipoConversao').selectedIndex = 1;");
    driver.findElement(By.name("temperatura")).clear();
    driver.findElement(By.name("temperatura")).sendKeys("100");
    driver.findElement(By.name("converter")).click();
    assertEquals("100,0°C equivalem a 212,0°F", driver.findElement(By.cssSelector("h1")).getText());
  }
  
  @Test
  public void testE2() throws Exception {
    driver.get(baseUrl + "/DACJA-TrabalhoSemana1/");
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("document.getElementById('tipoConversao').selectedIndex = 2;");
    driver.findElement(By.name("temperatura")).clear();
    driver.findElement(By.name("temperatura")).sendKeys("212");
    driver.findElement(By.name("converter")).click();
    assertEquals("212,0°F equivalem a 100,0°C", driver.findElement(By.cssSelector("h1")).getText());
  }
  
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}