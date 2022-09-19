package mini.project.mealplanner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mini.project.mealplanner.model.SearchRecipe;
import mini.project.mealplanner.services.SearchRecipeService;

@Controller
@RequestMapping(path={"/result"})
public class SearchRecipeController {


    @Autowired
    private SearchRecipeService searchSvc;

    @GetMapping
    public String searchRecipe(Model model, @RequestParam String query){

        List<SearchRecipe> searchRecipe = searchSvc.getSearch(query);
        model.addAttribute("query", query);
        model.addAttribute("searchRecipe", searchRecipe);

        return "result";

    }

   
    
}
