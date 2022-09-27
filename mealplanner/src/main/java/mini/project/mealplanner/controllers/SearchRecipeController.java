package mini.project.mealplanner.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mini.project.mealplanner.model.SearchRecipe;
import mini.project.mealplanner.repositories.RecipeRepository;
import mini.project.mealplanner.services.SearchRecipeService;

@Controller
@RequestMapping(path={"/result"})
public class SearchRecipeController {


    @Autowired
    private SearchRecipeService searchSvc;

    @Autowired
    private RecipeRepository recipeRepo;

    @GetMapping
    public String searchRecipe(Model model, @RequestParam String query, 
                                            @RequestParam Integer maxCarbs, 
                                            @RequestParam Integer maxCalories, HttpSession session){

        List<SearchRecipe> searchRecipe = searchSvc.getSearch(query,maxCarbs,maxCalories);
        session.setAttribute("searchRecipe", searchRecipe);
        model.addAttribute("query", query);
        model.addAttribute("searchRecipe", searchRecipe);
        model.addAttribute("maxCarbs", maxCarbs);
        model.addAttribute("maxCalories", maxCalories);

        return "result";

    }

    @PostMapping
    public String saveSearchRecipe(@RequestBody MultiValueMap<String,String> form, Model model){

        recipeRepo.saveRandomMeal(form);
        model.addAttribute("title", form.getFirst("title").toLowerCase());

        return "save";

    }

   
    
}
