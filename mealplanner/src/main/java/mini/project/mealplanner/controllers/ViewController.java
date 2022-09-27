package mini.project.mealplanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String,String> redisTemp;
    
    @GetMapping
    public String viewSavedItem(Model model){

        ValueOperations<String,String> ops = redisTemp.opsForValue();
        Object user = ops.get("UserName");
        Object item = ops.get("SavedMeal");

        model.addAttribute("UserName", user.toString());
        model.addAttribute("SavedMeal", item.toString());

        return "view";

    }
}
