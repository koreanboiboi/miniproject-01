package mini.project.miniproject01.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import mini.project.miniproject01.models.Store;


@Service
public class StoreService {

    private static final String storeURL = "https://fakestoreapi.com/";


    public List<Store> getAllProducts(){

        String productsUrl = UriComponentsBuilder.fromUriString(storeURL)
                                                 .queryParam("products", "products")
                                                 .toUriString(); 

        RequestEntity<Void> requestProduct = RequestEntity.get(productsUrl).build();
        RestTemplate restTemp = new RestTemplate();
        ResponseEntity<String> responseProduct = restTemp.exchange(requestProduct, String.class);

        String allProducts = responseProduct.getBody();
        Reader strReader = new StringReader(allProducts);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jsonObject = jsonReader.readObject();
        JsonArray jsonArray = jsonObject.asJsonArray();

        JsonObject ratingObject = jsonReader.readObject();
        JsonObject newObject = ratingObject.getJsonObject("rating");
        

        List<Store> storeList = new LinkedList<>();
        for(int i = 0; i<jsonArray.size(); i++){
            JsonObject jo = jsonArray.getJsonObject(i);
            storeList.add(Store.create(jo));
        }

        for(int j=0; j<newObject.size(); j++){
            JsonObject object = (JsonObject) newObject.get(j);
            storeList.add(Store.createRating(object));
        }

        return storeList;
    }
    
}
