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

    public TodoPage navigate() {
        driver.get("https://todomvc.com/examples/react/dist/#/active");
        return this;
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

    public void clickActiveFilter() {
        driver.findElement(By.linkText("Active")).click();
    }

    public void clickCompletedFilter() {
        driver.findElement(By.linkText("Completed")).click();
    }

    public void clickAllFilter() {
        driver.findElement(By.linkText("All")).click();
    }

    public int getNumberOfItems() {
        return driver.findElements(By.cssSelector("[data-testid='todo-item-label']")).size();
    }

    public void completeItem(int itemNumber) {

        driver.findElement(
                By.cssSelector("li:nth-child(" + itemNumber + ") [data-testid='todo-item-toggle']")
        ).click();
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
    public void deleteItem(int itemID) {
        WebElement itemToDelete = driver.findElement(By.cssSelector("li:nth-child(" + itemID + ") label"));
        new Actions(driver)
                .moveToElement(itemToDelete)
                .perform();
        driver.findElement(By.cssSelector("li:nth-child(" + itemID + ") .destroy")).click();
    }
    public void deleteMultipleItems(int numberOfItems) {
        while (numberOfItems >= 1) {
            WebElement itemToDelete = driver.findElement(By.cssSelector("li:nth-child(" + (numberOfItems) + ") label"));
            new Actions(driver)
                    .moveToElement(itemToDelete)
                    .perform();
            driver.findElement(By.cssSelector("li:nth-child(" + (numberOfItems) + ") .destroy")).click();
            numberOfItems--;
        }
    }

    public String getItemText(int itemID) {
        return driver.findElement(By.cssSelector("li:nth-child(" + (itemID) + ") label")).getText();
    }

    public void replaceItemText(int itemID, String newText) {
        WebElement itemToModify =
                driver.findElement(By.cssSelector("li:nth-child(" + itemID + ") label"));

        new Actions(driver)
                .doubleClick(itemToModify)
                .perform();

        WebElement textToModify =
                driver.findElement(By.cssSelector(".input-container:nth-child(1) > #todo-input"));

        textToModify.sendKeys(Keys.COMMAND + "a");
        textToModify.sendKeys(Keys.DELETE);
        textToModify.sendKeys(newText);
        textToModify.sendKeys(Keys.ENTER);
    }
    public String getStatusText() {
        return driver.findElement(
                By.cssSelector(".todo-count")
        ).getText();
    }

}