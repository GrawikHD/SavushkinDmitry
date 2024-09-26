package animals;

import animals.aviary.Size;
import food.Food;
import food.WrongFoodException;
import java.util.Objects;

public abstract class Animal {

    private String nameAnimals;
    private Size sizeAnimal;

    public Animal(String nameAnimals, Size sizeAnimal) {
        this.nameAnimals = nameAnimals;
        this.sizeAnimal = sizeAnimal;
    }

    public String getNameAnimals() {
        return nameAnimals;
    }

    public Size getAnimalSize() {
        return sizeAnimal;
    }

    public abstract void eat(Food food) throws WrongFoodException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return nameAnimals.equals(animal.nameAnimals) && sizeAnimal == animal.sizeAnimal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameAnimals, sizeAnimal);
    }
}
