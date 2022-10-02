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
import mini.project.mealplanner.services.SearchRecipeService;

@Controller
@RequestMapping(path={"/result"})
public class SearchRecipeController {


    @Autowired
    private SearchRecipeService searchSvc;

    @GetMapping
    public String searchRecipe(Model model, @RequestParam String query,
                                             HttpSession session){

        List<SearchRecipe> searchRecipe = searchSvc.getSearch(query);
        session.setAttribute("searchRecipe", searchRecipe);
        model.addAttribute("query", query);
        model.addAttribute("searchRecipe", searchRecipe);
        

        return "result";
    }

    @PostMapping
    public String saveSearchRecipe(@RequestBody MultiValueMap<String,String> form, Model model, HttpSession session){

        List<SearchRecipe> searchList = (List<SearchRecipe>) session.getAttribute("searchRecipe");
        List<String> save = form.get("title");
        List<SearchRecipe> toSave = searchList.stream().filter(search -> {
            for(String i : save)
            if(i.equals(search.getTitle()))
            return true;
            return false;
        }).toList();

        if(toSave.size()>0)
        searchSvc.save(toSave);
        
        model.addAttribute("title", searchList);

        return "save";

    }

   
    
}
