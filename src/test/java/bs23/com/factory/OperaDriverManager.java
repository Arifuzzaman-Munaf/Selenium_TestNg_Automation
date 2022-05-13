package bs23.com.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

public class OperaDriverManager implements DriverManager {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.operadriver().setup();
        WebDriver driver = new OperaDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
