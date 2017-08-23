package com.example.standard.bakingapp.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.standard.bakingapp.R;

/**
 * Created by standard on 8/23/17.
 */

public class FragmentRecipeDetailRightText extends Fragment {
  TextView tvIngredient;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View parent = inflater.inflate(R.layout.content_recipe_detail_item_right_recipe, container, false);

    tvIngredient = (TextView) parent.findViewById(R.id.content_recipe_detail_item_right_textview_recipeingredient);

    Bundle bundle = this.getArguments();

    if (bundle != null) {
      String theIngredient = bundle.getString(StaticValue.KEY_STRING_RECIPEINGREDIENT);
      tvIngredient.setText(theIngredient);
    }

    return parent;
  }
}
