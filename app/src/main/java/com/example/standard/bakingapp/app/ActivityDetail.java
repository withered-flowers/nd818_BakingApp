package com.example.standard.bakingapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.Recipe;
import com.example.standard.bakingapp.backend.pojo.RecipeIngredient;
import com.example.standard.bakingapp.backend.pojo.RecipeStep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by standard on 8/12/17.
 */

public class ActivityDetail extends AppCompatActivity
    implements AdapterRecipeDetailLeft.clickHandler {

  private boolean isTwoPanel;

  private RecyclerView rvwRecipeDetail;
  private List<RecipeIngredient> currentRecipeListIngredients;

  @Override
  public void onClickAdapterRecipeDetailLeft(RecipeStep recipeStep, int position) {
    Bundle bundle = new Bundle();


    if (recipeStep == null) {
      bundle.putParcelableArrayList(StaticValue.PARCEL_TAG_03, new ArrayList<Parcelable>(currentRecipeListIngredients));

      //RECIPE INGREDIENT HERE
      if (isTwoPanel) {
        //TODO TWO PANEL RECIPE INGREDIENT HERE
      } else {
        //TODO ONE PANEL (CHANGE ACTIVITY) RECIPE INGREDIENT HERE
        Intent i = new Intent(this, ActivityStep.class);
        i.putExtras(bundle);

        startActivity(i);
      }
    } else {
      //RECIPE STEP HERE
      bundle.putParcelable(StaticValue.PARCEL_TAG_02, recipeStep);

      if (isTwoPanel) {
        //TODO TWO PANEL RECIPE STEP HERE
      } else {
        //TODO ONE PANEL (CHANGE ACTIVITY) RECIPE STEP HERE
        Intent i = new Intent(this, ActivityStep.class);
        i.putExtras(bundle);

        startActivity(i);
      }
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    isTwoPanel = findViewById(R.id.content_recipe_detail_framestep) != null;
    rvwRecipeDetail = (RecyclerView) findViewById(R.id.content_recipe_detail_framerecipe);
    rvwRecipeDetail.setHasFixedSize(true);

    if (getIntent().getExtras() != null) {
      Recipe currentRecipe = getIntent().getExtras().getParcelable(StaticValue.PARCEL_TAG_01);

      if (currentRecipe != null) {
        currentRecipeListIngredients = currentRecipe.getRecipeListIngredients();
        List<RecipeStep> currentRecipeListSteps = currentRecipe.getRecipeListSteps();

        AdapterRecipeDetailLeft adpRecipeDetail_Left = new AdapterRecipeDetailLeft(currentRecipeListSteps);
        adpRecipeDetail_Left.setOnListItemViewClick(ActivityDetail.this);

        rvwRecipeDetail.setLayoutManager(new LinearLayoutManager(this));
        rvwRecipeDetail.setAdapter(adpRecipeDetail_Left);
      }
    }
  }
}
