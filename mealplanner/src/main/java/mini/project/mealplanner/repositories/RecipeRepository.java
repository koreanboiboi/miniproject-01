package mini.project.mealplanner.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import mini.project.mealplanner.model.RandomRecipe;
import mini.project.mealplanner.model.SearchRecipe;

@Repository
public class RecipeRepository {

    Map<String, String> map = new HashMap<>();

    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String,String> redisTemp;

    public void saveUser(String name){

        ValueOperations<String, String> ops = redisTemp.opsForValue();
        ops.set("UserName", name);

    }

    public void saveMeal(List<SearchRecipe> searchList){

        
        for (SearchRecipe sr : searchList)
        map.put(sr.getId().toString(), sr.toJson().toString());
        redisTemp.opsForValue().multiSet(map);
    }


    public void saveRandomMeal(List<RandomRecipe> randomList){

        for (RandomRecipe rr : randomList)
        map.put(rr.getId().toString(), rr.toJson().toString());
        redisTemp.opsForValue().multiSet(map);

    }

    
    public void clear(){

        Set<String> keys = redisTemp.keys("*");

        for(String key : keys){
            redisTemp.delete(key);
        }

    }

    public Object get(String id) {
        ValueOperations<String,String> ops = redisTemp.opsForValue();

        return ops.get(id.toString());
    }
}