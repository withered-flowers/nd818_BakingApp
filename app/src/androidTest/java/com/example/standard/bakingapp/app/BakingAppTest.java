package com.example.standard.bakingapp.app;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.standard.bakingapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BakingAppTest {

  @Rule
  public ActivityTestRule<ActivityMain> mActivityTestRule = new ActivityTestRule<>(ActivityMain.class);

  @Test
  public void activityMainTest() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    //Check if Connection is Enabled or Not
    assertTrue(isConnected(mActivityTestRule.getActivity().getApplicationContext()));

    //Check if RecyclerView (Recipe) have 4 item
    onView(withId(R.id.content_recipe_list_frame))
        .check(new RecyclerViewItemCount(4));

    //Check if RecyclerView have specific item
    onView(withRecyclerView(R.id.content_recipe_list_frame).atPosition(1))
        .check(matches(hasDescendant(withText("Brownies"))));

    //Click the RecipeList Position 1
    onView(withRecyclerView(R.id.content_recipe_list_frame).atPosition(1))
        .perform(click());

    //Check if RecyclerView (RecipeStep) for Position 1 (Brownies) has 10 + 1 items
    onView(withId(R.id.content_recipe_detail_framerecipe))
        .check(new RecyclerViewItemCount(11));

    //Check if the First Child of RecyclerView (RecipeStep) has Text with "Recipe Ingredients"
    onView(withRecyclerView(R.id.content_recipe_detail_framerecipe).atPosition(0))
        .check(matches(hasDescendant(withText("Recipe Ingredients"))));

    //Check if the Seventh Child of RecyclerView (RecipeStep) has Text with "Add eggs."
    onView(withRecyclerView(R.id.content_recipe_detail_framerecipe).atPosition(6))
        .check(matches(hasDescendant(withText("Add eggs."))));

    //Click the RecipeStep Position 1
    onView(withRecyclerView(R.id.content_recipe_detail_framerecipe).atPosition(0))
        .perform(click());

    //Check the Recipe Ingredients Text (350.0 G of Bittersweet blablabla)
    onView(withId(R.id.content_recipe_detail_item_right_textview_recipeingredient))
        .check(matches(withText(startsWith("350.0 G"))));

    //Check if exist Button Next and Click
    onView(withId(R.id.content_recipe_detail_item_right_buttonnext))
        .check(matches(isDisplayed()))
        .perform(click());

    //Check if ExoPlayer Pause Exist and Click it
    onView(allOf(withId(R.id.exo_pause), withContentDescription("Pause"), isDisplayed()))
        .perform(click());
  }

  private static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
    return new RecyclerViewMatcher(recyclerViewId);
  }

  private boolean isConnected(Context context) {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }
}
