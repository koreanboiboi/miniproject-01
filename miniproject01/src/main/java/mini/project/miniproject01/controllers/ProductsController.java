package mini.project.miniproject01.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import mini.project.miniproject01.models.Products;
import mini.project.miniproject01.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productSvc;

    @GetMapping
    public String getAllProducts(Model model,HttpSession session){

        List<Products> products = productSvc.getAllProducts();
        //session.setAttribute("products", products);
        model.addAttribute("products", products);

        return "products";
        
    }

    @PostMapping
    public String addProducts(@RequestBody MultiValueMap<String,String> form, Model model, HttpSession session){

        //List<Store> addproduct = (List<Store>)session.getAttribute("products");
        String title = form.getFirst("title");
        String price = form.getFirst("price");
        String description = form.getFirst("description");
        String image = form.getFirst("image");
        String category = form.getFirst("category");

        model.addAttribute("title", title);
        model.addAttribute("price", price);
        model.addAttribute("description", description);
        model.addAttribute("image", image);
        model.addAttribute("category", category);
        
        return "products";

    }
    
}
