package com.example.standard.bakingapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.Recipe;
import com.example.standard.bakingapp.backend.pojo.RecipeIngredient;
import com.example.standard.bakingapp.backend.pojo.RecipeStep;

import java.util.List;

public class ActivityStep extends AppCompatActivity {

  Button btnPrev;
  Button btnNext;

  TextView tvStep;

  int currPosition;
  int maxPosition;
  Recipe recipe;
  List<RecipeIngredient> recipeIngredients;
  List<RecipeStep> recipeSteps;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_step);

    btnPrev = (Button) findViewById(R.id.content_recipe_detail_item_right_buttonprev);
    btnNext = (Button) findViewById(R.id.content_recipe_detail_item_right_buttonnext);

    tvStep = (TextView) findViewById(R.id.content_recipe_detail_item_right_textview_stepdescription);

    if (getIntent().getExtras() != null) {
      currPosition = getIntent().getExtras().getInt(StaticValue.KEY_INT_POSITION_CURR);
      maxPosition = getIntent().getExtras().getInt(StaticValue.KEY_INT_POSITION_MAX);
      recipe = getIntent().getExtras().getParcelable(StaticValue.KEY_OBJECT_RECIPE);

      recipeIngredients = recipe.getRecipeListIngredients();
      recipeSteps = recipe.getRecipeListSteps();

      if (currPosition == 0) {
        if (recipeIngredients != null) {
          //INGREDIENT HERE
          String textIngredient = "";

          for (int i = 0; i < recipeIngredients.size(); i++) {
            textIngredient += recipeIngredients.get(i).getIngredientQuantity() + " ";
            textIngredient += recipeIngredients.get(i).getIngredientMeasure() + " of ";
            textIngredient += recipeIngredients.get(i).getIngredientName() + "\n";
          }

          Fragment fr = new FragmentRecipeDetailRightText();

          Bundle bundle = new Bundle();

          bundle.putString(StaticValue.KEY_STRING_RECIPEINGREDIENT, textIngredient);

          fr.setArguments(bundle);

          initializeFragment(fr);
        }
      } else if (currPosition > 0) {
        if (recipeSteps != null) {
          //STEP HERE
          Fragment fr = new FragmentRecipeDetailRightMovie();

          Bundle bundle = new Bundle();

          bundle.putParcelable(StaticValue.KEY_OBJECT_RECIPESTEP, recipeSteps.get(currPosition - 1));

          fr.setArguments(bundle);

          initializeFragment(fr);
        }
      }

      setViewVisibility(currPosition, maxPosition);
    }

    btnNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        initializeButtonActivity(currPosition + 1);
      }
    });

    btnPrev.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        initializeButtonActivity(currPosition - 1);
      }
    });
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

  void initializeButtonActivity(int position) {
    Bundle bundle = new Bundle();

    bundle.putInt(StaticValue.KEY_INT_POSITION_CURR, position);
    bundle.putInt(StaticValue.KEY_INT_POSITION_MAX, maxPosition);
    bundle.putParcelable(StaticValue.KEY_OBJECT_RECIPE, recipe);

    Intent i = new Intent(ActivityStep.this, ActivityStep.class);
    i.putExtras(bundle);

    startActivity(i);
    finish();
  }

  void initializeFragment(Fragment fragment) {
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.replace(R.id.content_recipe_detail_item_right_framelayoutcontent, fragment);
    ft.commit();
  }
}
