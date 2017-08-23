package com.example.standard.bakingapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
  private Recipe currentRecipe;
  private List<RecipeIngredient> currentRecipeListIngredients;
  private List<RecipeStep> currentRecipeListSteps;

  @Override
  public void onClickAdapterRecipeDetailLeft(int position) {
    Bundle bundle = new Bundle();

    bundle.putInt(StaticValue.KEY_INT_POSITION_CURR, position);
    bundle.putInt(StaticValue.KEY_INT_POSITION_MAX, currentRecipeListSteps.size());
    bundle.putParcelable(StaticValue.KEY_OBJECT_RECIPE, currentRecipe);

    if (isTwoPanel) {
      //TWO PANEL IMPLEMENTATION
    } else {
      //ONE PANEL IMPLEMENTATION
      Intent i = new Intent(this, ActivityStep.class);
      i.putExtras(bundle);

      startActivity(i);
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
      currentRecipe = getIntent().getExtras().getParcelable(StaticValue.KEY_OBJECT_RECIPE);

      if (currentRecipe != null) {
        currentRecipeListIngredients = currentRecipe.getRecipeListIngredients();
        currentRecipeListSteps = currentRecipe.getRecipeListSteps();

        AdapterRecipeDetailLeft adpRecipeDetail_Left = new AdapterRecipeDetailLeft(currentRecipeListSteps);
        adpRecipeDetail_Left.setOnListItemViewClick(ActivityDetail.this);

        rvwRecipeDetail.setLayoutManager(new LinearLayoutManager(this));
        rvwRecipeDetail.setAdapter(adpRecipeDetail_Left);
      }
    }
  }
}
