package bs23.com.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    public static void scrollView(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).
                executeScript(
                        "arguments[0].scrollIntoView(true)", element);
    }
}
