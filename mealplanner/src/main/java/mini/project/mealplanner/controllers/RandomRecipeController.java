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

import mini.project.mealplanner.model.RandomRecipe;
import mini.project.mealplanner.services.RandomRecipeService;

@Controller
@RequestMapping(path = {"/random"})
public class RandomRecipeController {

    @Autowired
    private RandomRecipeService rmdSvc;

    @GetMapping
    public String getRandomRecipe(Model model, HttpSession session){

        List<RandomRecipe> randomRecipe = rmdSvc.getRandomRecipe();
        session.setAttribute("randomRecipe", randomRecipe);
        model.addAttribute("randomRecipe",randomRecipe);
   
        return "random";
    }

    @PostMapping
    public String saveRandomRecipe(@RequestBody MultiValueMap<String,String> form, Model model, HttpSession session){

        List<RandomRecipe> randomList = (List<RandomRecipe>) session.getAttribute("randomRecipe");
        List<String> save = form.get("title");
        List<RandomRecipe> toSave = randomList.stream().filter(search -> {
            for(String i : save)
            if(i.equals(search.getTitle()))
            return true;
            return false;
        }).toList();

        if(toSave.size()>0)
        rmdSvc.save(toSave);

        model.addAttribute("title", randomList);

        return "save";
    }

}
