package pl.edu.pjatk.MPR_Projekt.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Projekt.Model.Cat;
import pl.edu.pjatk.MPR_Projekt.Repository.CatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CatService {
    private CatRepository catRepository;
    List<Cat> catList = new ArrayList<>();

    @Autowired
    public CatService(CatRepository repository) {
        this.catRepository = repository;

        catRepository.save(new Cat("Frank", 2));
        catRepository.save(new Cat("Leon", 3));
        catRepository.save(new Cat("Michael", 4));
    }

    public List<Cat> getCatByName(String name) {
        return this.catRepository.findByName(name);
    }

    public List<Cat> getCatList() {
        return this.catList;
    }

    public void createCat(Cat cat) {
        this.catList.add(cat);
    }

    public Optional<Cat> getCatById(Long id) {
        return this.catRepository.findById(id);
    }

    public void deleteCat(String name) {
        this.catList.removeIf(cat -> cat.getName().equals(name));
    }

    public void updateCat(Long id, Cat updatedCat) {
        Optional<Cat> cat = getCatById(id);
        if (cat.isPresent()) {
            cat.get().setName(updatedCat.getName());
            cat.get().setAge(updatedCat.getAge());
        }
    }

    public Optional<Cat> get(Long id) {
        return this.catRepository.findById(id);
    }
}
