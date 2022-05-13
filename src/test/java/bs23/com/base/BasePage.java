package bs23.com.base;

import bs23.com.utilities.ConfigLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExpectedCondition<WebElement> condition;


//  constructor to initialize the basic stuffs
    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }


//  this method helps to redirect to expected website
    public void loadUrl(String endpoint){
        driver.get(
                ConfigLoader.getInstance().getBaseUrl() + endpoint
        );
    }


    public By getLocator(String name){
        return By.xpath("//li[text()='"+name+"']");
    }

    public Object isClickable(Object object){
        try {
            return wait.until(
                    ExpectedConditions.elementToBeClickable((By) object)
            );
        }
        catch (Exception e){
            return wait.until(
                    ExpectedConditions.elementToBeClickable((WebElement) object)
            );
        }
    }

//  click operation with proper wait and response
    public void click(Object object){
        WebElement webElement = (WebElement) isClickable(object);
        webElement.click();
    }


//  this method confirms if the element is visible
//  or not in DOM till the expected waiting time
    public WebElement getByVisibility(WebElement element){
        condition = ExpectedConditions.visibilityOf(element);
        return wait.until(condition);
    }




//  this method returns a String with
//  specific locator
    public String getText(WebElement element){
        return getByVisibility(element).getText();
    }


//  this method helps to get the title of
//  page with the proper waiting time
    public void validateText(By locator, String text){
        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.textToBe(locator,text)
                )
        );
    }

    @Step
    public void validateSubText(WebElement element, String text){
        Assert.assertTrue(
                getText(element).contains(text)
        );
    }

}
