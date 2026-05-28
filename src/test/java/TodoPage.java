import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.Random;

public class TodoPage {
    protected WebDriver driver;

    public TodoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://todomvc.com/examples/react/dist/#/active");
    }

    public void addItem(String text) {
        WebElement searchBar = driver.findElement(By.id("todo-input"));
        searchBar.sendKeys(text);
        searchBar.sendKeys(Keys.ENTER);
    }

    public void addMultipleItems(int numberOfItems) {
        String[] words = {"apple", "banana", "orange", "grape", "melon"};
        Random random = new Random();
        WebElement searchBar = driver.findElement(By.id("todo-input"));
        int i = 1;
        while (i <= numberOfItems) {
            String randomWord = words[random.nextInt(words.length)];
            searchBar.sendKeys(randomWord);
            searchBar.sendKeys(Keys.ENTER);
            i++;
        }
    }
    public void modifyItem(int itemID, String textToAdd) {
            WebElement itemToModify = driver.findElement(By.cssSelector("li:nth-child(" + itemID + ") label"));
            new Actions(driver)
                    .doubleClick(itemToModify)
                    .perform();
            WebElement textToModify = driver.findElement(By.cssSelector(".input-container:nth-child(1) > #todo-input"));
            textToModify.sendKeys(textToAdd);
            textToModify.sendKeys(Keys.ENTER);
    }
    public void deleteItem(int itemID, String textToAdd) {
        WebElement itemToModify = driver.findElement(By.cssSelector("li:nth-child(" + itemID + ") label"));
        new Actions(driver)
                .doubleClick(itemToModify)
                .perform();
        WebElement textToModify = driver.findElement(By.cssSelector(".input-container:nth-child(1) > #todo-input"));
        textToModify.sendKeys(textToAdd);
        textToModify.sendKeys(Keys.ENTER);
    }
}