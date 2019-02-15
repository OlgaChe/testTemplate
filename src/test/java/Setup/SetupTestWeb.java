package Setup;

import Driver.MainMethods;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class SetupTestWeb extends MainMethods {


    String operationSystem = System.getProperty("os.name").toLowerCase();

    File dir = new File("src");

    File driverChromeWin = new File(dir, "chromedriver.exe");
    File driverChromeLinux = new File(dir, "chromedriverLinux64");
    File driverChromeMac = new File(dir, "chromedriverMac");

    File driverFFWin = new File(dir, "geckodriver.exe");
    File driverFFLinux = new File(dir, "geckodriverLinux64");
    File driverFFMac = new File(dir, "geckodriverMac");

    File driverIE = new File(dir, "IEDriverServer.exe");


    @BeforeSuite(groups = {"Chrome"})                       // РАЗОБРАТЬСЯ - ЗАПУСК ДЛЯ ВСЕХ ГРУП
    @Parameters("baseUrl")
    public void DriverChrome(String baseUrl) throws IOException {
        System.out.println(("[TEST STARTED]"));
        System.out.println(("OS: "+ operationSystem));
        if (operationSystem.contains("win")){
            System.setProperty("webdriver.chrome.driver", String.valueOf(driverChromeWin));
        }else if (operationSystem.contains("nux") || operationSystem.contains("nix")) {
            System.setProperty("webdriver.chrome.driver", String.valueOf(driverChromeLinux));
        }else if (operationSystem.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", String.valueOf(driverChromeMac));
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);

        driver.navigate().to(getProperty(baseUrl));

        System.out.println("Created by Qovtunov and OlgaChe");
    }

    @BeforeTest(groups = "FireFox")
    @Parameters ("baseUrl")
    public void  DriverFF(String baseUrl) throws InterruptedException {
        System.out.println(("[TEST STARTED]"));
        System.out.println(("OS: "+ operationSystem));
        if (operationSystem.contains("win")){
            System.setProperty("webdriver.gecko.driver",String.valueOf(driverFFWin));
        }else if (operationSystem.contains("nux") || operationSystem.contains("nix")) {
            System.setProperty("webdriver.gecko.driver",String.valueOf(driverFFLinux));
        }else if (operationSystem.contains("mac")) {
            System.setProperty("webdriver.gecko.driver",String.valueOf(driverFFMac));
        }

        FirefoxOptions options = new FirefoxOptions().setLegacy(false);
        driver = new FirefoxDriver(options);
        driver = new FirefoxDriver();

        driver.navigate().to(baseUrl);
    }


    @AfterSuite(groups = {"Chrome"})
    public void tearDown() throws InterruptedException {
        //driver.quit();
        System.out.println(("[TEST FINISHED]" + "\n"));
    }


}


