package animals;

import animals.aviary.Size;
import food.Food;
import food.Grass;
import food.WrongFoodException;

public class Herbivore extends Animal {

    private int satiety = 0;

    public Herbivore(String nameAnimal, Size size) {
        super(nameAnimal, size);
    }

    @Override
    public void eat(Food _food) throws WrongFoodException {
        if (_food instanceof Grass) {
            System.out.println(getNameAnimals() + " will eat " + _food.getName());
            addSatiety(_food);
            System.out.println(getNameAnimals() + " ate with pleasure and it added satiety: " + getSatiety() + ".\n");
        } else {
            throw new WrongFoodException("WrongFoodException");
        }
    }

    public void addSatiety(Food _food) {
        this.satiety += _food.addSatiety();
    }

    public int getSatiety() {
        return satiety;
    }
}
