package mini.project.mealplanner.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class SearchRecipe {

    private Long id;
    private String title;
    private String restaurantChain;
    private String image;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getRestaurantChain() {
        return restaurantChain;
    }
    public void setRestaurantChain(String restaurantChain) {
        this.restaurantChain = restaurantChain;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }



    public SearchRecipe(JsonObject jo) {

        this.id= jo.getJsonNumber("id").longValue();
        this.title=jo.getString("title");
        this.image=jo.getString("image");
        this.restaurantChain=jo.getString("restaurantChain");
       

    }

    public static SearchRecipe create(String jsonString){

        try(StringReader strReader = new StringReader(jsonString)){
         JsonReader reader = Json.createReader(strReader);
         
         return create(reader.readObject());
        }
 
     }
 
     public static SearchRecipe create(JsonObject jo){
 
         SearchRecipe searchRecipe = new SearchRecipe(jo);
         searchRecipe.setTitle(jo.getString("title"));
         searchRecipe.setImage(jo.getString("image"));
         searchRecipe.setId(jo.getJsonNumber("id").longValue());
         searchRecipe.setRestaurantChain(jo.getString("restaurantChain"));
   
 
         return searchRecipe;
 
         
     }
 
     public JsonObject toJson(){
 
         return Json.createObjectBuilder()
                    
                    .add("title", title)
                    .add("image", image)
                    .add("id", id)
                    .add("restaurantChain", restaurantChain)
                    .build();
                 
     }
    
}
