package pl.edu.pjatk.MPR_Projekt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Projekt.Model.Cat;
import pl.edu.pjatk.MPR_Projekt.Services.CatService;

import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {
    private CatService catService;

    @Autowired
    public MyRestController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("cat/{id}/identifier")
    public long getIdentifier(@PathVariable Long id) {
        Optional<Cat> cat = this.catService.get(id);
        return cat.map(Cat::getIdentifier).orElseThrow(() -> new RuntimeException("Cat nie odnaleziony ;("));
    }

    public Optional<Cat> get(@PathVariable Long id) {
        return this.catService.get(id);
    }

    @PostMapping("cat")
    public void create(@RequestBody Cat cat) {
        this.catService.createCat(cat);
    }

    @DeleteMapping("cat/{name}")
    public void delete(@PathVariable String name){
        this.catService.deleteCat(name);
    }

    @PutMapping("cat/{id}")
    public void update(@PathVariable Long id, @RequestBody Cat cat){
        this.catService.updateCat(id, cat);
    }
}
