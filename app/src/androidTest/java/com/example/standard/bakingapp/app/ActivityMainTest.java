package com.example.standard.bakingapp.app;


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
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ActivityMainTest {

  @Rule
  public ActivityTestRule<ActivityMain> mActivityTestRule = new ActivityTestRule<>(ActivityMain.class);

  @Test
  public void activityMainTest() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    //Check if RecyclerView have the item
    onView(withRecyclerView(R.id.content_recipe_list_frame).atPosition(1))
        .check(matches(hasDescendant(withText("Brownies"))));

    onView(withRecyclerView(R.id.content_recipe_list_frame).atPosition(1))
        .perform(click());
  }

  private static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
    return new RecyclerViewMatcher(recyclerViewId);
  }
}
