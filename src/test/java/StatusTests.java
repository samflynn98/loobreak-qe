import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class StatusTests {

    private ChromeDriver driver;

    @BeforeEach
    void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void numberOfIncompleteItemsEqualsStatusBar() {

        TodoPage page = new TodoPage(driver);
        page.navigate();

        WebElement searchBar = driver.findElement(By.id("todo-input"));
        searchBar.sendKeys("test");
        searchBar.sendKeys(Keys.ENTER);

        WebElement checkbox = driver.findElement(By.cssSelector("[data-testid='todo-item-toggle']"));

        checkbox.click();


        String statusText = driver.findElement(By.cssSelector(".todo-count")).getText();

        assertTrue(statusText.contains("0"));
    }

    @Test
    public void noItemsLeftWhenCompleted() {

        TodoPage page = new TodoPage(driver);
        page.navigate();

        WebElement searchBar = driver.findElement(By.id("todo-input"));
        searchBar.sendKeys("test");
        searchBar.sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("[data-testid='todo-item-toggle']")

        ).click();

        String statusText = driver.findElement(By.cssSelector(".todo-count")).getText();

        assertTrue(statusText.contains("0"));
    }

    @Test
    public void completedItemsNotIncludedInStatus() {

        TodoPage page = new TodoPage(driver);
        page.navigate();

        WebElement searchBar = driver.findElement(By.id("todo-input"));
        searchBar.sendKeys("test1");
        searchBar.sendKeys(Keys.ENTER);
        searchBar.sendKeys("test2");
        searchBar.sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("[data-testid='todo-item-toggle']")).click();

        String statusText = driver.findElement(By.cssSelector(".todo-count")).getText();

        assertTrue(statusText.contains("1"));
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

}
