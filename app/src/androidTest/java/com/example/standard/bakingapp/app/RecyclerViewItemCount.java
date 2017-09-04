package com.example.standard.bakingapp.app;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by standard on 9/4/17.
 */

// Class provided by Will Mentzel @ Stack Overflow
// https://stackoverflow.com/questions/36399787/how-to-count-recyclerview-items-with-espresso

public class RecyclerViewItemCount implements ViewAssertion {
  private final Matcher<Integer> matcher;

  public RecyclerViewItemCount(int expectedCount) {
    this.matcher = is(expectedCount);
  }

  @Override
  public void check(View view, NoMatchingViewException noViewFoundException) {
    if (noViewFoundException != null) {
      throw noViewFoundException;
    }

    RecyclerView recyclerView = (RecyclerView) view;
    RecyclerView.Adapter adapter = recyclerView.getAdapter();
    assertThat(adapter.getItemCount(), matcher);
  }

}
