package mini.project.mealplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mini.project.mealplanner.repositories.RecipeRepository;

@Controller
@RequestMapping(path = {"","/","/home"})
public class HomeController {

    @Autowired
    private RecipeRepository recipeRepo;


    @GetMapping
    public String resetUser(){

        recipeRepo.clear();

        return "home";
    }

}
