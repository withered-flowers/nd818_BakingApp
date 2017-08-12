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
    int idLayout = android.R.layout.simple_list_item_1;

    View view = inflater.inflate(idLayout, parent, false);

    return new AdapterRecipeDetail_Left_ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(AdapterRecipeDetail_Left_ViewHolder holder, int position) {
    final RecipeStep currentRecipeStep = listRecipeStep.get(position);

    Log.d(TAG, "onBindViewHolder: " + currentRecipeStep.getStepDescription());

    holder.tvRecipeDetail.setText(currentRecipeStep.getStepShortDescription());
  }

  @Override
  public int getItemCount() {
    return listRecipeStep.size();
  }

  static class AdapterRecipeDetail_Left_ViewHolder extends RecyclerView.ViewHolder {
    TextView tvRecipeDetail;

    public AdapterRecipeDetail_Left_ViewHolder(View itemView) {
      super(itemView);
      tvRecipeDetail = (TextView) itemView.findViewById(android.R.id.text1);
    }
  }
}
