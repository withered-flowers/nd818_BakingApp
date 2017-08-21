package com.example.standard.bakingapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.Recipe;
import com.example.standard.bakingapp.backend.pojo.RecipeIngredient;
import com.example.standard.bakingapp.backend.pojo.RecipeStep;

import java.util.List;

/**
 * Created by standard on 8/12/17.
 */

public class ActivityDetail extends AppCompatActivity
  implements AdapterRecipeDetailLeft.clickHandler {

  private static final String PARCEL_TAG = "RECIPE";

  private boolean isTwoPanel;

  private RecyclerView rvwRecipeDetail;

  @Override
  public void onClickAdapterRecipeDetailLeft(RecipeStep recipeStep, int position) {
    String theText;
    if(recipeStep == null) {
      theText = "Recipe Ingredient";
    }
    else {
      theText = recipeStep.getStepShortDescription();
    }
    Toast.makeText(getApplicationContext(), theText, Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    isTwoPanel = findViewById(R.id.content_recipe_detail_framestep) != null;
    rvwRecipeDetail = (RecyclerView) findViewById(R.id.content_recipe_detail_framerecipe);
    rvwRecipeDetail.setHasFixedSize(true);

    if (getIntent().getExtras() != null) {
      Recipe currentRecipe = getIntent().getExtras().getParcelable(PARCEL_TAG);

      if (currentRecipe != null) {
        List<RecipeIngredient> currentRecipeListIngredients = currentRecipe.getRecipeListIngredients();
        List<RecipeStep> currentRecipeListSteps = currentRecipe.getRecipeListSteps();

        Log.d("TEST", String.valueOf(currentRecipeListSteps.size()));

        AdapterRecipeDetailLeft adpRecipeDetail_Left = new AdapterRecipeDetailLeft(currentRecipeListSteps);
        adpRecipeDetail_Left.setOnListItemViewClick(ActivityDetail.this);

        rvwRecipeDetail.setLayoutManager(new LinearLayoutManager(this));
        rvwRecipeDetail.setAdapter(adpRecipeDetail_Left);
      }
    }
  }
}
