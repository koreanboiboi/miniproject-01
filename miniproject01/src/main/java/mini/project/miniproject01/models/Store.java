package mini.project.miniproject01.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Store {
    private String id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private Double rate;
    private Integer count;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String dsecription) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Double getRate() {
        return rate;
    }
    public void setRate(Double rate) {
        this.rate = rate;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }

    public static Store create(String jsonStr){
        StringReader stringReader = new StringReader(jsonStr);
        JsonReader reader = Json.createReader(stringReader);
        return create(reader.readObject());
    }

    public static Store create(JsonObject jo){
        Store store = new Store();
        store.setId(jo.getString("id"));
        store.setTitle(jo.getString("title"));
        store.setCategory(jo.getString("category"));
        store.setDescription(jo.getString("description"));
        store.setImage(jo.getString("image"));
        store.setPrice(jo.getJsonNumber("price").doubleValue());

        return store;
    }

    public static Store createRating(JsonObject object){

        Store rating = new Store();
        rating.setRate(object.getJsonNumber("rate").doubleValue());
        rating.setCount(object.getInt("count"));

        return rating;

    }

    public JsonObject toJson(){
        return Json.createObjectBuilder()
                    .add("id", id)
                    .add("title", title)
                    .add("category", category)
                    .add("description",description)
                    .add("image", image)
                    .add("price", price)
                    .build();
    }

    public JsonObject ratingToJson(){
        return Json.createObjectBuilder()
                    .add("rate", rate)
                    .add("count", count)
                    .build();
    }

                                                                                
    
}
