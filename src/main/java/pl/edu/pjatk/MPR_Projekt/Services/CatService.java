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
    private StringUtilsService stringUtilsService;

    @Autowired
    public CatService(CatRepository repository, StringUtilsService stringUtilsService) {
        this.catRepository = repository;
        this.stringUtilsService = stringUtilsService;
        catRepository.save(new Cat("Frank", 2, 1L));
        catRepository.save(new Cat("Leon", 3,2L));
        catRepository.save(new Cat("Michael", 4, 3L));
    }

    public List<Cat> getCatByName(String name) {
        return this.catRepository.findByName(name);
    }

    public List<Cat> getCatList() {
        List<Cat> cats = (List<Cat>) catRepository.findAll();
        // Przetworzenie nazw przy pobieraniu z bazy (GET)
        cats.forEach(cat -> cat.setName(stringUtilsService.toLowerCase(cat.getName())));
        return cats;
    }

    public void createCat(Cat cat) {
        // Przetworzenie nazw przy zapisie do bazy (POST)
        cat.setName(stringUtilsService.toUpperCase(cat.getName()));
        catRepository.save(cat);
    }

    public Optional<Cat> getCatById(Long id) {
        Optional<Cat> cat = this.catRepository.findById(id);
        // Przetworzenie nazwy przy pobieraniu z bazy (GET)
        cat.ifPresent(c -> c.setName(stringUtilsService.toLowerCase(c.getName())));
        return cat;
    }

    public void deleteCat(Long id) {
        catRepository.deleteById(id);
    }

    public void updateCat(Long id, Cat updatedCat) {
        Optional<Cat> cat = getCatById(id);
        if (cat.isPresent()) {
            Cat existingCat = cat.get();
            existingCat.setName(stringUtilsService.toUpperCase(updatedCat.getName())); // przetworzenie przy zapisie (POST)
            existingCat.setAge(updatedCat.getAge());
            catRepository.save(existingCat);
        } else {
            throw new RuntimeException("Cat not found");
        }
    }

    public Optional<Cat> get(Long id) {
        Optional<Cat> cat = this.catRepository.findById(id);
        cat.ifPresent(c -> c.setName(stringUtilsService.toLowerCase(c.getName())));
        return cat;
    }
}
