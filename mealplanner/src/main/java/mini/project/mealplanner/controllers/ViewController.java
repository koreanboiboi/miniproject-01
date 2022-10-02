package mini.project.mealplanner.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
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

        List<String> idList = new LinkedList<>();

        Set<String> id = redisTemp.keys("0*");
        Set<String> id1 = redisTemp.keys("1*");
        Set<String> id2 = redisTemp.keys("2*");
        Set<String> id3 = redisTemp.keys("3*");
        Set<String> id4 = redisTemp.keys("4*");
        Set<String> id5 = redisTemp.keys("5*");
        Set<String> id6 = redisTemp.keys("6*");
        Set<String> id7 = redisTemp.keys("7*");
        Set<String> id8 = redisTemp.keys("8*");
        Set<String> id9 = redisTemp.keys("9*");

        idList.addAll(id);
        idList.addAll(id1);
        idList.addAll(id2);
        idList.addAll(id3);
        idList.addAll(id4);
        idList.addAll(id5);
        idList.addAll(id6);
        idList.addAll(id7);
        idList.addAll(id8);
        idList.addAll(id9);


        model.addAttribute("item", idList.toString());

        return "view";

    }
}
