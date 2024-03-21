package MainClasses;

import java.util.ArrayList;
import java.util.Random;
public abstract class Animal {
    protected int id;
    protected int movementRange;
    protected Gender gender;
    protected int[] position ;
    private static final Random random = new Random();


    public Animal(int id, int movementRange, Gender gender)
    {
        this.id = id;
        this.movementRange = movementRange;
        this.gender = gender;
        // Rastgele bir konum oluştur
        int x = random.nextInt(500); // X koordinatı için 0-499 arasında rastgele bir değer
        int y = random.nextInt(500); // Y koordinatı için 0-499 arasında rastgele bir değer
        this.position = new int[]{x, y}; // Oluşturulan rastgele konumu ata
    }

    public void move(int direction, int distance) {
        // İstenen yöne hareket et
        int newX = this.position[0];
        int newY = this.position[1];

        switch (direction) {
            case 0: // Kuzey
                newY += distance;
                break;
            case 1: // Doğu
                newX += distance;
                break;
            case 2: // Güney
                newY -= distance;
                break;
            case 3: // Batı
                newX -= distance;
                break;
        }

        // Sınırların dışına çıkılıyorsa, geçerli yönleri bul ve rastgele bir yöne hareket et
        if (newX < 0 || newX >= 500 || newY < 0 || newY >= 500)
        {
            ArrayList<Integer> validDirections = new ArrayList<Integer>();

            // Sınırlar içinde kalmak için geçerli yönleri belirle
            if (this.position[0] > 0) validDirections.add(3); // Batıya hareket edebilir
            if (this.position[0] < 499) validDirections.add(1); // Doğuya hareket edebilir
            if (this.position[1] > 0) validDirections.add(2); // Güneye hareket edebilir
            if (this.position[1] < 499) validDirections.add(0); // Kuzeye hareket edebilir

            // Geçerli yönlerden rastgele birini seç
            int randomIndex = random.nextInt(validDirections.size());
            int newDirection = validDirections.get(randomIndex);

            // Yeni yönü kullanarak tekrar hareket et
            move(newDirection, distance); // Recursive çağrı, bu sefer geçerli bir yönde
        }
        else
        {
            // Sınırlar içindeyse, yeni konumu güncelle
            this.position[0] = newX;
            this.position[1] = newY;
        }
    }


    public boolean isWithinRange(Animal target, int range)
    {
        int x = this.position[0] - target.position[0];
        int y = this.position[1] - target.position[1];
        double distance = Math.sqrt(x*x + y*y);

        return distance <= range;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getMovementRange() {return movementRange;}

    public void setMovementRange(int movementRange) {this.movementRange = movementRange;}

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int[] getPosition() {return position;}

    public void setPosition(int[] position) {this.position = position;}
}


