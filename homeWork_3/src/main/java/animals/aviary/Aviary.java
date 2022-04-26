package animals.aviary;

import animals.Animal;

import java.util.HashSet;
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

    public void addAnimal(T animal) {
        if (animal.getAnimalSize().getValue() <= sizeAviary.getValue()) {
            System.out.println(animal.getNameAnimals() + " ��������� � ������ " + getSizeAviary());
            animalAviary.add(animal);
        } else {
            System.out.println(animal.getNameAnimals() + " �� ��������� � ������ " + getSizeAviary());
        }
    }

    public void removeAnimal(T animal) {
        System.out.println(animal.getNameAnimals() + " �������. ");
        animalAviary.remove(animal);
    }

    public T getAnimalLink(String nameAnimals) {
        for (T t : animalAviary) {
            if (t.getNameAnimals().equals(nameAnimals)) {
                System.out.println("������ �� " + t.getNameAnimals() + ": " + t);
            } else {
                System.out.println("������ �� ��� ������ � �������. ");
            }
        }
        return null;
    }

}
