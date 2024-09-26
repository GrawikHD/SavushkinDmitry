package animals.carnivorous;

import animals.Carnivorous;
import animals.aviary.Size;
import animals.interfaces.Fly;
import animals.interfaces.Voice;

public class Bird extends Carnivorous implements Fly, Voice {

    private String nameAnimal;

    public Bird(String nameAnimal, Size size) {
        super(nameAnimal, size);
    }


    @Override
    public String voice() {
        return getNameAnimals() + " makes is sound: ChikChirik";
    }

    @Override
    public void fly() {
        System.out.println(getNameAnimals() + " flying");
    }
}
