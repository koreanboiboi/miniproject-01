// package mini.project.miniproject.repositories;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Repository;

// import mini.project.miniproject.models.Products;

// @Repository
// public class ProductRepository {

//     @Autowired
//     private RedisTemplate<String,String> redisTemplate;

//     public Optional<Products> getSingleProduct(String id){
//         if(!redisTemplate.hasKey(id))
//         return Optional.empty();
//         String singleProduct = redisTemplate.opsForValue().get(id);
//         return Optional.of(Products.create(singleProduct));
//     }
    
// }
