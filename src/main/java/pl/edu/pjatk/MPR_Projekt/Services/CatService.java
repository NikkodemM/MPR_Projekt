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

        catRepository.save(new Cat("Frank", 2, 1L));
        catRepository.save(new Cat("Leon", 3,2L));
        catRepository.save(new Cat("Michael", 4, 3L));
    }

    public List<Cat> getCatByName(String name) {
        return this.catRepository.findByName(name);
    }

    public List<Cat> getCatList() {
        return (List<Cat>) catRepository.findAll();
    }

    public void createCat(Cat cat) {
        catRepository.save(cat);
    }

    public Optional<Cat> getCatById(Long id) {
        return this.catRepository.findById(id);
    }

    public void deleteCat(Long id) {
        catRepository.deleteById(id);
    }

    public void updateCat(Long id, Cat updatedCat) {
        Optional<Cat> cat = getCatById(id);
        if (cat.isPresent()) {
            Cat existingCat = cat.get();
            existingCat.setName(updatedCat.getName());
            existingCat.setAge(updatedCat.getAge());
            catRepository.save(existingCat);
        } else {
            throw new RuntimeException("Cat not found");
        }
    }

    public Optional<Cat> get(Long id) {
        return this.catRepository.findById(id);
    }
}
