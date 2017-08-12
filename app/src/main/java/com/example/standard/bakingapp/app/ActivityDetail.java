package com.example.standard.bakingapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.standard.bakingapp.R;

/**
 * Created by standard on 8/12/17.
 */

public class ActivityDetail extends AppCompatActivity {

  private boolean isTwoPanel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    isTwoPanel = findViewById(R.id.content_frame_step_detail) != null;

  }
}
