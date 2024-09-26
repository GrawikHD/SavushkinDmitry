package food;

public class Meat extends Food {

    @Override
    public String getName() {
        return "Meat";
    }

    @Override
    public int addSatiety() {
        return 5;
    }

}
