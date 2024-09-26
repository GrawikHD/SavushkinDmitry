import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AvitoStepTest {
    WebDriver driver;
    WebDriverWait wait;

    @Description("Инициализация драйвера")
    @BeforeClass
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Attachment(value = "Скриншоты", type = "image/png")
    public byte[] captureScreenshot(Screenshot screenshot) {
        //Создаём объект в котором будут записаны данные в массив байтов
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            //Записываем скриншот, указываем формат и куда будет записываться наш скриншот
            // (в начало текущего указателя потока)
            ImageIO.write(screenshot.getImage(), "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //С помощью toByteArray получаем данные
        return byteArrayOutputStream.toByteArray();
    }

    @Attachment(value = "Результат вывода", type = "text/plain")
    public String saveStringResult(String res) {
        return res;
    }

    @Test(description = "Запуск методов")
    void runMethodTest() {
        getResource();
        selectCategory();
        searchProduct();
        selectRegion();
        searchRegion();
        checkboxClick();
        selectFilter();
        searchAndSelectProduct();
    }

    @Step("Переход к ресурсу авито")
    void getResource() {
        driver.get("https://www.avito.ru/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='app']")));
        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("Выбор категории товаров")
    void selectCategory() {
        Select select = new Select(driver.findElement(By.cssSelector("#category")));
        select.selectByVisibleText("Оргтехника и расходники");

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("Поиск товара")
    void searchProduct() {
        driver.findElement(By.xpath("//input[@data-marker='search-form/suggest']")).sendKeys("Принтер");

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("Список регионов")
    void selectRegion() {
        driver.findElement(By.xpath("//div[@data-marker='search-form/region']")).click();

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("Выбор региона из списка регионов")
    void searchRegion() {
        driver.findElement(By.xpath("//div/input[@data-marker='popup-location/region/input']")).sendKeys("Владивосток");
        WebElement searchRegion = driver.findElement(By.xpath("//li[@data-marker='suggest(0)']"));
        System.out.println(searchRegion.getText());

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchRegion));
        searchRegion.click();
        driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']")).click();
    }

    @Step("Товары с доставкой авито")
    void checkboxClick() {
        WebElement checkbox = driver.findElement(By.xpath("//input[@data-marker='delivery-filter/input']"));
        if (!checkbox.isSelected()) {
            checkbox.sendKeys(Keys.SPACE);
        }

        Screenshot screen = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver);
        captureScreenshot(screen);
        driver.findElement(By.xpath("//button[@data-marker='search-filters/submit-button']")).click();
    }

    @Step("Фильтрация по ценам")
    void selectFilter() {
        Select selectFilter = new Select(driver.findElement(By.xpath(
                "//div[contains(@class, 'index-content')]" +
                        "//select[contains(@class, 'select-select') and position()=1]")));
        selectFilter.selectByVisibleText("Дороже");

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("Найти 3 самых дорогих товара")
    void searchAndSelectProduct() {
        By itemTitle = By.xpath(".//a[@data-marker='item-title']");
        By itemPrice = By.xpath(".//span[@data-marker='item-price']");

        List<WebElement> titleProduct = driver.findElements(itemTitle);
        List<WebElement> priceProduct = driver.findElements(itemPrice);

        for (int i = 0; i < 3; i++) {
            String result = "Наименование товара: " + titleProduct.get(i).getText() + "\n Цена товара: " + priceProduct.get(i).getText() + " (" + i + ")";
            System.out.println(result);
            Allure.addAttachment("Результат с консоли: ", result);
        }

        Screenshot screen = new AShot().takeScreenshot(driver);
        captureScreenshot(screen);
    }

    @Step("Выход из браузера")
    @AfterClass
    void tearDown() {
        driver.quit();
    }
}
