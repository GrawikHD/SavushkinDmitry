package animals.herbivore;

import animals.Herbivore;
import animals.aviary.Size;
import animals.interfaces.Run;
import animals.interfaces.Swim;
import animals.interfaces.Voice;

public class Duck extends Herbivore implements Swim, Voice, Run {

    public Duck(String nameAnimal, Size size) {
        super(nameAnimal, size);
    }

    @Override
    public String voice() {
        return getNameAnimals() + " makes is sound: Quack";
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
