// in MakersSearchTest.java

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class AddTests {
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

    @Test
    public void addMultipleItems() throws Exception {
        driver.get("https://todomvc.com/examples/react/dist/#/active");
        WebElement searchBar = driver.findElement(By.id("todo-input"));
        int number_of_items = 1;
        while(number_of_items <= 10) {
            searchBar.sendKeys("test");
            searchBar.sendKeys(Keys.ENTER);
            number_of_items++;
            Thread.sleep(500);
        }
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
