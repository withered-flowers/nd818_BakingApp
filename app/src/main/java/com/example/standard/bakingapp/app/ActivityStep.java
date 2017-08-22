package com.example.standard.bakingapp.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.RecipeIngredient;
import com.example.standard.bakingapp.backend.pojo.RecipeStep;

import java.util.ArrayList;
import java.util.List;

public class ActivityStep extends AppCompatActivity {

  Button btnPrev;
  Button btnNext;
  LinearLayout lytTop;
  LinearLayout lytBottom;
  TextView tvIngredient;
  TextView tvStep;

  int currPosition;
  int maxPosition;
  List<RecipeIngredient> recipeIngredients;
  List<RecipeStep> recipeSteps;

  //TODO Implement the Movie
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_step);

    btnPrev = (Button) findViewById(R.id.content_recipe_detail_item_right_buttonprev);
    btnNext = (Button) findViewById(R.id.content_recipe_detail_item_right_buttonnext);
    lytTop = (LinearLayout) findViewById(R.id.content_recipe_detail_item_right_layoutrecipe);
    lytBottom = (LinearLayout) findViewById(R.id.content_recipe_detail_item_right_layoutexoplayer);
    tvIngredient = (TextView) findViewById(R.id.content_recipe_detail_item_right_textview_recipeingredient);
    tvStep = (TextView) findViewById(R.id.content_recipe_detail_item_right_textview_stepdescription);

    if (getIntent().getExtras() != null) {
      currPosition = getIntent().getExtras().getInt(StaticValue.KEY_INT_POSITION_CURR);
      maxPosition = getIntent().getExtras().getInt(StaticValue.KEY_INT_POSITION_MAX);
      recipeIngredients = getIntent().getExtras().getParcelableArrayList(StaticValue.KEY_OBJECT_RECIPEINGREDIENT_ARRAY);
      recipeSteps = getIntent().getExtras().getParcelableArrayList(StaticValue.KEY_OBJECT_RECIPESTEP);

      if (currPosition == 0) {
        if (recipeIngredients != null) {
          //INGREDIENT HERE
          String textIngredient = "";

          for (int i = 0; i < recipeIngredients.size(); i++) {
            textIngredient += recipeIngredients.get(i).getIngredientQuantity() + " ";
            textIngredient += recipeIngredients.get(i).getIngredientMeasure() + " of ";
            textIngredient += recipeIngredients.get(i).getIngredientName() + "\n";
          }

          tvIngredient.setText(textIngredient);
        }
      } else if (currPosition > 0) {
        if (recipeSteps != null) {
          //STEP HERE
          if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvStep.setText(Html.fromHtml(recipeSteps.get(currPosition - 1).getStepDescription(), Html.FROM_HTML_MODE_LEGACY));
          }
          else {
            tvStep.setText(Html.fromHtml(recipeSteps.get(currPosition - 1).getStepDescription()));
          }
        }
      }

      setViewVisibility(currPosition, maxPosition);
    }

    btnNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Bundle bundle = new Bundle();

        bundle.putInt(StaticValue.KEY_INT_POSITION_CURR, currPosition+1);
        bundle.putInt(StaticValue.KEY_INT_POSITION_MAX, maxPosition);
        bundle.putParcelableArrayList(StaticValue.KEY_OBJECT_RECIPEINGREDIENT_ARRAY, new ArrayList<Parcelable>(recipeIngredients));
        bundle.putParcelableArrayList(StaticValue.KEY_OBJECT_RECIPESTEP, new ArrayList<Parcelable>(recipeSteps));

        Intent i = new Intent(ActivityStep.this, ActivityStep.class);
        i.putExtras(bundle);

        startActivity(i);
        finish();
      }
    });

    btnPrev.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Bundle bundle = new Bundle();

        bundle.putInt(StaticValue.KEY_INT_POSITION_CURR, currPosition-1);
        bundle.putInt(StaticValue.KEY_INT_POSITION_MAX, maxPosition);
        bundle.putParcelableArrayList(StaticValue.KEY_OBJECT_RECIPEINGREDIENT_ARRAY, new ArrayList<Parcelable>(recipeIngredients));
        bundle.putParcelableArrayList(StaticValue.KEY_OBJECT_RECIPESTEP, new ArrayList<Parcelable>(recipeSteps));

        Intent i = new Intent(ActivityStep.this, ActivityStep.class);
        i.putExtras(bundle);

        startActivity(i);
        finish();
      }
    });
  }

  void setViewVisibility(int curr, int max) {
    if(curr == 0) {
      lytTop.setVisibility(View.VISIBLE);
      lytBottom.setVisibility(View.INVISIBLE);

      btnPrev.setVisibility(View.INVISIBLE);
      btnNext.setVisibility(View.VISIBLE);
    } else if(curr == max) {
      lytTop.setVisibility(View.INVISIBLE);
      lytBottom.setVisibility(View.VISIBLE);

      btnPrev.setVisibility(View.VISIBLE);
      btnNext.setVisibility(View.INVISIBLE);
    }
    else {
      lytTop.setVisibility(View.INVISIBLE);
      lytBottom.setVisibility(View.VISIBLE);

      btnPrev.setVisibility(View.VISIBLE);
      btnNext.setVisibility(View.VISIBLE);
    }
  }
}
