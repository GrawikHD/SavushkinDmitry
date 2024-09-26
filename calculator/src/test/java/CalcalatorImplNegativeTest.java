import org.mockito.InOrder;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import services.CalculatorFunction;
import services.CalculatorImpl;
import services.ConsoleIOService;
import services.IOService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CalcalatorImplNegativeTest {

    private IOService ioService;
    private CalculatorFunction calculatorFunction;
    private InOrder inOrder;

    @BeforeMethod
    void setUp() {
        ioService = Mockito.mock(IOService.class);
        calculatorFunction = new CalculatorImpl(ioService);
        inOrder = inOrder(ioService);
    }

    @DataProvider
    public Object[][] testMultiply() {
        return new Object[][]{
                {"(' * <& = ('<&", "('", "<&"},
                {"aa * bb = aabb", "aa", "bb"},
                {"10 * 0 = 10", "10", "0"},
                {"-10 * 1 = 10", "-10", "1"}
        };
    }

    @DataProvider
    public Object[][] testDivide() {
        return new Object[][]{
                {"(' / <& = '&", "('", "<&"},
                {"aa / bb = ab", "aa", "bb"},
                {"10 / 0 = 10", "10", "0"},
                {"-10 / 1 = 10", "-10", "1"}
        };
    }

    @DataProvider
    public Object[][] testSum() {
        return new Object[][]{
                {"(' + <& = ('<&", "('", "<&"},
                {"aa + bb = aabb", "aa", "bb"},
                {"10 + 1 = 12", "10", "1"},
                {"-10 + 0 = 9", "-10", "0"}
        };
    }

    @DataProvider
    public Object[][] testSubtract() {
        return new Object[][]{
                {"!& - #$ = !$", "!&", "!$"},
                {"aa - bb = ba", "aa", "bb"},
                {"10 - 1 = 0", "10", "1"},
                {"-10 - 0 = 10", "-10", "0"}
        };
    }

    @Test(dataProvider = "testMultiply")
    public void twoDigitsMultiply(String result, String n1, String n2) {
        given(ioService.readMessage()).willReturn(n1).willReturn(n2);
        calculatorFunction.readTwoDigitsAndMultiply();
        inOrder.verify(ioService, times(2)).readMessage();
        inOrder.verify(ioService, times(1)).out(result);
    }

    @Test(dataProvider = "testDivide")
    public void twoDigitsDivide(String result, String n1, String n2) {
        given(ioService.readMessage()).willReturn(n1).willReturn(n2);
        calculatorFunction.readTwoDigitsAndDivide();
        inOrder.verify(ioService, times(2)).readMessage();
        inOrder.verify(ioService, times(1)).out(result);
    }

    @Test(dataProvider = "testSum")
    public void twoDigitsSum(String result, String n1, String n2) {
        given(ioService.readMessage()).willReturn(n1).willReturn(n2);
        calculatorFunction.readTwoDigitsAndSum();
        inOrder.verify(ioService, times(2)).readMessage();
        inOrder.verify(ioService, times(1)).out(result);
    }

    @Test(dataProvider = "testSubtract")
    public void twoDigitsSubtract(String result, String n1, String n2) {
        given(ioService.readMessage()).willReturn(n1).willReturn(n2);
        calculatorFunction.readTwoDigitsAndSubtract();
        inOrder.verify(ioService, times(2)).readMessage();
        inOrder.verify(ioService, times(1)).out(result);
    }


}
