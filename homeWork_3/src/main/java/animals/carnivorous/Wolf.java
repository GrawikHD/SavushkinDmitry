package animals.carnivorous;

import animals.Carnivorous;
import animals.aviary.Size;
import animals.interfaces.Run;
import animals.interfaces.Swim;
import animals.interfaces.Voice;

public class Wolf extends Carnivorous implements Run, Voice, Swim {

    private String nameAnimal;

    public Wolf(String nameAnimal, Size size) {
        super(nameAnimal, size);
    }


    @Override
    public String voice() {
        return getNameAnimals() + " makes is sound: woof";
    }

    @Override
    public void swim() {
        System.out.println(getNameAnimals() + " swims");
    }

    @Override
    public void run() {
        System.out.println("The " + getNameAnimals() + " is running");
    }
}
