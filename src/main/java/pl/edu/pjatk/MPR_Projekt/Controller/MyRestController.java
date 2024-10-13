package pl.edu.pjatk.MPR_Projekt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Projekt.Model.Cat;
import pl.edu.pjatk.MPR_Projekt.Services.CatService;

import java.util.List;

@RestController
public class MyRestController {
    private CatService catService;

    @Autowired
    public MyRestController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("cat/all")
    public List<Cat> getAll() {

        return this.catService.getCatList();
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
    public void update(@PathVariable int id, @RequestBody Cat cat){
        this.catService.updateCat(id, cat);
    }
}
