package com.example.standard.bakingapp.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.Recipe;

import java.util.List;

/**
 * Created by standard on 8/30/17.
 */

class AdapterWidgetRecipeList extends RecyclerView.Adapter<AdapterWidgetRecipeList.AdapterWidgetRecipeListViewHolder> {
  private List<Recipe> listRecipe;

  AdapterWidgetRecipeList(List<Recipe> listRecipe) {
    this.listRecipe = listRecipe;
  }

  @Override
  public AdapterWidgetRecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    int idLayout = R.layout.widget_recipe_configure_item;

    View view = inflater.inflate(idLayout, parent, false);

    return new AdapterWidgetRecipeListViewHolder(view);
  }

  @Override
  public void onBindViewHolder(AdapterWidgetRecipeListViewHolder holder, int position) {
    Recipe currentRecipe = listRecipe.get(position);

    holder.tvWidgetRecipeText.setText(currentRecipe.getRecipeName());
  }

  @Override
  public int getItemCount() {
    return listRecipe.size();
  }

  class AdapterWidgetRecipeListViewHolder extends RecyclerView.ViewHolder {
    final View vwWidgetRecipeContainer;
    final TextView tvWidgetRecipeText;

    AdapterWidgetRecipeListViewHolder(View itemView) {
      super(itemView);

      vwWidgetRecipeContainer = itemView.findViewById(R.id.widget_recipe_configure_item_container);
      tvWidgetRecipeText = (TextView) itemView.findViewById(R.id.widget_recipe_configure_item_textview);
    }
  }
}
