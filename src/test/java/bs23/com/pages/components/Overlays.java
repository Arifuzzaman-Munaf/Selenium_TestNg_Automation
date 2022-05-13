package bs23.com.pages.components;

import bs23.com.base.BasePage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Overlays extends BasePage {

    public Overlays(WebDriver driver) {
        super(driver);
    }

    //  this method confirms if the overlay in the
    //  page vanishes with the expected wait time or not
    public void waitForOverlayToInvisible(@NotNull List<WebElement> overlays){
        if (overlays.size() > 0){
            wait.until(
                    ExpectedConditions.invisibilityOfAllElements(overlays)
            );
        }
        else {
            System.out.println("No Overlay to locate");
        }

    }
}
