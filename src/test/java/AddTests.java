import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class AddTests {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void addItem() throws Exception {
        TodoPage page = new TodoPage(driver);
        page.navigate();
        WebElement searchBar = driver.findElement(By.id("todo-input"));
        searchBar.sendKeys("test");
        searchBar.sendKeys(Keys.ENTER);
        WebElement item = driver.findElement(By.cssSelector("[data-testid='todo-item-label']"));
        String itemName = item.getText();
        assertEquals(itemName, "test");
    }

    @Test
    public void addMultipleItems() throws Exception {
        String[] words = {"apple", "banana", "orange", "grape", "melon"};
        Random random = new Random();
        TodoPage page = new TodoPage(driver);
        page.navigate();
        WebElement searchBar = driver.findElement(By.id("todo-input"));
        int itemNumber = 1;
        while(itemNumber <= 10) {
            String randomWord = words[random.nextInt(words.length)];
            searchBar.sendKeys(randomWord);
            searchBar.sendKeys(Keys.ENTER);
            WebElement currentItem = driver.findElement(By.cssSelector("li:nth-child(" + itemNumber + ") label"));
            assertEquals(currentItem.getText(), randomWord);
            itemNumber++;
            Thread.sleep(100);
        }
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
