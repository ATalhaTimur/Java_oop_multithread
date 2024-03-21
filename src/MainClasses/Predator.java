package MainClasses;

import java.util.List;
import java.util.ArrayList;

public abstract class Predator extends Animal {

    public Predator(int id, int movementRange, Gender gender) {
        super(id, movementRange, gender);
    }

    // Belirli bir menzildeki avları avlama fonksiyonu
    public List<Animal> hunt(List<Animal> animals, int huntingRange) {
        List<Animal> caughtPrey = new ArrayList<>();
        for (Animal animal : animals) {
            if (this.isWithinRange(animal, huntingRange) && animal instanceof Prey) {
                caughtPrey.add(animal);
            }
        }
        return caughtPrey;
    }
}
