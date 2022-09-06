package mini.project.mealplanner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mini.project.mealplanner.model.RandomRecipe;
import mini.project.mealplanner.services.RandomRecipeService;

@Controller
@RequestMapping(path = {"/random"})
public class RandomRecipeController {

    @Autowired
    private RandomRecipeService rmdSvc;

    @GetMapping
    public String getRandomRecipe(Model model){

        List<RandomRecipe> randomRecipe = rmdSvc.getRandomRecipe();

        model.addAttribute("randomRecipe",randomRecipe);


        return "random";

    }

    
}
