import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatusTests {
    private WebDriver driver;
    String browser = "safari";

    @BeforeEach
    void launchBrowser() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equals("safari")) {
            driver = new SafariDriver();
        }
    }

    @Test
    public void numberOfIncompleteItemsEqualsStatusBarTest() {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("test");
        String statusText = page.getStatusText();

        assertTrue(statusText.contains("1"));
    }

    @Test
    public void noItemsLeftWhenCompletedTest() {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("test");
        page.completeItem(1);
        String statusText = page.getStatusText();

        assertTrue(statusText.contains("0"));
    }

    @Test
    public void completedItemsNotIncludedInStatusTest() {
        TodoPage page = new TodoPage(driver).navigate();
        page.addMultipleItems(2);
        page.completeItem(1);
        String statusText = page.getStatusText();

        assertTrue(statusText.contains("1"));
    }

    @Test
    public void thereIsAFilterForCompletionStatusTest() {
        TodoPage page = new TodoPage(driver);
        page.navigate();

        page.addItem("test");

        page.clickActiveFilter();
        page.clickCompletedFilter();
        page.clickAllFilter();
        assertEquals(3, driver.findElements(By.cssSelector(".filters a")).size());
    }

    @Test
    public void completionStatusFilterSortsItemsTest() {
        TodoPage page = new TodoPage(driver);
        page.navigate();

        page.addItem("test1");
        page.addItem("test2");

        page.completeItem(1);

        page.clickActiveFilter();

        assertEquals(1, page.getNumberOfItems());

        page.clickCompletedFilter();

        assertEquals(1, page.getNumberOfItems());

        page.clickAllFilter();

        assertEquals(2, page.getNumberOfItems());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

}
