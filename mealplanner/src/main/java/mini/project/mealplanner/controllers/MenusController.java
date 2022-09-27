package mini.project.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/menus"})
public class MenusController {

    
    @GetMapping
    public String menus(){

        return "menus";
    }
    
}
