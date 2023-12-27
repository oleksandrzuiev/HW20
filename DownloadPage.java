package homeWork20;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class DownloadPage {

    public final WebDriver driver;

    public final WebDriverWait wait;

    public DownloadPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void downloadFile(String fileName) {
        try {
            wait.until(visibilityOf(driver.findElement(By.cssSelector("a[href$='" + fileName + "']")))).click();
        } catch (NoSuchElementException | TimeoutException e) {
            System.err.println("Error while trying to download the file: " + e.getMessage());
        }
    }
}
