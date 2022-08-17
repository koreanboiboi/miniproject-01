package mini.project.miniproject01.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import mini.project.miniproject01.models.Products;
import mini.project.miniproject01.services.ProductService;

@RestController
@RequestMapping(path = "/products" , produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsRestController {

    @Autowired
    private ProductService productSvc;

    @GetMapping(path="{id}")
    public ResponseEntity<String> getSingleProduct(Model model, @PathVariable(name="id") String id){
        Optional<Products> opt = productSvc.getSingleProduct(id);
        if(opt.isEmpty()){
            JsonObject payload = Json.createObjectBuilder()
                                    .add("error", "cannot find item number %s".formatted(id))
                                    .build();
                                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                        .body(payload.toString());
        }
        Products singleProduct = opt.get();
        return ResponseEntity.ok(singleProduct.toJson().toString());
    }
    
}
