package mini.project.mealplanner.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

@Repository
public class RecipeRepository {

    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String,String> redisTemp;

    public void saveUser(String name){

        ValueOperations<String, String> ops = redisTemp.opsForValue();
        ops.set("UserName", name);

    }

    public void saveMeal(MultiValueMap<String,String> form){

        ValueOperations<String,String> ops = redisTemp.opsForValue();
        ops.set("SavedMeal", form.getFirst("title"));

    }

    public void saveRandomMeal(MultiValueMap<String,String> form){

        ValueOperations<String,String> ops = redisTemp.opsForValue();
        ops.set("SavedMeal", form.getFirst("title"));

    }

    public void resetUser(){
        ValueOperations<String,String> ops = redisTemp.opsForValue();
        ops.getAndDelete("UserName");
        ops.getAndDelete("SavedMeal");
    }
}