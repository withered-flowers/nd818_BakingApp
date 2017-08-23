package com.example.standard.bakingapp.app;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.RecipeStep;

/**
 * Created by standard on 8/23/17.
 */

public class FragmentRecipeDetailRightMovie extends Fragment {
  TextView tvStep;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View parent = inflater.inflate(R.layout.content_recipe_detail_item_right_movieplayer, container, false);

    tvStep = (TextView) parent.findViewById(R.id.content_recipe_detail_item_right_textview_stepdescription);

    Bundle bundle = this.getArguments();

    if (bundle != null) {
      RecipeStep recipeStep = bundle.getParcelable(StaticValue.KEY_OBJECT_RECIPESTEP);


      String theStep = "";

      if (recipeStep != null) {
        theStep = recipeStep.getStepDescription();
      }

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        tvStep.setText(Html.fromHtml(theStep, Html.FROM_HTML_MODE_LEGACY));
      } else {
        tvStep.setText(Html.fromHtml(theStep));
      }
    }

    return parent;
  }
}
