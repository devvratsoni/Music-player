package com.example.musicplayersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {
    Button button, button3, button4;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        button4 = findViewById(R.id.button4);
        player = MediaPlayer.create(MainActivity4.this, R.raw.song1);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player == null) {
                    player = MediaPlayer.create(getApplicationContext(), R.raw.song1);
                }
                player.start();
            }
        });
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player!=null){
                    player = MediaPlayer.create(getApplicationContext(), R.raw.song1);
                    player.pause();
                }

            }
        });


    }
}

