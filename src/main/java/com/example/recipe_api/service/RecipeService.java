package com.example.recipe_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipe_api.entity.Recipe;
import com.example.recipe_api.repository.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository repo;

    public Recipe createRecipe(Recipe recipe) {
        return repo.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return repo.findAll();
    }

    public Optional<Recipe> getRecipe(Long id) {
        return repo.findById(id);
    }

    public Optional<Recipe> updateRecipe(Long id, Recipe newRecipe) {
        return repo.findById(id).map(r -> {
            r.setTitle(newRecipe.getTitle());
            r.setMakingTime(newRecipe.getMakingTime());
            r.setServes(newRecipe.getServes());
            r.setIngredients(newRecipe.getIngredients());
            r.setCost(newRecipe.getCost());
            return repo.save(r);
        });
    }

    public boolean deleteRecipe(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
