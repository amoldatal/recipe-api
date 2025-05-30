package com.example.recipe_api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipe_api.entity.Recipe;
import com.example.recipe_api.service.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Recipe recipe) {
        if (recipe.getTitle() == null || recipe.getMakingTime() == null || recipe.getServes() == null ||
                recipe.getIngredients() == null || recipe.getCost() == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Recipe creation failed!",
                    "required", "title, making_time, serves, ingredients, cost"
            ));
        }
        Recipe created = service.createRecipe(recipe);
        return ResponseEntity.ok(Map.of(
                "message", "Recipe successfully created!",
                "recipe", List.of(created)
        ));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(Map.of("recipes", service.getAllRecipes()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service.getRecipe(id)
                .map(r -> ResponseEntity.ok(Map.of("message", "Recipe details by id", "recipe", List.of(r))))
                .orElse(ResponseEntity.status(404).body(Map.of("message", "No recipe found")));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Recipe updated) {
        return service.updateRecipe(id, updated)
                .map(r -> ResponseEntity.ok(Map.of("message", "Recipe successfully updated!", "recipe", List.of(r))))
                .orElse(ResponseEntity.status(404).body(Map.of("message", "No recipe found")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (service.deleteRecipe(id)) {
            return ResponseEntity.ok(Map.of("message", "Recipe successfully removed!"));
        }
        return ResponseEntity.status(404).body(Map.of("message", "No recipe found"));
    }
}

