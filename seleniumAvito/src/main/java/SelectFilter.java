import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectFilter {

    WebDriver driver;

    public SelectFilter(WebDriver driver) {
        this.driver = driver;
    }

    public void setFilter(String nameFilter) {
        Select selectFilter = new Select(driver.findElement(By.xpath(
                "//div[contains(@class, 'index-content')]" +
                        "//select[contains(@class, 'select-select') and position()=1]")));
        selectFilter.selectByVisibleText(nameFilter);
    }
}
