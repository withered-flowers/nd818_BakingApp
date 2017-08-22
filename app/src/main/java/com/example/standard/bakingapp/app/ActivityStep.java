package com.example.standard.bakingapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.RecipeIngredient;
import com.example.standard.bakingapp.backend.pojo.RecipeStep;

import java.util.List;

public class ActivityStep extends AppCompatActivity {

  TextView tvIngredient;
  LinearLayout lytTop;
  LinearLayout lytBottom;

  //TODO add One Panel TextView or One Panel Fragment Movie
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_step);

    tvIngredient = (TextView) findViewById(R.id.content_recipe_detail_item_right_textview);
    lytTop = (LinearLayout) findViewById(R.id.content_recipe_detail_item_right_layoutrecipe);
    lytBottom = (LinearLayout) findViewById(R.id.content_recipe_detail_item_right_layoutexoplayer);

    if (getIntent().getExtras() != null) {
      if (getIntent().getExtras().getParcelable(StaticValue.KEY_OBJECT_RECIPESTEP) != null) {
        //STEP HERE
        RecipeStep recipeStep = getIntent().getExtras().getParcelable(StaticValue.KEY_OBJECT_RECIPESTEP);

        if (recipeStep != null) {
          Toast.makeText(this, recipeStep.getStepShortDescription(), Toast.LENGTH_SHORT).show();
        }

        lytTop.setVisibility(View.INVISIBLE);
        lytBottom.setVisibility(View.VISIBLE);
      } else if (getIntent().getExtras().getParcelableArrayList(StaticValue.KEY_OBJECT_RECIPEINGREDIENT_ARRAY) != null) {
        //INGREDIENT HERE
        List<RecipeIngredient> recipeIngredients = getIntent().getExtras().getParcelableArrayList(StaticValue.KEY_OBJECT_RECIPEINGREDIENT_ARRAY);

        if (recipeIngredients != null) {
          String textIngredient = "";

          for (int i=0; i<recipeIngredients.size(); i++) {
            textIngredient += recipeIngredients.get(i).getIngredientQuantity() + " ";
            textIngredient += recipeIngredients.get(i).getIngredientMeasure() + " of ";
            textIngredient += recipeIngredients.get(i).getIngredientName() + "\n";
          }

          tvIngredient.setText(textIngredient);

          lytTop.setVisibility(View.VISIBLE);
          lytBottom.setVisibility(View.INVISIBLE);
        }
      }
    }
  }
}
