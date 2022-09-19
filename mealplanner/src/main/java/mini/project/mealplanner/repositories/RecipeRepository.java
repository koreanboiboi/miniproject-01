package mini.project.mealplanner.repositories;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import mini.project.mealplanner.model.RandomRecipe;

@Repository
public class RecipeRepository {

    @Autowired
    private RedisTemplate<Object,String> redisTemp;

    public void saveRecipe(RandomRecipe recipe){

        redisTemp.opsForValue().set(recipe.getId(), recipe.toJson().toString());
        redisTemp.expire(recipe.getId(), Duration.ofMinutes(5));
        
    }

    public void saveRecipe(List<RandomRecipe> randomRecipe){
        Map<Object,String> map = new HashMap<>();
        for(RandomRecipe recipe: randomRecipe)
        map.put(recipe.getId(), recipe.toJson().toString());
        redisTemp.opsForValue().multiSet(map);

        for (Object id: map.keySet())
        redisTemp.expire(id, Duration.ofMinutes(5));

    }
    
    public Optional<RandomRecipe> get(Object id){

        if(!redisTemp.hasKey(id))
        return Optional.empty();

        String data = redisTemp.opsForValue().get(id);
        return Optional.of(RandomRecipe.create(data));
    }
}
