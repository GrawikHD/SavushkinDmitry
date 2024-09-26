package food;

public class WrongFoodException extends Exception {
    public WrongFoodException(String name) {
        System.out.println("Exception: " + name);
    }
}
