package com.example.recipe_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipe_api.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
