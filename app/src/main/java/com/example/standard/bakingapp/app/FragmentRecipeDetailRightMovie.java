package com.example.standard.bakingapp.app;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.standard.bakingapp.R;
import com.example.standard.bakingapp.backend.pojo.RecipeStep;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by standard on 8/23/17.
 */

public class FragmentRecipeDetailRightMovie extends Fragment {
  TextView tvStep;
  SimpleExoPlayer exoPlayer;
  SimpleExoPlayerView exoPlayerView;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View parent = inflater.inflate(R.layout.content_recipe_detail_item_right_movieplayer, container, false);

    tvStep = (TextView) parent.findViewById(R.id.content_recipe_detail_item_right_textview_stepdescription);
    exoPlayerView = (SimpleExoPlayerView) parent.findViewById(R.id.content_recipe_detail_item_right_exoplayer);

    Bundle bundle = this.getArguments();

    if (bundle != null) {
      RecipeStep recipeStep = bundle.getParcelable(StaticValue.KEY_OBJECT_RECIPESTEP);

      String theStep = "";

      if (recipeStep != null) {
        theStep = recipeStep.getStepDescription();
        String theStepVideoUrl = recipeStep.getStepVideoURL();

        if (theStepVideoUrl == null || theStepVideoUrl.equals("")) {
          exoPlayerView.setVisibility(View.GONE);
        } else {
          Uri theStepVideo = Uri.parse(theStepVideoUrl);

          //Initialize ExoPlayer & Media Source
          //Track Selector
          TrackSelector trackSelector = new DefaultTrackSelector();

          //Load Control
          LoadControl loadControl = new DefaultLoadControl();

          //Player
          exoPlayer = ExoPlayerFactory.newSimpleInstance(parent.getContext(), trackSelector, loadControl);

          //Media Controller
          exoPlayerView.setUseController(true);
          exoPlayerView.requestFocus();

          //Bind Player to View
          exoPlayerView.setPlayer(exoPlayer);

          //Prepare MediaSource
          MediaSource mediaSource = new ExtractorMediaSource(
              theStepVideo,
              new DefaultDataSourceFactory(parent.getContext(), Util.getUserAgent(parent.getContext(), "bakingapp")),
              new DefaultExtractorsFactory(),
              null,
              null
          );

          exoPlayer.prepare(mediaSource);
          exoPlayer.setPlayWhenReady(true);
          //End of Exoplayer & Media Source

          exoPlayerView.setVisibility(View.VISIBLE);
        }
      }

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        tvStep.setText(Html.fromHtml(theStep, Html.FROM_HTML_MODE_LEGACY));
      } else {
        tvStep.setText(Html.fromHtml(theStep));
      }
    }

    return parent;
  }
}
