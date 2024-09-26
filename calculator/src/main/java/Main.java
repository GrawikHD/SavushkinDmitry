import services.CalculatorFunction;
import services.CalculatorImpl;
import services.ConsoleIOService;
import services.IOService;

public class Main {
    public static void main(String[] args) {

        IOService consoleIO = new ConsoleIOService();

        consoleIO.out("Это калькулятор!");

        CalculatorFunction calculatorFunction = new CalculatorImpl(consoleIO);

        calculatorFunction.readTwoDigitsAndMultiply("Введите по очереди два числа и мы их перемножим");
        consoleIO.out("-----------------------------------");
        calculatorFunction.readTwoDigitsAndDivide();
        consoleIO.out("-----------------------------------");
        calculatorFunction.readTwoDigitsAndSum("Введите по очереди два числа и мы их сложим");
        consoleIO.out("-----------------------------------");
        calculatorFunction.readTwoDigitsAndSubtract();
        consoleIO.out("-----------------------------------");
    }
}
