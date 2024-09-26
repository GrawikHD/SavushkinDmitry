package animals.carnivorous;

import animals.Carnivorous;
import animals.aviary.Size;
import animals.interfaces.Swim;

public class Fish extends Carnivorous implements Swim {

    private String nameAnimal;

    public Fish(String nameAnimal, Size size) {
        super(nameAnimal, size);
    }


    @Override
    public void swim() {
        System.out.println(getNameAnimals() + " swims");
    }
}
