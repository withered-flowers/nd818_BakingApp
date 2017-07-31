package com.example.standard.bakingapp.backend.pojo;

import com.google.gson.annotations.SerializedName;

public class RecipeStep {
  @SerializedName("id")
  private int stepId;

  @SerializedName("shortDescription")
  private String stepShortDescription;

  @SerializedName("description")
  private String stepDescription;

  @SerializedName("videoURL")
  private String stepVideoURL;

  @SerializedName("thumbnailURL")
  private String stepThumbnailURL;
  
  public int getStepId() {
    return stepId;
  }
  
  public void setStepId(int stepId) {
    this.stepId = stepId;
  }
  
  public String getStepShortDescription() {
    return stepShortDescription;
  }
  
  public void setStepShortDescription(String stepShortDescription) {
    this.stepShortDescription = stepShortDescription;
  }
  
  public String getStepDescription() {
    return stepDescription;
  }
  
  public void setStepDescription(String stepDescription) {
    this.stepDescription = stepDescription;
  }
  
  public String getStepVideoURL() {
    return stepVideoURL;
  }
  
  public void setStepVideoURL(String stepVideoURL) {
    this.stepVideoURL = stepVideoURL;
  }
  
  public String getStepThumbnailURL() {
    return stepThumbnailURL;
  }
  
  public void setStepThumbnailURL(String stepThumbnailURL) {
    this.stepThumbnailURL = stepThumbnailURL;
  }
}