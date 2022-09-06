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

        StringReader strReader = new StringReader(jsonString);
        JsonReader reader = Json.createReader(strReader);
        
        return create(reader.readObject());

    }

    public static RandomRecipe create(JsonObject jo){

        RandomRecipe randomRecipe = new RandomRecipe();
        randomRecipe.setTitle(jo.getString("title"));
        randomRecipe.setImage(jo.getString("image"));
        randomRecipe.setInstructions(jo.getString("instructions"));
        randomRecipe.setSourceUrl(jo.getString("sourceUrl"));

        return randomRecipe;

        
    }

    public JsonObject toJson(){

        return Json.createObjectBuilder()
                   
                   .add("title", title)
                   .add("image", image)
                   .add("instructions", instructions)
                   .add("sourceUrl", sourceUrl)
                   .build();
                
    }
    
   
}
