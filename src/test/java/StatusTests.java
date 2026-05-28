import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

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

    @Test
    public void thereIsAFilterForCompletionStatus() {
        TodoPage page = new TodoPage(driver);
        page.navigate();

        page.addItem("test");

        page.clickActiveFilter();
        page.clickCompletedFilter();
        page.clickAllFilter();
    }

    @Test
    public void completionStatusFilterSortsItemsAsExpected() {
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
