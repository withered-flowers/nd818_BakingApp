package com.example.standard.bakingapp.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.Recipe;
import com.example.standard.bakingapp.backend.retrofit.APIEndpoint;
import com.example.standard.bakingapp.backend.retrofit.Fetcher;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMain extends AppCompatActivity
  implements AdapterRecipeList.clickHandler {

  private boolean isTwoPanel;

  private RecyclerView rvwListRecipe;

  @Override
  public void onCardViewClick(Recipe obj) {
    Toast.makeText(getApplicationContext(), obj.getRecipeName(), Toast.LENGTH_LONG).show();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if(findViewById(R.id.content_frame_recipe_detail) != null) {
      isTwoPanel = true;
    }

    rvwListRecipe = (RecyclerView) findViewById(R.id.content_frame_recipe_list);
    rvwListRecipe.setHasFixedSize(true);

    /* Fetch API Content Here */
    Fetcher theFetcher = new Fetcher();
    APIEndpoint theEndpoint = theFetcher.getFetcher().create(APIEndpoint.class);
    
    Call<List<Recipe>> callListRecipe = theEndpoint.getListRecipe("android-baking-app-json");
    
    callListRecipe.enqueue(new Callback<List<Recipe>>() {
      @Override
      public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
        List<Recipe> listRecipe = response.body();
        
        if(listRecipe != null) {
          String allRecipeName = "";

          for(int i=0; i<listRecipe.size(); i++) {
            allRecipeName += listRecipe.get(i).getRecipeName() + ", ";
          }

          AdapterRecipeList adpRecipeList = new AdapterRecipeList(listRecipe);
          adpRecipeList.setOnCardViewClick(ActivityMain.this);

          rvwListRecipe.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
          rvwListRecipe.setAdapter(adpRecipeList);

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
