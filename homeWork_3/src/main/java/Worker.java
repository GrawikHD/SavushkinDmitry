import animals.Animal;
import animals.interfaces.Voice;
import food.Food;
import food.WrongFoodException;

public class Worker {

    private String nameWorker;

    public Worker(String nameWorker) {
        this.nameWorker = nameWorker;
    }

    public String getNameWorker() {
        return nameWorker;
    }

    public void feed(Animal animals, Food _food) throws WrongFoodException {
        System.out.println(getNameWorker() + " trying to feed " + animals.getNameAnimals() + " the next food: " + _food.getName());
        animals.eat(_food);
    }

    public void getVoice(Voice animals) {
        System.out.println(animals.voice());
    }
}
