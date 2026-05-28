import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModifyTests {

    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void modifyItem() throws Exception {

        TodoPage page = new TodoPage(driver);
        page.navigate();

        WebElement searchBar =
                driver.findElement(By.id("todo-input"));

        searchBar.sendKeys("test");
        searchBar.sendKeys(Keys.ENTER);

        WebElement item =
                driver.findElement(
                        By.cssSelector("[data-testid='todo-item-label']")
                );

        new Actions(driver)
                .doubleClick(item)
                .perform();

        WebElement updatedItem =
                driver.findElement(
                        By.cssSelector(".input-container:nth-child(1) > #todo-input")
                );

        updatedItem.sendKeys("123");
        updatedItem.sendKeys(Keys.ENTER);

        Thread.sleep(500);

        WebElement modifiedItem =
                driver.findElement(
                        By.cssSelector("[data-testid='todo-item-label']")
                );

        String itemName = modifiedItem.getText();

        assertEquals("test123", itemName);

        Thread.sleep(5000);
    }



    @Test
    public void modifyItemByRemovingAllText() throws Exception {
        TodoPage page = new TodoPage(driver);
        page.navigate();

        WebElement searchBar = driver.findElement(By.id("todo-input"));
        searchBar.sendKeys("test");
        searchBar.sendKeys(Keys.ENTER);

        WebElement item = driver.findElement(By.cssSelector("[data-testid='todo-item-label']"));

        new Actions(driver).doubleClick(item).perform();

        WebElement updatedItem = driver.findElement(
                By.cssSelector(".input-container:nth-child(1) > #todo-input")
        );

        updatedItem.sendKeys(Keys.COMMAND + "a");
        updatedItem.sendKeys(Keys.DELETE);
        updatedItem.sendKeys(Keys.ENTER);

        assertEquals(0, driver.findElements(By.cssSelector("[data-testid='todo-item-label']")).size());
    }

    @Test
    public void modifyCompletedItem() throws Exception {
        TodoPage page = new TodoPage(driver);
        page.navigate();

        WebElement searchBar = driver.findElement(By.id("todo-input"));
        searchBar.sendKeys("test");
        searchBar.sendKeys(Keys.ENTER);

        WebElement checkbox = driver.findElement(By.cssSelector("[data-testid='todo-item-toggle']"));
        checkbox.click();

        WebElement item = driver.findElement(By.cssSelector("[data-testid='todo-item-label']"));

        new Actions(driver).doubleClick(item).perform();

        WebElement updatedItem = driver.findElement(
                By.cssSelector(".input-container:nth-child(1) > #todo-input")
        );

        updatedItem.sendKeys("123");
        updatedItem.sendKeys(Keys.ENTER);

        WebElement modifiedItem = driver.findElement(By.cssSelector("[data-testid='todo-item-label']"));

        assertEquals("test123", modifiedItem.getText());
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}