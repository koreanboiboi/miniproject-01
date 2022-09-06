package mini.project.miniproject.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import mini.project.miniproject.models.Products;
// import mini.project.miniproject.repositories.ProductRepository;

@Service
public class ProductService {

    // @Autowired
    // private ProductRepository productRepo;

    private static final String storeURL = "https://fakestoreapi.com/";


    public List<Products> getAllProducts(){

        String productsUrl = UriComponentsBuilder.fromUriString(storeURL)
                                                 .path("products")
                                                 .toUriString(); 

        RequestEntity<Void> requestProduct = RequestEntity.get(productsUrl).build();
        RestTemplate restTemp = new RestTemplate();
        ResponseEntity<String> responseProduct = restTemp.exchange(requestProduct, String.class);

        String allProducts = responseProduct.getBody();
        Reader strReader = new StringReader(allProducts);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonArray data = jsonReader.readArray();

        List<Products> productList = new LinkedList<>();

        for(int i = 0; i< data.size(); i++){
            JsonArray ja = data.getJsonArray(i);
            productList.add(Products.create(ja));
        }

        return productList;
    }

    // public Optional<Products> getSingleProduct(String id) {
	// 	return productRepo.getSingleProduct(id);
	// }
    
}
