package com.example.standard.bakingapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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

  private Button btnPrev;
  private Button btnNext;
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
      if (findViewById(R.id.content_recipe_detail_framestep) != null) {
        if (position == 0) {
          //RECIPE INGREDIENT HERE
          String textIngredient = "";

          for (int i = 0; i < currentRecipeListIngredients.size(); i++) {
            textIngredient += currentRecipeListIngredients.get(i).getIngredientQuantity() + " ";
            textIngredient += currentRecipeListIngredients.get(i).getIngredientMeasure() + " of ";
            textIngredient += currentRecipeListIngredients.get(i).getIngredientName() + "\n";
          }

          Fragment fr = new FragmentRecipeDetailRightText();

          bundle.putString(StaticValue.KEY_STRING_RECIPEINGREDIENT, textIngredient);

          fr.setArguments(bundle);

          FragmentManager fm = getSupportFragmentManager();
          FragmentTransaction ft = fm.beginTransaction();
          ft.replace(R.id.content_recipe_detail_framestep, fr);
          ft.commit();
        } else if (position > 0) {
          //RECIPE STEP HERE
          Fragment fr = new FragmentRecipeDetailRightMovie();

          bundle.putParcelable(StaticValue.KEY_OBJECT_RECIPESTEP, currentRecipeListSteps.get(position - 1));

          fr.setArguments(bundle);

          FragmentManager fm = getSupportFragmentManager();
          FragmentTransaction ft = fm.beginTransaction();
          ft.replace(R.id.content_recipe_detail_framestep, fr);
          ft.commit();
        }

        setViewVisibility(position, currentRecipeListSteps.size());
      }
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

    btnPrev = (Button) findViewById(R.id.content_recipe_detail_buttonprev);
    btnNext = (Button) findViewById(R.id.content_recipe_detail_buttonnext);

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

  void setViewVisibility(int curr, int max) {
    if (curr == 0) {
      btnPrev.setVisibility(View.INVISIBLE);
      btnNext.setVisibility(View.VISIBLE);
    } else if (curr == max) {
      btnPrev.setVisibility(View.VISIBLE);
      btnNext.setVisibility(View.INVISIBLE);
    } else {
      btnPrev.setVisibility(View.VISIBLE);
      btnNext.setVisibility(View.VISIBLE);
    }
  }
}
