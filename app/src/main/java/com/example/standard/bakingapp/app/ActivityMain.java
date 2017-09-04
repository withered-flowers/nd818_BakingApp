package com.example.standard.bakingapp.app;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.Recipe;
import com.example.standard.bakingapp.backend.retrofit.APIEndpoint;
import com.example.standard.bakingapp.backend.retrofit.Fetcher;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMain extends AppCompatActivity
    implements AdapterRecipeList.clickHandler {

  private static final String PARCEL_TAG = "RECIPE";

  private RecyclerView rvwListRecipe;
  private CoordinatorLayout vwCoordinator;

  @Override
  public void onClickAdapterRecipeList(Recipe obj) {
    Bundle bundle = new Bundle();
    bundle.putParcelable(PARCEL_TAG, obj);

    Intent i = new Intent(this, ActivityDetail.class);
    i.putExtras(bundle);

    startActivity(i);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    vwCoordinator = (CoordinatorLayout) findViewById(R.id.main_coordinator);

    rvwListRecipe = (RecyclerView) findViewById(R.id.content_recipe_list_frame);
    rvwListRecipe.setHasFixedSize(true);

    //Handling Network Is Connected or Not
    ConnectivityManager connectivityManager =
        (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

    boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    if(isConnected) {
    /* Fetch API Content Here */
      Fetcher theFetcher = new Fetcher();
      APIEndpoint theEndpoint = theFetcher.getFetcher().create(APIEndpoint.class);

      Call<List<Recipe>> callListRecipe = theEndpoint.getListRecipe("android-baking-app-json");

      callListRecipe.enqueue(new Callback<List<Recipe>>() {
        @Override
        public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
          List<Recipe> listRecipe = response.body();

          if (listRecipe != null) {
            AdapterRecipeList adpRecipeList = new AdapterRecipeList(listRecipe);
            adpRecipeList.setOnCardViewClick(ActivityMain.this);

            if (getApplicationContext().getResources().getBoolean(R.bool.should_use_multi_grid)) {
              rvwListRecipe.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
            } else {
              rvwListRecipe.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
            rvwListRecipe.setAdapter(adpRecipeList);
          }
        }

        @Override
        public void onFailure(Call<List<Recipe>> call, Throwable t) {
          showSnackbar(t.toString());
        }
      });
    }
    else {
      showSnackbar(getString(R.string.activity_main_no_internet));
    }
    /* End of Fetch API Content */
  }

  private void showSnackbar(String theMessage) {
    Snackbar
      .make(vwCoordinator, theMessage, Snackbar.LENGTH_LONG)
      .show();
  }
}
