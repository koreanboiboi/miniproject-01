package mini.project.miniproject.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Products {
    private String id;
    private String title; 
    private String description;
    private String category;
    private String image;
    private Integer count;
    private Double rate;
    private Double price;

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
    public void setDescription(String description) {
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

    public static Products create(JsonArray ja){
        StringReader strReader = new StringReader(ja);
        JsonReader reader = Json.createReader(strReader);
        return create(reader.readObject());
    }
    public static Products create(JsonObject jo) {
        Products products = new Products();
        products.setId(jo.getString("id"));
        products.setTitle(jo.getString("title"));
        products.setDescription(jo.getString("description"));
        products.setCategory(jo.getString("category"));
        products.setImage(jo.getString("image"));
        // products.setCount(jo.getInt("count"));
        // products.setRate(jo.getJsonNumber("rate").doubleValue());
        // products.setPrice(jo.getJsonNumber("price").doubleValue());
        
        return products;
    }
    
    public JsonObject toJson(){
        return Json.createObjectBuilder()
                    .add("id", id)
                    .add("title", title)
                    .add("description",description)
                    .add("category", category)
                    .add("image", image)
                    // .add("count", count)
                    // .add("rate", rate)
                    // .add("price", price)
                    .build();
    }
                                                                                
    
}
