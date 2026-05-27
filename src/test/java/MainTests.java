// in MakersSearchTest.java

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTests {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void addItem() throws Exception {
        driver.get("https://todomvc.com/examples/react/dist/#/active");
        WebElement searchBar = driver.findElement(By.id("todo-input"));
        searchBar.sendKeys("test");
        searchBar.sendKeys(Keys.ENTER);
        WebElement item = driver.findElement(By.cssSelector("[data-testid='todo-item-label']"));
        String itemName = item.getText();
        assertEquals(itemName, "test");
        Thread.sleep(5000);
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
