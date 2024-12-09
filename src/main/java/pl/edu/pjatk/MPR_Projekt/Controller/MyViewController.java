package pl.edu.pjatk.MPR_Projekt.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.pjatk.MPR_Projekt.Model.Cat;
import pl.edu.pjatk.MPR_Projekt.Services.CatService;

import java.util.List;

@Controller
public class MyViewController {
    private CatService catService;

    public MyViewController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/view/all")
    public String displayAllCats(Model model) {
        model.addAttribute("nazwazmiennej", "jakaswartosc");
        List<Cat> catList = this.catService.getCatList();
        model.addAttribute("koty", catList);
        return "viewAll";
    }
    @GetMapping("/addForm")
    public String displayAddForm(Model model) {
        model.addAttribute("cat", new Cat());
        return "addForm";
    }
    @PostMapping("/addForm")
    public String displayAddForm(@ModelAttribute Cat cat) {
        this.catService.createCat(cat);
        return "redirect:/view/all";
    }
}
