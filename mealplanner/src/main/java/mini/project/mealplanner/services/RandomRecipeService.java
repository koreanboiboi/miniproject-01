package mini.project.mealplanner.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

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

@Service
public class RandomRecipeService {
    
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


}
