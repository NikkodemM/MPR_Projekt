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


    public Cat() {}

    public Cat(String name, int age, Long id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.identifier = calculateIdentifier();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
        this.identifier = calculateIdentifier();
    }

    public int getAge() { return age; }
    public void setAge(int age) {
        this.age = age;
        this.identifier = calculateIdentifier();
    }

    public long getIdentifier() { return identifier; }

    private long calculateIdentifier() {
        long sum = 0;
        if (name != null) {
            for (int i = 0; i < name.length(); i++) {
                sum += (int) name.charAt(i);
            }
        }
        sum += age;
        return sum;
    }
}
