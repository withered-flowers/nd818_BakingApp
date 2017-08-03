package com.example.standard.bakingapp.backend.retrofit;

import com.example.standard.bakingapp.backend.pojo.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIEndpoint {
  @GET
  Call<List<Recipe>> getListRecipe(@Url String theUrl);
}
