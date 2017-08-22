package com.example.standard.bakingapp.backend.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RecipeIngredient implements Parcelable {
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

  public static final Creator<RecipeIngredient> CREATOR = new Creator<RecipeIngredient>() {
    @Override
    public RecipeIngredient createFromParcel(Parcel in) {
      return new RecipeIngredient(in);
    }

    @Override
    public RecipeIngredient[] newArray(int size) {
      return new RecipeIngredient[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeFloat(getIngredientQuantity());
    dest.writeString(getIngredientMeasure());
    dest.writeString(getIngredientName());
  }

  public RecipeIngredient() {
  }

  public RecipeIngredient(Parcel in) {
    setIngredientQuantity(in.readFloat());
    setIngredientMeasure(in.readString());
    setIngredientName(in.readString());
  }
}