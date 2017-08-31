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

  private clickHandler listener;
  private List<Recipe> listRecipe;

  interface clickHandler {
    void onClickAdapterWidgetRecipeList(Recipe obj);
  }

  AdapterWidgetRecipeList(List<Recipe> listRecipe) {
    this.listRecipe = listRecipe;
  }

  void setViewOnClick(clickHandler listener) {
    this.listener = listener;
  }

  @Override
  public AdapterWidgetRecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    int idLayout = R.layout.widget_recipe_configure_item;

    View view = inflater.inflate(idLayout, parent, false);

    return new AdapterWidgetRecipeListViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final AdapterWidgetRecipeListViewHolder holder, int position) {
    Recipe currentRecipe = listRecipe.get(position);

    holder.tvWidgetRecipeText.setText(currentRecipe.getRecipeName());
    holder.vwWidgetRecipeContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (listener != null) {
          listener.onClickAdapterWidgetRecipeList(listRecipe.get(holder.getAdapterPosition()));
        }
      }
    });
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
