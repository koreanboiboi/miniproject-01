package mini.project.mealplanner.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import mini.project.mealplanner.model.RandomRecipe;
import mini.project.mealplanner.repositories.RecipeRepository;

@Service
public class RandomRecipeService {

    @Autowired
    private RecipeRepository recipeRepo;
    
    private static final String RandomURL = "https://api.spoonacular.com/recipes/random";
    
    @Value("${apiKey}")
    private String apikey;

    public List<RandomRecipe> getRandomRecipe(){

        String randomUrl = UriComponentsBuilder.fromUriString(RandomURL)
                                               .queryParam("number", 1)
                                               .queryParam("apiKey", apikey)
                                               .toUriString();

        RequestEntity<Void> req = RequestEntity.get(randomUrl).build();
        RestTemplate restTemp = new RestTemplate();
        ResponseEntity<String> resp = restTemp.exchange(req, String.class);

        String payload = resp.getBody();
        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jsonObject = jsonReader.readObject();
        JsonArray data = jsonObject.getJsonArray("recipes");

        List<RandomRecipe> randomList = new LinkedList<>();

        for(int i = 0 ; i < data.size(); i++){
            JsonObject jo = data.getJsonObject(i);
            randomList.add(RandomRecipe.create(jo));
        }

        return randomList;

    }

    public void save(List<RandomRecipe> toSave) {
		recipeRepo.saveRandomMeal(toSave);
	}

    public Optional<RandomRecipe> saveSearchById(String id) {
        Object result = recipeRepo.get(id);
        if(result == null)
        return Optional.empty();

        return Optional.of(RandomRecipe.create((String)result));
    }


}
