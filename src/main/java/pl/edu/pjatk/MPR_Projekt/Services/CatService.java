package pl.edu.pjatk.MPR_Projekt.Services;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Projekt.Model.Cat;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatService {
    List<Cat> catList = new ArrayList<>();

    public CatService() {
        catList.add(new Cat("Frank",2));
        catList.add(new Cat("Leon",3));
        catList.add(new Cat("Michael",4));
    }

    public List<Cat> getCatList() {
        return this.catList;
    }

    public void createCat(Cat cat) {
        this.catList.add(cat);
    }

    public Cat getCatById(int id) {
        if (id >= 0 && id < catList.size()) {
            return catList.get(id);
        }
        return null;
    }

    public void deleteCat(String name) {
        this.catList.removeIf(cat -> cat.getName().equals(name));
    }

    public void updateCat(int id, Cat updatedCat) {
        Cat cat = getCatById(id);
        if (cat != null) {
            cat.setName(updatedCat.getName());
            cat.setAge(updatedCat.getAge());
        }
    }

}

