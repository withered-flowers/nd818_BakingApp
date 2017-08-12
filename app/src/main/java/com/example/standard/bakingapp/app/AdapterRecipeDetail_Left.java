package com.example.standard.bakingapp.app;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.RecipeStep;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by standard on 8/12/17.
 */

public class AdapterRecipeDetail_Left extends RecyclerView.Adapter<AdapterRecipeDetail_Left.AdapterRecipeDetail_Left_ViewHolder> {
  private List<RecipeStep> listRecipeStep;

  public AdapterRecipeDetail_Left(List<RecipeStep> listRecipeStep) {
    this.listRecipeStep = new ArrayList<>();
    this.listRecipeStep.addAll(listRecipeStep);
    Log.d(TAG, String.valueOf(listRecipeStep.size()));
  }

  @Override
  public AdapterRecipeDetail_Left_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    int idLayout = R.layout.content_recipe_detail_item_left;

    View view = inflater.inflate(idLayout, parent, false);

    return new AdapterRecipeDetail_Left_ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(AdapterRecipeDetail_Left_ViewHolder holder, int position) {
    if(position == 0) {
      holder.btnRecipeDetail.setText("Recipe Ingredients");
    }
    else if (position > 0) {
      final RecipeStep currentRecipeStep = listRecipeStep.get(position-1);

      //Log.d(TAG, "onBindViewHolder: " + currentRecipeStep.getStepDescription());

      holder.btnRecipeDetail.setText(currentRecipeStep.getStepDescription());
    }
  }

  @Override
  public int getItemCount() {
    return listRecipeStep.size()+1;
  }

  public class AdapterRecipeDetail_Left_ViewHolder extends RecyclerView.ViewHolder {
    final Button btnRecipeDetail;

    public AdapterRecipeDetail_Left_ViewHolder(View itemView) {
      super(itemView);

      btnRecipeDetail = (Button) itemView.findViewById(R.id.content_frame_recipe_textview_item_left);
    }
  }
}
