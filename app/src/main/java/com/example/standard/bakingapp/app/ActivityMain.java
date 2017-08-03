package com.example.standard.bakingapp.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.Recipe;
import com.example.standard.bakingapp.backend.retrofit.APIEndpoint;
import com.example.standard.bakingapp.backend.retrofit.Fetcher;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMain extends AppCompatActivity {

  private boolean isTwoPanel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    /* Fetch API Content Here */
    Fetcher theFetcher = new Fetcher();
    APIEndpoint theEndpoint = theFetcher.getFetcher().create(APIEndpoint.class);
    
    Call<List<Recipe>> callListRecipe = theEndpoint.getListRecipe("android-baking-app-json");
    
    callListRecipe.enqueue(new Callback<List<Recipe>>() {
      @Override
      public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
        List<Recipe> theRecipe = response.body();
        
        if(theRecipe != null) {
          String allRecipeName = "";

          for(int i=0; i<theRecipe.size(); i++) {
            allRecipeName += theRecipe.get(i).getRecipeName() + ", ";
          }

          Log.d("RECIPE", "onResponse: " + allRecipeName);
        }
      }
  
      @Override
      public void onFailure(Call<List<Recipe>> call, Throwable t) {
        Log.e("RECIPE", "onFailure: " + t.toString());
      }
    });
    /* End of Fetch API Content */
  }
}
