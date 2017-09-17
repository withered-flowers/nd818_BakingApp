package com.example.standard.bakingapp.backend.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fetcher {
  final String BASE_URL = "http://go.udacity.com/";

  //Make OkHttpClient for enable redirect page
  OkHttpClient client = new OkHttpClient.Builder()
      .followRedirects(true)
      .followSslRedirects(true)
      .build();
  
  Retrofit theFetcher;
  
  public Fetcher() {
    theFetcher = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build();
  }
  
  public Retrofit getFetcher() {
    return theFetcher;
  }
}
