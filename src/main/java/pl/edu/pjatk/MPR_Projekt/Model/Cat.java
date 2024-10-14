package pl.edu.pjatk.MPR_Projekt.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;
    private long identifier;

    // Domyślny konstruktor
    public Cat() {}

    // Konstruktor inicjalizujący, obliczający identyfikator
    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        this.identifier = calculateIdentifier();
    }

    // Getter i setter dla id
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Getter i setter dla name
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
        this.identifier = calculateIdentifier(); // Reobliczanie identifier po zmianie name
    }

    // Getter i setter dla age
    public int getAge() { return age; }
    public void setAge(int age) {
        this.age = age;
        this.identifier = calculateIdentifier(); // Reobliczanie identifier po zmianie age
    }

    // Getter dla identifier (brak settera, bo identifier obliczany automatycznie)
    public long getIdentifier() { return identifier; }

    // Metoda obliczająca wartość identyfikatora
    private long calculateIdentifier() {
        long sum = 0;

        // Sumowanie wartości charów w stringu name
        for (int i = 0; i < name.length(); i++) {
            sum += (int) name.charAt(i);
        }

        // Dodawanie wartości age
        sum += age;

        return sum;
    }
}
