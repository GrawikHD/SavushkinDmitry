import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.ConsoleIOService;
import services.IOService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CalculatorConsoleTest {

    private static final String EOL = System.lineSeparator();
    private static final String TEXT_TO_PRINT = "Test console calculator 1";
    private static final String TEXT_TO_PRINT1 = "Test console calculator 1";
    private PrintStream printStream;
    private ByteArrayOutputStream bos;
    private IOService ioService;

    @BeforeMethod
    void setUp() {
        System.out.println(Thread.currentThread().getName());
        //запоминаем prinstream специальную переменную
        printStream = System.out;
        //динамический массив
        bos = new ByteArrayOutputStream();
        //устанавливаем текующий System.out
        System.setOut(new PrintStream(bos));
        ioService = new ConsoleIOService();
    }

    @AfterMethod
    void tearDown() {
        //возвращаемся в прежнее состояние
        System.setOut(printStream);
    }

    @Test
    void testPrintFirstLine() throws InterruptedException {
        ioService.out(TEXT_TO_PRINT);
        //пребразовываем записанные байт данные в строку и сравниваем
        Assert.assertEquals(bos.toString(), TEXT_TO_PRINT + EOL);
    }

    @Test
    void testPrintSecondLine() throws InterruptedException {
        ioService.out(TEXT_TO_PRINT1);
        Assert.assertEquals(bos.toString(), TEXT_TO_PRINT1 + EOL);
    }

}
