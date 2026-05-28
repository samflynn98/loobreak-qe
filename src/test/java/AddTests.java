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
    public void addNumbersTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("0123456789");
        assertEquals("0123456789", page.getItemText(1));
    }

    @Test
    public void addSpecialCharactersTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("~`!@#%^&*()_-+={[}]|:;\"'<,>.?/$€£¥¢©®™§¶±÷×≠≤≥∞π°");
        assertEquals("~`!@#%^&*()_-+={[}]|:;\"'<,>.?/$€£¥¢©®™§¶±÷×≠≤≥∞π°", page.getItemText(1));
    }

    /*@Test
    public void addEmojisTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        String emojis = "😀😎🔥🚀💡🎉💯❤️🌟✨🎨🍕🌍🚀🤖💻🎧📱🚗🐾🛠️🔮🛍️🎈🏆";
        page.addItem(emojis);
        Thread.sleep(2000);
        assertEquals(emojis, page.getItemText(1));
    }*/

    @Test
    public void cannotAddEmptyValueTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("");
        assertEquals(0, page.getNumberOfItems());
    }

    @Test
    public void cannotAddSingleCharacterTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("a");
        assertEquals(0, page.getNumberOfItems());
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