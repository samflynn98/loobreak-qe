import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteTests {
    private ChromeDriver driver;

    @BeforeEach
    void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void deleteMultipleItemsTest() throws Exception {
        TodoPage page = new TodoPage(driver);
        page.navigate();
        int numberOfItemsToAdd = 1;
        page.addMultipleItems(numberOfItemsToAdd);
        int numberOfItemsToDelete = 1;
        page.deleteMultipleItems(numberOfItemsToDelete);
        int actualNumberOfItems = driver.findElements(By.cssSelector("[data-testid='todo-item-label']")).size();
        assertEquals((numberOfItemsToAdd-numberOfItemsToDelete), actualNumberOfItems);
    }

    @Test
    public void noItemsNothingToDeleteTest() throws Exception {
        TodoPage page = new TodoPage(driver);
        page.navigate();
        int itemCount = driver.findElements(By.cssSelector("[data-testid='todo-item-label']")).size();
        assertEquals(0, itemCount);
        int clearCompletedButtons = driver.findElements(By.cssSelector("[data-testid='footer-clear-completed']")).size();
        assertEquals(0, clearCompletedButtons);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}