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
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("test");
        page.completeItem(1);
    }

    @Test
    public void noItemsLeftWhenCompleted() {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("test");
        page.completeItem(1);
    }

    @Test
    public void completedItemsNotIncludedInStatus() {
        TodoPage page = new TodoPage(driver).navigate();
        page.addMultipleItems(2);
        page.completeItem(1);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

}
