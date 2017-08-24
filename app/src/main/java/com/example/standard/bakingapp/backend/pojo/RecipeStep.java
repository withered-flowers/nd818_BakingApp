package com.example.standard.bakingapp.backend.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RecipeStep implements Parcelable {
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

  public static final Creator<RecipeStep> CREATOR = new Creator<RecipeStep>() {
    @Override
    public RecipeStep createFromParcel(Parcel in) {
      return new RecipeStep(in);
    }

    @Override
    public RecipeStep[] newArray(int size) {
      return new RecipeStep[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(getStepId());
    dest.writeString(getStepDescription());
    dest.writeString(getStepShortDescription());
    dest.writeString(getStepVideoURL());
    dest.writeString(getStepThumbnailURL());
  }

  public RecipeStep() {
  }

  public RecipeStep(Parcel in) {
    setStepId(in.readInt());
    setStepDescription(in.readString());
    setStepShortDescription(in.readString());
    setStepVideoURL(in.readString());
    setStepThumbnailURL(in.readString());
  }
}