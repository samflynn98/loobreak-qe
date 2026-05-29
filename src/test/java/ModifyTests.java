import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

public class ModifyTests {
    private WebDriver driver;
    String browser = "chrome";

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
    public void modifyItemTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addMultipleItems(10);
        String textToAdd = "123";
        int itemID = 5;
        page.modifyItem(itemID, textToAdd);
        String modifiedItemName = page.getItemText(5);
        assertTrue(modifiedItemName.contains(textToAdd));
    }

    @Test
    public void modifyItemByRemovingAllTextTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addMultipleItems(10);
        String newText = "123";
        int itemID = 5;
        page.replaceItemText(itemID, newText);
        assertEquals(newText, page.getItemText(itemID));
    }

    @Test
    public void modifyCompletedItemTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("test");
        page.completeItem(1);
        WebElement completed = driver.findElement(By.cssSelector("[href='#/completed']"));
        completed.click();
        String textToAdd = "123";
        int itemID = 1;
        page.modifyItem(itemID, textToAdd);
        assertTrue(page.getItemText(itemID).contains(textToAdd));
    }

    @Test
    public void exitModifyingItemTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        String itemName = "test";
        page.addItem(itemName);
        int itemID = 1;
        WebElement itemToModify = driver.findElement(By.cssSelector("li:nth-child(" + itemID + ") label"));
        new Actions(driver)
                .doubleClick(itemToModify)
                .perform();
        WebElement textToModify = driver.findElement(By.cssSelector(".input-container:nth-child(1) > #todo-input"));
        String textToAdd = "123";
        textToModify.sendKeys(textToAdd);
        driver.findElement(By.id("todo-input")).click();
        assertEquals(itemName, page.getItemText(1));
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}