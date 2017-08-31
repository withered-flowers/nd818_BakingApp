package com.example.standard.bakingapp.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.Recipe;
import com.example.standard.bakingapp.backend.pojo.RecipeIngredient;
import com.example.standard.bakingapp.backend.retrofit.APIEndpoint;
import com.example.standard.bakingapp.backend.retrofit.Fetcher;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The configuration screen for the {@link WidgetRecipe WidgetRecipe} AppWidget.
 */
public class WidgetRecipeConfigureActivity extends Activity
    implements AdapterWidgetRecipeList.clickHandler {

  private static final String PREFS_NAME = "com.example.standard.bakingapp.widget.WidgetRecipe";
  private static final String PREF_PREFIX_KEY = "appwidget_";
  int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

  public WidgetRecipeConfigureActivity() {
    super();
  }

  // Write the prefix to the SharedPreferences object for this widget
  static void saveTitlePref(Context context, int appWidgetId, String text) {
    SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
    prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
    prefs.apply();
  }

  // Read the prefix from the SharedPreferences object for this widget.
  // If there is no preference saved, get the default from a resource
  static String loadTitlePref(Context context, int appWidgetId) {
    SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
    String titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null);
    if (titleValue != null) {
      return titleValue;
    } else {
      return context.getString(R.string.appwidget_text);
    }
  }

  static void deleteTitlePref(Context context, int appWidgetId) {
    SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
    prefs.remove(PREF_PREFIX_KEY + appWidgetId);
    prefs.apply();
  }

  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);

    // Set the result to CANCELED.  This will cause the widget host to cancel
    // out of the widget placement if the user presses the back button.
    setResult(RESULT_CANCELED);

    setContentView(R.layout.widget_recipe_configure);

    final ProgressBar pgbRecipeList = (ProgressBar) findViewById(R.id.widget_recipe_configure_progressbar);
    final RecyclerView rvwRecipeList = (RecyclerView) findViewById(R.id.widget_recipe_configure_recyclerview);
    rvwRecipeList.setHasFixedSize(true);

    // SECURITY REASON
    // Find the widget id from the intent.
    Intent intent = getIntent();
    Bundle extras = intent.getExtras();
    if (extras != null) {
      mAppWidgetId = extras.getInt(
          AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    // If this activity was started with an intent without an app widget ID, finish with an error.
    if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
      finish();
      return;
    }
    // END OF SECURITY REASON

    Fetcher theFetcher = new Fetcher();
    APIEndpoint theEndpoint = theFetcher.getFetcher().create(APIEndpoint.class);

    Call<List<Recipe>> callListRecipe = theEndpoint.getListRecipe("android-baking-app-json");

    callListRecipe.enqueue(new Callback<List<Recipe>>() {
      @Override
      public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
        List<Recipe> listRecipe = response.body();

        AdapterWidgetRecipeList adpWidgetRecipeList = new AdapterWidgetRecipeList(listRecipe);
        adpWidgetRecipeList.setViewOnClick(WidgetRecipeConfigureActivity.this);

        rvwRecipeList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvwRecipeList.setAdapter(adpWidgetRecipeList);

        pgbRecipeList.setVisibility(View.GONE);
        rvwRecipeList.setVisibility(View.VISIBLE);
      }

      @Override
      public void onFailure(Call<List<Recipe>> call, Throwable t) {
        Log.e("RECIPE", "onFailure: " + t.toString());
      }
    });
  }

  @Override
  public void onClickAdapterWidgetRecipeList(Recipe obj) {
    final Context context = WidgetRecipeConfigureActivity.this;

    List<RecipeIngredient> recipeIngredients = obj.getRecipeListIngredients();

    String widgetText = obj.getRecipeName() + "\n\n";

    for (int i = 0; i < recipeIngredients.size(); i++) {
      widgetText += recipeIngredients.get(i).getIngredientQuantity() + " ";
      widgetText += recipeIngredients.get(i).getIngredientMeasure() + " of ";
      widgetText += recipeIngredients.get(i).getIngredientName() + "\n";
    }

    // When the button is clicked, store the string locally
    saveTitlePref(context, mAppWidgetId, widgetText);

    // It is the responsibility of the configuration activity to update the app widget
    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
    WidgetRecipe.updateAppWidget(context, appWidgetManager, mAppWidgetId);

    // Make sure we pass back the original appWidgetId
    Intent resultValue = new Intent();
    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
    setResult(RESULT_OK, resultValue);
    finish();
  }
}

