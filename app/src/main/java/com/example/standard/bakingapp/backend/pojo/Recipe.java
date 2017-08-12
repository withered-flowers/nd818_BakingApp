package com.example.standard.bakingapp.backend.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
  URL - REDIRECTED : http://go.udacity.com/android-baking-app-json
  URL - ORIGINAL   : https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json
*/

public class Recipe implements Parcelable {
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

  public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
    @Override
    public Recipe createFromParcel(Parcel in) {
      return new Recipe(in);
    }

    @Override
    public Recipe[] newArray(int size) {
      return new Recipe[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(getRecipeId());
    dest.writeString(getRecipeName());
    dest.writeList(getRecipeListIngredients());
    dest.writeList(getRecipeListSteps());
    dest.writeInt(getRecipeServings());
    dest.writeString(getRecipeImage());
  }

  public Recipe() {}
  public Recipe(Parcel in) {
    setRecipeId(in.readInt());
    setRecipeName(in.readString());
    in.readList(recipeListIngredients, List.class.getClassLoader());
    in.readList(recipeListSteps, List.class.getClassLoader());
    setRecipeServings(in.readInt());
    setRecipeImage(in.readString());
  }
}