package com.example.standard.bakingapp.app;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.RecipeStep;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by standard on 8/12/17.
 */

public class AdapterRecipeDetailLeft extends RecyclerView.Adapter<AdapterRecipeDetailLeft.AdapterRecipeDetailLeftViewHolder> {
  private List<RecipeStep> listRecipeStep;

  public AdapterRecipeDetailLeft(List<RecipeStep> listRecipeStep) {
    this.listRecipeStep = new ArrayList<>();
    this.listRecipeStep.addAll(listRecipeStep);
    Log.d(TAG, String.valueOf(listRecipeStep.size()));
  }

  @Override
  public AdapterRecipeDetailLeftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    int idLayout = R.layout.content_recipe_detail_item_left;

    View view = inflater.inflate(idLayout, parent, false);

    return new AdapterRecipeDetailLeftViewHolder(view);
  }

  @Override
  public void onBindViewHolder(AdapterRecipeDetailLeftViewHolder holder, int position) {
    if (position == 0) {
      holder.tvRecipeDetail.setText("Recipe Ingredients");
    } else if (position > 0) {
      final RecipeStep currentRecipeStep = listRecipeStep.get(position - 1);
      holder.tvRecipeDetail.setText(currentRecipeStep.getStepDescription());
    }
  }

  @Override
  public int getItemCount() {
    return listRecipeStep.size() + 1;
  }

  public class AdapterRecipeDetailLeftViewHolder extends RecyclerView.ViewHolder {
    final TextView tvRecipeDetail;

    public AdapterRecipeDetailLeftViewHolder(View itemView) {
      super(itemView);

      tvRecipeDetail = (TextView) itemView.findViewById(R.id.content_frame_recipe_textview_item_left);
    }
  }
}