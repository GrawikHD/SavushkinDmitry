import org.mockito.InOrder;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import services.CalculatorFunction;
import services.CalculatorImpl;
import services.IOService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CalculatorImplTest {

    private String prompt;

    private IOService ioService;
    private InOrder inOrder;
    private CalculatorFunction calculatorFunction;

    @BeforeMethod
    void setUp() {
        ioService = Mockito.mock(IOService.class);
        calculatorFunction = new CalculatorImpl(ioService);
        inOrder = inOrder(ioService);
    }

    @DataProvider
    public Object[][] testMultiply() {
        return new Object[][]{
                {"10 * 1 = 10", "10", "1"},
                {"11 * 0 = 0", "11", "0"},
                {"-10 * 1 = -10", "-10", "1"}
        };
    }

    @DataProvider
    public Object[][] testDivide() {
        return new Object[][]{
                {"10 / 1 = 10", "10", "1"},
                {"11 / -1 = -11", "11", "-1"}
        };
    }

    @DataProvider
    public Object[][] testSum() {
        return new Object[][]{
                {"10 + 1 = 11", "10", "1"},
                {"-10 + 0 = -10", "-10", "0"}
        };
    }

    @DataProvider
    public Object[][] testSubtract() {
        return new Object[][]{
                {"10 - 1 = 9", "10", "1"},
                {"-10 - 0 = -10", "-10", "0"}
        };
    }

    @DataProvider
    public Object[][] testFormat() {
        return new Object[][]{
                {"abc", "23"},
                {"24", "%ts"}
        };
    }

    @Test(dataProvider = "testMultiply")
    public void twoDigitsMultiply(String result, String n1, String n2) {
        given(ioService.readMessage()).willReturn(n1).willReturn(n2);
        calculatorFunction.readTwoDigitsAndMultiply(prompt);
        inOrder.verify(ioService, times(1)).out(prompt);
        inOrder.verify(ioService, times(2)).readMessage();
        inOrder.verify(ioService, times(1)).out(result);
    }

    @Test(dataProvider = "testDivide")
    public void twoDigitsDivide(String result, String n1, String n2) {
        given(ioService.readMessage()).willReturn(n1).willReturn(n2);
        calculatorFunction.readTwoDigitsAndDivide(prompt);
        inOrder.verify(ioService, times(1)).out(prompt);
        inOrder.verify(ioService, times(2)).readMessage();
        inOrder.verify(ioService, times(1)).out(result);
    }

    @Test(dataProvider = "testSum")
    public void twoDigitsSum(String result, String n1, String n2) {
        given(ioService.readMessage()).willReturn(n1).willReturn(n2);
        calculatorFunction.readTwoDigitsAndSum(prompt);
        inOrder.verify(ioService, times(1)).out(prompt);
        inOrder.verify(ioService, times(2)).readMessage();
        inOrder.verify(ioService, times(1)).out(result);
    }

    @Test(dataProvider = "testSubtract")
    public void twoDigitsSubtract(String result, String n1, String n2) {
        given(ioService.readMessage()).willReturn(n1).willReturn(n2);
        calculatorFunction.readTwoDigitsAndSubtract(prompt);
        inOrder.verify(ioService, times(1)).out(prompt);
        inOrder.verify(ioService, times(2)).readMessage();
        inOrder.verify(ioService, times(1)).out(result);
    }

    @Test(dataProvider = "testFormat")
    public void numberFormatMultiplyException(String n1, String n2) {
        given(ioService.readMessage()).willReturn("").willReturn(n2);
        given(ioService.readMessage()).willReturn(n1).willReturn("");

        Assert.assertThrows(NumberFormatException.class, () -> calculatorFunction.readTwoDigitsAndMultiply());
    }

    @Test(dataProvider = "testFormat")
    public void numberFormatDivideException(String n1, String n2) {
        given(ioService.readMessage()).willReturn("").willReturn(n2);
        given(ioService.readMessage()).willReturn(n1).willReturn("");

        Assert.assertThrows(NumberFormatException.class, () -> calculatorFunction.readTwoDigitsAndDivide());
    }

    @Test(dataProvider = "testFormat")
    public void numberFormatSumException(String n1, String n2) {
        given(ioService.readMessage()).willReturn("").willReturn(n2);
        given(ioService.readMessage()).willReturn(n1).willReturn("");

        Assert.assertThrows(NumberFormatException.class, () -> calculatorFunction.readTwoDigitsAndSum());
    }

    @Test(dataProvider = "testFormat")
    public void numberFormatSubtractException(String n1, String n2) {
        given(ioService.readMessage()).willReturn("").willReturn(n2);
        given(ioService.readMessage()).willReturn(n1).willReturn("");

        Assert.assertThrows(NumberFormatException.class, () -> calculatorFunction.readTwoDigitsAndSubtract());
    }

}
