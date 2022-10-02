package mini.project.mealplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mini.project.mealplanner.repositories.RecipeRepository;

@Controller
@RequestMapping(path = {"/menu"})
public class MenuController {


    @Autowired
    private RecipeRepository recipeRepo;

    @GetMapping
    public String menu(){

        return "menu";
    }


    @PostMapping
    public String saveUser(@RequestParam String name, String password, Model model){

        recipeRepo.saveUser(name,password);
        
        model.addAttribute("name",name);

        return "menu";
    }
    
}
