package com.playspirit.corey.twitcast.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.playspirit.corey.twitcast.R;

public class VideoViewActivity extends ActionBarActivity {

    ProgressDialog mProgressDialog;
    VideoView mVideoView;
    String mTitle;
    String mUrl;
    String mShowLabel;
    int stopPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mVideoView = (VideoView) findViewById(R.id.VideoView);

        Intent intent = getIntent();
        mUrl = intent.getExtras().getString("mUrl");
        mTitle = intent.getExtras().getString("mTitle");
        mShowLabel = intent.getExtras().getString("mShowLabel");

        mProgressDialog = new ProgressDialog(VideoViewActivity.this);
        mProgressDialog.setTitle(mShowLabel);
        mProgressDialog.setMessage("Now Loanding: " + mTitle);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        if(savedInstanceState != null) {
            stopPosition = savedInstanceState.getInt("position");
        }

        try {

            MediaController mediaController = new MediaController(VideoViewActivity.this);
            // MediaController mediaController = new MediaController(VideoViewActivity.this);
            mediaController.setAnchorView(mVideoView);
            Uri video = Uri.parse(mUrl);
            mVideoView.setMediaController(mediaController);
            mVideoView.setVideoURI(video);
        } catch (Exception e) {
            Log.e("ERROR: ", e.getMessage());
            e.printStackTrace();
        }
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mProgressDialog.dismiss();
                mVideoView.start();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        stopPosition = mVideoView.getCurrentPosition();
        mVideoView.pause();
        outState.putInt("position", stopPosition);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.seekTo(stopPosition);
        mVideoView.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
