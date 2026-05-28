import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModifyTests {

    private ChromeDriver driver;

    @BeforeEach
    void launchBrowser() {
        driver = new ChromeDriver();
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
        WebElement itemToModify = driver.findElement(By.cssSelector("li:nth-child(" + itemID + ") label"));
        new Actions(driver).doubleClick(itemToModify).perform();
        WebElement textToModify = driver.findElement(By.cssSelector(".input-container:nth-child(1) > #todo-input"));
        textToModify.sendKeys(Keys.COMMAND + "a");
        textToModify.sendKeys(Keys.DELETE);
        textToModify.sendKeys(newText);
        textToModify.sendKeys(Keys.ENTER);
        String modifiedItemName = page.getItemText(5);
        System.out.println(modifiedItemName);
        assertEquals(newText, modifiedItemName);
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

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}