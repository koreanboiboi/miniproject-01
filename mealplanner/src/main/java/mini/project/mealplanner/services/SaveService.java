package mini.project.mealplanner.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini.project.mealplanner.model.RandomRecipe;
import mini.project.mealplanner.repositories.RecipeRepository;

@Service
public class SaveService {

    @Autowired
    private RecipeRepository recipeRepo;

    public void save(List<RandomRecipe> toSave){

        recipeRepo.saveRecipe(toSave);

    }

    public Optional<RandomRecipe> get(Long id){
        return recipeRepo.get(id);
    }
    
}
