import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void addMultipleItems(int itemNumber) {
        String[] words = {"apple", "banana", "orange", "grape", "melon"};
        Random random = new Random();
        WebElement searchBar = driver.findElement(By.id("todo-input"));
        int i = 1;
        while (i <= itemNumber) {
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
}