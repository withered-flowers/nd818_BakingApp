package com.example.standard.bakingapp.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.RecipeIngredient;
import com.example.standard.bakingapp.backend.pojo.RecipeStep;

import java.util.ArrayList;
import java.util.List;

public class ActivityStep extends AppCompatActivity {

  //TODO add One Panel TextView or One Panel Fragment Movie
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_step);

    if (getIntent().getExtras() != null) {
      if (getIntent().getExtras().getParcelable(StaticValue.PARCEL_TAG_02) != null) {
        //STEP HERE
        RecipeStep recipeStep = getIntent().getExtras().getParcelable(StaticValue.PARCEL_TAG_02);

        if (recipeStep != null) {
          Toast.makeText(this, recipeStep.getStepShortDescription(), Toast.LENGTH_SHORT).show();
        }
      } else if (getIntent().getExtras().getParcelableArrayList(StaticValue.PARCEL_TAG_03) != null) {
        //INGREDIENT HERE
        List<RecipeIngredient> rec = getIntent().getExtras().getParcelableArrayList(StaticValue.PARCEL_TAG_03);

        if (rec != null) {
          Toast.makeText(this, String.valueOf(rec.size()), Toast.LENGTH_SHORT).show();
        }
      }
    }
  }
}
