package animals.aviary;

import animals.Animal;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Aviary<T extends Animal> {
    private Size sizeAviary;

    public Size getSizeAviary() {
        return sizeAviary;
    }

    public Aviary(Size sizeAviary) {
        this.sizeAviary = sizeAviary;
    }

    private Set<T> animalAviary = new HashSet<>();

    public boolean addAnimal(T animal) {
        if (animal.getAnimalSize().getValue() <= sizeAviary.getValue()) {
            if(animalAviary.add(animal)) {
                System.out.println(animal.getNameAnimals() + " добавлено в вольер " + getSizeAviary() + " " + animal.hashCode());
                return true;
            } else {
                System.out.println(animal.getNameAnimals() + " уже присутствует в вальере. ");
                return false;
            }
        } else {
            System.out.println(animal.getNameAnimals() + " не добавлено в вольер, так как " +
                    "животное слишком большое дл€ вольера " + getSizeAviary());
            return false;
        }
    }

    public void getAnimals() {
        System.out.println("¬альер размером " + getSizeAviary() + " имеет объектов: " + animalAviary.size() + ". ");
    }

    public void removeAnimal(T animal) {
        System.out.println(animal.getNameAnimals() + " удалено. ");
        animalAviary.remove(animal);
    }

    public T getAnimalLink(String nameAnimals) {
        for (T animal : animalAviary) {
            if (animal.getNameAnimals().equals(nameAnimals)) {
                System.out.println("—сылка на " + animal.getNameAnimals() + ": " + animal);
            } else {
                System.out.println("ќбъект не был найден в вольере. ");
            }
        }
        return null;
    }
}
