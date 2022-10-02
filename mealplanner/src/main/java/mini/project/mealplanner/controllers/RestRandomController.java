package mini.project.mealplanner.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import mini.project.mealplanner.model.RandomRecipe;
import mini.project.mealplanner.services.RandomRecipeService;

@RestController
@RequestMapping(path = {"/random"} , produces = MediaType.APPLICATION_JSON_VALUE)
public class RestRandomController {


    @Autowired
    private RandomRecipeService randomSvc;

    @GetMapping(value = "{id}")
    public ResponseEntity<String> getSearch(@PathVariable String id){

        Optional<RandomRecipe> opt = randomSvc.randomSearchById(id);
        if(opt.isEmpty()){
            JsonObject error = Json.createObjectBuilder()
                                .add("error", "Cannot find food ID %s".formatted(id)).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        }

        RandomRecipe random = opt.get();
        return ResponseEntity.ok(random.toJson().toString());

    }
    
    
}
