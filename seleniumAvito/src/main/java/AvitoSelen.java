import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AvitoSelen {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

        //Шаг 1
        driver.get("https://www.avito.ru/");

        //Шаг 2
        //Ищем категории по id
        Select select = new Select(driver.findElement(By.cssSelector("#category")));

        //Выводим все найденые элементы
        select.getOptions().forEach(webElement -> {
            System.out.println(webElement.getText());
        });

        //Выбираем категорию
        select.selectByVisibleText("Оргтехника и расходники");

        //Шаг 3
        //Ищем поле ввода и вписываем "Принтер"
        driver.findElement(By.xpath("//input[@data-marker='search-form/suggest']")).sendKeys("Принтер");

        //Шаг 4
        //Ищем элемент выбора региона и кликаем по нему
        driver.findElement(By.xpath("//div[@data-marker='search-form/region']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //В новом окне ищем поле вывода региона и вписываем "Владивосток"
        driver.findElement(By.xpath("//div/input[@data-marker='popup-location/region/input']")).sendKeys("Владивосток");

        //Шаг 5
        //Ищем первый элемент из выпадающего списка и кликаем по нему
        WebElement searchRegion = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(webDriver -> driver.findElement(By.xpath("//li[@data-marker='suggest(0)']")));
        System.out.println(searchRegion.getText());
        searchRegion.click();

        driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Шаг 6
        //Ищем checkbox и делаем проверку, если неактивне, то кликаем по нему и нажимаем кнопку
        WebElement checkbox = driver.findElement(By.xpath("//input[@data-marker='delivery-filter/input']"));
        if (!checkbox.isSelected()) {
            checkbox.sendKeys(Keys.SPACE);
        }
        driver.findElement(By.xpath("//button[@data-marker='search-filters/submit-button']")).click();

        //Шаг 7
        //Ищем элементы фильтра и выбираем соответствующее значение
        Select selectFilter = new Select(driver.findElement(By.xpath(
                "//div[contains(@class, 'index-content')]" +
                        "//select[contains(@class, 'select-select') and position()=1]")));

        selectFilter.selectByVisibleText("Дороже");

        //Шаг 8
        //В List будут находится найденные элементы
        List<WebElement> selectProduct = driver.findElements(By.xpath("//div[@data-marker='catalog-serp']" +
                "/div[@data-marker='item']"));

        //Итерируемся по элементам в List
        for (int i = 0; i < 3; i++) {
            System.out.println(selectProduct.get(i).findElement(By.xpath(".//a[@data-marker='item-title']")).getText());
            System.out.println(selectProduct.get(i).findElement(By.xpath(".//span[@data-marker='item-price']")).getText());
        }

        driver.quit();
    }
}
