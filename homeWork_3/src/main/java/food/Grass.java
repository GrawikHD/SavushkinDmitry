package food;

public class Grass extends Food {

    @Override
    public String getName() {
        return "Grass";
    }

    @Override
    public int addSatiety() {
        return 5;
    }


}
