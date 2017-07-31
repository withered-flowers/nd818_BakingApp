package com.example.standard.bakingapp.backend.pojo;

import com.google.gson.annotations.SerializedName;

public class RecipeIngredient {
  @SerializedName("quantity")
  private float ingredientQuantity;

  @SerializedName("measure")
  private String ingredientMeasure;

  @SerializedName("ingredient")
  private String ingredientName;
  
  public float getIngredientQuantity() {
    return ingredientQuantity;
  }
  
  public void setIngredientQuantity(float ingredientQuantity) {
    this.ingredientQuantity = ingredientQuantity;
  }
  
  public String getIngredientMeasure() {
    return ingredientMeasure;
  }
  
  public void setIngredientMeasure(String ingredientMeasure) {
    this.ingredientMeasure = ingredientMeasure;
  }
  
  public String getIngredientName() {
    return ingredientName;
  }
  
  public void setIngredientName(String ingredientName) {
    this.ingredientName = ingredientName;
  }
}