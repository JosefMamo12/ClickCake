package com.example.clickcake;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class OpeningActivity extends AppCompatActivity {
    Button userButton;
    Button clientButton;
    VideoView videoView;

    private int videoPosition = 0; // To store the video playback position

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clientButton = findViewById(R.id.button2);
        userButton = findViewById(R.id.button1);
        videoView = findViewById(R.id.video_view);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.high_ratio_clip;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities(userButton);
            }
        });

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivities(clientButton);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause the video playback and save the current position
        if (videoView.isPlaying()) {
            videoPosition = videoView.getCurrentPosition();
            videoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Resume the video playback from where it left off
        videoView.seekTo(videoPosition);
        videoView.start();
    }

    private void switchActivities(Button button) {
        Intent intent = button.getText().equals("לקוח") ?
                new Intent(this, MainActivity.class) :
                new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}