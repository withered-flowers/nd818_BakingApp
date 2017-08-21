package com.example.standard.bakingapp.app;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.Recipe;

import java.util.List;

public class AdapterRecipeList extends RecyclerView.Adapter<AdapterRecipeList.AdapterRecipeListViewHolder> {

  private clickHandler listener;
  private List<Recipe> listRecipe;

  public interface clickHandler {
    void onClickAdapterRecipeList(Recipe obj);
  }

  public AdapterRecipeList(List<Recipe> listRecipe) {
    this.listRecipe = listRecipe;
  }

  public void setOnCardViewClick(clickHandler listener) {
    this.listener = listener;
  }

  @Override
  public AdapterRecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    int idLayout = R.layout.content_recipe_list_item;

    View view = inflater.inflate(idLayout, parent, false);

    return new AdapterRecipeListViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final AdapterRecipeListViewHolder holder, int position) {
    final Recipe currentRecipe = listRecipe.get(position);

    holder.cardRecipeText.setText(currentRecipe.getRecipeName());
    holder.cardRecipe.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (listener != null) {
          listener.onClickAdapterRecipeList(listRecipe.get(holder.getAdapterPosition()));
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return listRecipe.size();
  }

  public class AdapterRecipeListViewHolder extends RecyclerView.ViewHolder {
    public final CardView cardRecipe;
    public final TextView cardRecipeText;

    public AdapterRecipeListViewHolder(View itemView) {
      super(itemView);

      cardRecipe = (CardView) itemView.findViewById(R.id.content_recipe_list_item_cardrecipe);
      cardRecipeText = (TextView) itemView.findViewById(R.id.content_recipe_list_item_cardrecipe_text);
    }
  }
}
