package com.example.standard.bakingapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.Recipe;
import com.example.standard.bakingapp.backend.pojo.RecipeIngredient;
import com.example.standard.bakingapp.backend.pojo.RecipeStep;

import java.util.List;

/**
 * Created by standard on 8/12/17.
 */

public class ActivityDetail extends AppCompatActivity {

  private static final String PARCEL_TAG = "RECIPE";

  private boolean isTwoPanel;

  private TextView tvIngredients;
  private RecyclerView rvwRecipeDetail;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    isTwoPanel = findViewById(R.id.content_frame_step_detail) != null;
    rvwRecipeDetail = (RecyclerView) findViewById(R.id.content_frame_recipe_detail);
    rvwRecipeDetail.setHasFixedSize(true);

    assert getIntent().getExtras() != null;
    Recipe currentRecipe = getIntent().getExtras().getParcelable(PARCEL_TAG);

    assert currentRecipe != null;
    List<RecipeIngredient> currentRecipe_ListIngredient = currentRecipe.getRecipeListIngredients();
    List<RecipeStep> currentRecipe_ListStep = currentRecipe.getRecipeListSteps();

    Log.d("TEST", String.valueOf(currentRecipe_ListStep.size()));

    AdapterRecipeDetail_Left adpRecipeDetail_Left = new AdapterRecipeDetail_Left(currentRecipe_ListStep);
    rvwRecipeDetail.setAdapter(adpRecipeDetail_Left);
    rvwRecipeDetail.setLayoutManager(new LinearLayoutManager(this));

//    String recipeIngredient = "";
//
//    int size = currentRecipe_ListIngredient.size();
//    for(int i=0; i<size; i++) {
//      recipeIngredient += currentRecipe_ListIngredient.get(i).getIngredientName();
//      if(i<size-1) {
//        recipeIngredient += "\n";
//      }
//    }

//    tvIngredients = (TextView) findViewById(R.id.content_textview_ingredient_list);
//    tvIngredients.setText(recipeIngredient);
  }
}
