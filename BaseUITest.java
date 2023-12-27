package homeWork20;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.HashMap;

public class BaseUITest {

    protected WebDriver driver;
    protected TestProperties props;

    @BeforeClass
    public void setUpClass() {
        props = new TestProperties();
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory",
                new File(props.getProperty("downloadFolderPath")).getAbsolutePath());
        options.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(props.getProperty("url"));
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        clearDownloadDirectory(props.getProperty("downloadFolderPath"));
    }

    public void clearDownloadDirectory(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.delete()) {
                    System.err.println("Failed to delete: " + file.getAbsolutePath());
                }
            }
        }
    }
}
