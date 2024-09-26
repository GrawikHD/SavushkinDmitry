package animals.herbivore;

import animals.Herbivore;
import animals.aviary.Size;
import animals.interfaces.Run;
import animals.interfaces.Voice;

public class Sheep extends Herbivore implements Run, Voice {

    private String nameAnimal;

    public Sheep(String nameAnimal, Size size) {
        super(nameAnimal, size);
    }

    @Override
    public String voice() {
        return getNameAnimals() + " makes is sound: Baaaa";
    }

    @Override
    public void run() {
        System.out.println("The " + getNameAnimals() + " is running");
    }
}
