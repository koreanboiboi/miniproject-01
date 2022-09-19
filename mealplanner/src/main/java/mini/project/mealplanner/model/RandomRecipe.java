package mini.project.mealplanner.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class RandomRecipe {

    

    private String title;
    private String sourceUrl;
    private String image;
    private String instructions;
    private Long id;
    private Long readyInMinutes;
    private Long servings;

    public Long getReadyInMinutes() {
        return readyInMinutes;
    }
    public void setReadyInMinutes(Long readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }
    
    public Long getServings() {
        return servings;
    }
    public void setServings(Long servings) {
        this.servings = servings;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSourceUrl() {
        return sourceUrl;
    }
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public static RandomRecipe create(String jsonString){

       try(StringReader strReader = new StringReader(jsonString)){
        JsonReader reader = Json.createReader(strReader);
        
        return create(reader.readObject());
       }

    }

    public static RandomRecipe create(JsonObject jo){

        RandomRecipe randomRecipe = new RandomRecipe();
        randomRecipe.setTitle(jo.getString("title"));
        randomRecipe.setImage(jo.getString("image"));
        randomRecipe.setInstructions(jo.getString("instructions"));
        randomRecipe.setSourceUrl(jo.getString("sourceUrl"));
        randomRecipe.setId(jo.getJsonNumber("id").longValue());
        randomRecipe.setReadyInMinutes(jo.getJsonNumber("readyInMinutes").longValue());
        randomRecipe.setServings(jo.getJsonNumber("servings").longValue());

        return randomRecipe;

        
    }

    public JsonObject toJson(){

        return Json.createObjectBuilder()
                   
                   .add("title", title)
                   .add("image", image)
                   .add("instructions", instructions)
                   .add("sourceUrl", sourceUrl)
                   .add("id", id)
                   .add("servings", servings)
                   .add("readyInMinutes", readyInMinutes)
                   .build();
                
    }
  
    
   
}
