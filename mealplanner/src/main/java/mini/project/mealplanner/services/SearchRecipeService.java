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
import mini.project.mealplanner.model.SearchRecipe;
import mini.project.mealplanner.repositories.RecipeRepository;

@Service
public class SearchRecipeService {

    @Autowired
    private RecipeRepository recipeRepo;


    private static final String URL = "https://api.spoonacular.com/food/menuItems/search";

    @Value("${apiKey}")
    private String key;

    public List<SearchRecipe> getSearch (String query, Long maxCalories){

        
         String searchUrl = UriComponentsBuilder.fromUriString(URL)
                                             .queryParam("query", query)
                                             .queryParam("maxCalories", maxCalories)
                                             .queryParam("number", 5)
                                             .queryParam("apiKey", key)
                                             .toUriString();

         RequestEntity<Void> req = RequestEntity.get(searchUrl).build();
         RestTemplate restTemp = new RestTemplate();
         ResponseEntity<String> resp = restTemp.exchange(req, String.class);
                                     
        String payload = resp.getBody();

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jsonObject = jsonReader.readObject();
        JsonArray data = jsonObject.getJsonArray("menuItems");

        List<SearchRecipe> searchList = new LinkedList<>();

        for(int i = 0 ; i < data.size(); i++){
            JsonObject jo = data.getJsonObject(i);
            searchList.add(SearchRecipe.create(jo));
        }

        return searchList;
    }
    
    public void save(List<SearchRecipe> toSave) {
		recipeRepo.saveMeal(toSave);
	}

    public Optional<SearchRecipe> saveSearchById(String id) {
        Object result = recipeRepo.get(id);
        if(result == null)
        return Optional.empty();

        return Optional.of(SearchRecipe.create((String)result));
    }
    

  
}
