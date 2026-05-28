import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class AddTests {
    private ChromeDriver driver;

    @BeforeEach
    void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void addSingleItemTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("test");
        assertEquals("test", page.getItemText(1));
    }

    @Test
    public void addMultipleItemsTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        int numberOfItems = 10;
        page.addMultipleItems(numberOfItems);
        assertEquals(numberOfItems, page.getNumberOfItems());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}