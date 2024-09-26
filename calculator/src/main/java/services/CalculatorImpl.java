package services;

public class CalculatorImpl implements CalculatorFunction {

    private IOService ioService;

    public CalculatorImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void readTwoDigitsAndMultiply() {
        int n1 = Integer.parseInt(ioService.readMessage());
        int n2 = Integer.parseInt(ioService.readMessage());
        multiplyAndOutResult(n1, n2);
    }

    public void readTwoDigitsAndMultiply(String prompt) {
        ioService.out(prompt);
        readTwoDigitsAndMultiply();
    }

    @Override
    public void multiplyAndOutResult(int n1, int n2) {
        ioService.out(String.format("%d * %d = %d", n1, n2, n1 * n2));
    }

    @Override
    public void readTwoDigitsAndDivide() {
        int n1 = Integer.parseInt(ioService.readMessage());
        int n2 = Integer.parseInt(ioService.readMessage());
        divideAndOutResult(n1, n2);
    }

    public void readTwoDigitsAndDivide(String prompt) {
        ioService.out(prompt);
        readTwoDigitsAndDivide();
    }

    @Override
    public void divideAndOutResult(int n1, int n2) {
        ioService.out(String.format("%d / %d = %d", n1, n2, n1 / n2));
    }

    @Override
    public void readTwoDigitsAndSum() {
        int n1 = Integer.parseInt(ioService.readMessage());
        int n2 = Integer.parseInt(ioService.readMessage());
        sumAndOutResult(n1, n2);
    }

    public void readTwoDigitsAndSum(String prompt) {
        ioService.out(prompt);
        readTwoDigitsAndSum();
    }

    @Override
    public void sumAndOutResult(int n1, int n2) {
        ioService.out(String.format("%d + %d = %d", n1, n2, n1 + n2));
    }

    @Override
    public void readTwoDigitsAndSubtract() {
        int n1 = Integer.parseInt(ioService.readMessage());
        int n2 = Integer.parseInt(ioService.readMessage());
        subtractAndOutResult(n1, n2);
    }

    public void readTwoDigitsAndSubtract(String prompt) {
        ioService.out(prompt);
        readTwoDigitsAndSubtract();
    }

    @Override
    public void subtractAndOutResult(int n1, int n2) {
        ioService.out(String.format("%d - %d = %d", n1, n2, n1 - n2));
    }
}
