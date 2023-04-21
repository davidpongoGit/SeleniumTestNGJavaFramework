package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    public static WebDriver driver;

    @BeforeMethod
    @Parameters(value={"browserName"})
    public void  beforeMethodMethod(String browserName, Method testMethod){
        logger = extent.createTest(testMethod.getName());
        setupDriver(browserName);
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @BeforeTest
    public void  beforeTestMethod(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Test Automation Report");
        htmlReporter.config().setReportName("Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("QA Automation Engineer", "David Pongo");
    }
    @AfterMethod
    public void  afterMethodMethod(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS){
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
;        }
        else if(result.getStatus()==ITestResult.FAILURE){
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Failed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        }
        driver.quit();
    }
    @AfterTest
    public void  afterTestMethod(){
        extent.flush();
    }

    public void setupDriver(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.http.factory", "jdk-http-client");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.http.factory", "jdk-http-client");
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "gecko.exe");
            driver = new FirefoxDriver();
            
        } else{
            System.setProperty("webdriver.http.factory", "jdk-http-client");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
        }
    }
}
