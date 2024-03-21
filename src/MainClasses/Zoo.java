package MainClasses;

import Animals.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Zoo {
    private List<Animal> animals;
    private Hunter hunter;
    private final Random random = new Random();
    private int idCounter = 0; // Hayvanlar için benzersiz ID sayaçı

    public Zoo() {
        animals = new ArrayList<>();
        initializeAnimals();
        hunter = new Hunter(idCounter++, 1, Gender.MALE); // Avcının ID'si, hareket aralığı ve cinsiyeti
    }

    private void initializeAnimals() {
        // Hayvanları başlat
        for (int i = 0; i < 15; i++) {
            animals.add(new Sheep(idCounter++, 2, Gender.MALE));
            animals.add(new Sheep(idCounter++, 2, Gender.FEMALE));
        }
        for (int i = 0; i < 5; i++) {
            animals.add(new Cow(idCounter++, 2, Gender.MALE));
            animals.add(new Cow(idCounter++, 2, Gender.FEMALE));
        }
        for (int i = 0; i < 10; i++) {
            animals.add(new Chicken(idCounter++, 1, Gender.FEMALE)); // Tavukların cinsiyetini varsayılan olarak dişi aldım
            animals.add(new Rooster(idCounter++, 1, Gender.MALE));
        }
        for (int i = 0; i < 5; i++) {
            animals.add(new Wolf(idCounter++, 3, Gender.FEMALE));
            animals.add(new Wolf(idCounter++, 3, Gender.MALE));
        }
        for (int i = 0; i < 4; i++) {
            animals.add(new Lion(idCounter++, 4, Gender.MALE));
            animals.add(new Lion(idCounter++, 4, Gender.FEMALE));
        }
    }

    public void simulateMovements() {
        for (Animal animal : animals) {
            int direction = random.nextInt(4); // 0-3 arası yön (Kuzey, Doğu, Güney, Batı)
            animal.move(direction, animal.getMovementRange());
        }
        // Avcının hareketi
        int hunterDirection = random.nextInt(4);
        hunter.move(hunterDirection, hunter.getMovementRange());
    }

    public void checkPredation() {
        List<Animal> toRemove = new ArrayList<>(); // Silinecek hayvanları tutacak geçici liste

        for (Animal predator : animals) {
            if (predator instanceof Predator && !(predator instanceof Hunter)) {
                List<Animal> caughtPrey = ((Predator) predator).hunt(animals, predator instanceof Wolf ? 4 : predator instanceof Lion ? 5 : 0);
                toRemove.addAll(caughtPrey); // Yakalanan avları geçici listeye ekle
            }
        }

        // Avcı için avlanma kontrolü
        List<Animal> hunterCaughtPrey = hunter.hunt(animals, 8);
        toRemove.addAll(hunterCaughtPrey); // Avcının yakaladığı avları da geçici listeye ekle

        animals.removeAll(toRemove); // Geçici listedeki tüm hayvanları ana listeden çıkar
    }


    public void checkReproduction() {
        List<Animal> newAnimals = new CopyOnWriteArrayList<>();

        animals.parallelStream().forEach(animal1 -> {
            animals.stream()
                    .filter(animal2 -> !animal1.equals(animal2)
                            && animal1.getClass().equals(animal2.getClass())
                            && animal1.isWithinRange(animal2, 3)
                            && animal1.getGender() != animal2.getGender())
                    .forEach(animal2 -> {
                        Gender gender = ThreadLocalRandom.current().nextBoolean() ? Gender.MALE : Gender.FEMALE;
                        Animal newAnimal = createNewAnimal(animal1, gender);
                        if (newAnimal != null) {
                            newAnimals.add(newAnimal);
                        }
                    });
        });

        animals.addAll(newAnimals);
    }



    private Animal createNewAnimal(Animal parent, Gender gender) {

        if (parent instanceof Sheep)
        {
            return new Sheep(idCounter++, 2, gender);
        }
        else if (parent instanceof Cow)
        {
            return new Cow(idCounter++, 2, gender);
        }
        else if (parent instanceof Chicken || parent instanceof Rooster) {
            // Tavuk ve horozdan rastgele olarak yeni bir tavuk veya horoz oluştur
            if (random.nextBoolean()) {
                return new Chicken(idCounter++, 1, gender); // Yeni tavuk
            } else {
                return new Rooster(idCounter++, 1, gender); // Yeni horoz
            }
        }
        else if (parent instanceof Wolf)
        {
            // Kurt için yeni bir kurt örneği oluştur
            return new Wolf(idCounter++, 3, gender);
        }
        else if (parent instanceof Lion)
        {
            // Aslan için yeni bir aslan örneği oluştur
            return new Lion(idCounter++, 4, gender);
        }

        return null;
    }

    public void printAnimalCounts() {

        System.out.println("Hayvan Sayıları:");
        System.out.println("********************************************");
        System.out.println("Koyun: " + animals.stream().filter(a -> a instanceof Sheep).count());
        System.out.println("İnek: " + animals.stream().filter(a -> a instanceof Cow).count());
        System.out.println("Tavuk: " + animals.stream().filter(a -> a instanceof Chicken).count());
        System.out.println("Horoz: " + animals.stream().filter(a -> a instanceof Rooster).count());
        System.out.println("Kurt: " + animals.stream().filter(a -> a instanceof Wolf).count());
        System.out.println("Aslan: " + animals.stream().filter(a -> a instanceof Lion).count());
        // Avcının sayısını doğrudan yazdırıyorum çünkü avcıdan birtane var
        System.out.println("********************************************");
        System.out.println("Avcı Sayısı");
        System.out.println("********************************************");
        System.out.println("Avcı: 1");
    }

    public void simulate() {
        // Simülasyon başlangıç zamanını kaydet
        long startTime = System.currentTimeMillis();

        int i;
        for (i = 0; i < 1000; i++) {
            simulateMovements();
            checkPredation();
            checkReproduction();
        }

        // Simülasyon bitiş zamanını kaydet ve toplam süreyi hesapla
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("********************************************");
        System.out.println("Simülasyon " + i + " adımda tamamlandı.");
        System.out.println("Toplam Çalışma Süresi: " + totalTime + " milisaniye.");
        System.out.println("********************************************");
        printAnimalCounts();
    }


    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        zoo.simulate();
    }
}
