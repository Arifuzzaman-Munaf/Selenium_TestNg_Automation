package bs23.com.base;

import bs23.com.factory.DriverSelection;
import bs23.com.utilities.CookieUtils;
import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {


    private
    ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    @Step
    protected WebDriver getDriver() {
        return this.driver.get();
    }

    protected void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public void injectCookies(Cookies cookies){
        List<Cookie> seleniumCookies = new CookieUtils().convertedCookies(cookies);
        for (Cookie cookie : seleniumCookies){
            getDriver().
            manage().
            addCookie(cookie);
        }
    }


//    initializing the driver
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public synchronized void setDriver(@Optional String browser){

        browser = (browser == null) ? "chrome":browser;
//        String browser = System.getProperty("browser",browser);

        setDriver(
                DriverSelection.
                selectDriver(browser).
                createDriver()
        );
    }


//    terminating the session
    @Parameters("browser")
    @AfterMethod
    public synchronized void tearDown(
            @Optional String browser, ITestResult iTestResult
        ) throws IOException

    {
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            File destFile =
                    new File(
                   "src"+ File.separator +"screenShot" +
                           File.separator + browser + File.separator +
                           iTestResult.getTestClass().getRealClass().
                           getSimpleName() + "_" + iTestResult.
                           getMethod().getMethodName() + ".png"
                    );

            takeScreenShot(destFile);
        }
        getDriver().quit();
    }

    private void takeScreenShot(File destFile) throws IOException {
        File srcFile = ((TakesScreenshot) getDriver()).
                       getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(srcFile, destFile);
    }
}
