package com.example.travelling.Services;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.travelling.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private YouTubePlayerView youTubePlayerView;
    Button button;

    ImageView play, pause, stop,display;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        youTubePlayerView = findViewById(R.id.videoId);
        button = findViewById(R.id.buttonId);
        play = findViewById(R.id.playIcon);
        pause = findViewById(R.id.pauseIcon);
        stop = findViewById(R.id.stopIcon);
        display = findViewById(R.id.sampleImageId);

        mediaPlayer = MediaPlayer.create(this, R.raw.newmusic);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId = "_q3VtPZQpcI";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.playIcon){
            mediaPlayer.start();

        }

        if(view.getId() == R.id.pauseIcon){
            mediaPlayer.pause();

        }

        if(view.getId() == R.id.stopIcon){
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }

    }
}