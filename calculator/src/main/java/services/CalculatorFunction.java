package services;

public interface CalculatorFunction {
    void readTwoDigitsAndMultiply();

    void readTwoDigitsAndMultiply(String prompt);

    void multiplyAndOutResult(int n1, int n2);

    void readTwoDigitsAndDivide();

    void readTwoDigitsAndDivide(String prompt);

    void divideAndOutResult(int n1, int n2);

    void readTwoDigitsAndSum();

    void readTwoDigitsAndSum(String prompt);

    void sumAndOutResult(int n1, int n2);

    void readTwoDigitsAndSubtract();

    void readTwoDigitsAndSubtract(String prompt);

    void subtractAndOutResult(int n1, int n2);
}
