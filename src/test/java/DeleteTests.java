// in MakersSearchTest.java

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteTests {
    private ChromeDriver driver;

    @BeforeEach
    void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void deleteItem() throws Exception {
        driver.get("https://todomvc.com/examples/react/dist/");
        driver.findElement(By.id("todo-input")).sendKeys("test");
        driver.findElement(By.id("todo-input")).sendKeys(Keys.ENTER);
        WebElement itemToDelete = driver.findElement(By.cssSelector("[data-testid='todo-item-label']"));
        new Actions(driver)
                .moveToElement(itemToDelete)
                .perform();
        driver.findElement(By.cssSelector(".destroy")).click();
        boolean isPresent = driver.findElements(By.cssSelector("[data-testid='todo-item-label']")).size() > 0;
        assertFalse(isPresent);
    }

    @Test
    public void addMultipleItems() throws Exception {
        String[] words = {"apple", "banana", "orange", "grape", "melon"};
        Random random = new Random();
        driver.get("https://todomvc.com/examples/react/dist/#/active");
        WebElement searchBar = driver.findElement(By.id("todo-input"));
        int itemNumber = 1;
        while (itemNumber <= 10) {
            String randomWord = words[random.nextInt(words.length)];
            searchBar.sendKeys(randomWord);
            searchBar.sendKeys(Keys.ENTER);
            itemNumber++;
            Thread.sleep(100);
        }
        while (itemNumber > 1) {
            WebElement itemToDelete = driver.findElement(By.cssSelector("li:nth-child(" + (itemNumber - 1) + ") label"));
            new Actions(driver)
                    .moveToElement(itemToDelete)
                    .perform();
            Thread.sleep(100);
            driver.findElement(By.cssSelector("li:nth-child(" + (itemNumber - 1) + ") .destroy")).click();
            boolean isPresent = driver.findElements(By.cssSelector("li:nth-child(" + (itemNumber - 1) + ") label")).size() > 0;
            assertFalse(isPresent);
            itemNumber--;
            Thread.sleep(100);
        }
    }

    @Test
    public void noItemsNothingToDelete() throws Exception {

        driver.get("https://todomvc.com/examples/react/dist/#/");

        int itemCount = driver.findElements(
                By.cssSelector("[data-testid='todo-item-label']")
        ).size();

        assertEquals(0, itemCount);

        int clearCompletedButtons = driver.findElements(
                By.cssSelector("[data-testid='footer-clear-completed']")
        ).size();

        assertEquals(0, clearCompletedButtons);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}