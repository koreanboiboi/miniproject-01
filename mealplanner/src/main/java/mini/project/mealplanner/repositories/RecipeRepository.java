package mini.project.mealplanner.repositories;

import java.util.HashMap;
import java.util.LinkedList;
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

    

    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String,String> redisTemp;

    public void saveUser(String name,String password){

        ValueOperations<String, String> ops = redisTemp.opsForValue();
        ops.set(name, password.format("Password:%s", password));

    }

    public void saveMeal(List<SearchRecipe> searchList){

        Map<String, String> map = new HashMap<>();
        for (SearchRecipe sr : searchList)
        map.put(sr.getId().toString(), sr.toJson().toString());
        redisTemp.opsForValue().multiSet(map);
    }


    public void saveRandomMeal(List<RandomRecipe> randomList){

        Map<String, String> map = new HashMap<>();
        for (RandomRecipe rr : randomList)
        map.put(rr.getId().toString(), rr.toJson().toString());
        redisTemp.opsForValue().multiSet(map);

    }

    
    public void clear(){

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

        Set<String> user = redisTemp.keys("0*");
        Set<String> user1 = redisTemp.keys("1*");
        Set<String> user2 = redisTemp.keys("2*");
        Set<String> user3 = redisTemp.keys("3*");
        Set<String> user4 = redisTemp.keys("4*");
        Set<String> user5 = redisTemp.keys("5*");
        Set<String> user6 = redisTemp.keys("6*");
        Set<String> user7 = redisTemp.keys("7*");
        Set<String> user8 = redisTemp.keys("8*");
        Set<String> user9 = redisTemp.keys("9*");

        Set<String> userName = redisTemp.keys("*");

        List<String> userList = new LinkedList<>();
        userList.addAll(userName);
        userList.removeAll(user);
        userList.removeAll(user1);
        userList.removeAll(user2);
        userList.removeAll(user3);
        userList.removeAll(user4);
        userList.removeAll(user5);
        userList.removeAll(user6);
        userList.removeAll(user7);
        userList.removeAll(user8);
        userList.removeAll(user9);
        
       
        
        Map<String, String> userKey = new HashMap<>();
        userKey.put(userList.toString(),"Last User Saved Item:" + idList.toString() + " Thank you for using Meal Planner please visit again");
        redisTemp.opsForValue().multiSet(userKey);
        
        redisTemp.delete(idList);
        redisTemp.delete(userList);
        

    }

    public Object get(String id) {
        ValueOperations<String,String> ops = redisTemp.opsForValue();

        return ops.get(id.toString());
    }
}