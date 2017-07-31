package com.example.standard.bakingapp.backend.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
  URL - REDIRECTED : http://go.udacity.com/android-baking-app-json
  URL - ORIGINAL   : https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json
*/

public class Recipe {
  @SerializedName("id")
  private int recipeId;

  @SerializedName("name")
  private String recipeName;
  
  @SerializedName("ingredients")
  private List<RecipeIngredient> recipeListIngredients;

  @SerializedName("steps")
  private List<RecipeStep> recipeListSteps;
  
  @SerializedName("servings")
  private int recipeServings;
  
  @SerializedName("image")
  private String recipeImage;
  
  public int getRecipeId() {
    return recipeId;
  }
  
  public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
  }
  
  public String getRecipeName() {
    return recipeName;
  }
  
  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }
  
  public List<RecipeIngredient> getRecipeListIngredients() {
    return recipeListIngredients;
  }
  
  public void setRecipeListIngredients(List<RecipeIngredient> recipeListIngredients) {
    this.recipeListIngredients = recipeListIngredients;
  }
  
  public List<RecipeStep> getRecipeListSteps() {
    return recipeListSteps;
  }
  
  public void setRecipeListSteps(List<RecipeStep> recipeListSteps) {
    this.recipeListSteps = recipeListSteps;
  }
  
  public int getRecipeServings() {
    return recipeServings;
  }
  
  public void setRecipeServings(int recipeServings) {
    this.recipeServings = recipeServings;
  }
  
  public String getRecipeImage() {
    return recipeImage;
  }
  
  public void setRecipeImage(String recipeImage) {
    this.recipeImage = recipeImage;
  }
}