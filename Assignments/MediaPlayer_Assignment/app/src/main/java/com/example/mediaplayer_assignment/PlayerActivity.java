package com.example.mediaplayer_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {

    ImageView play;
    MediaPlayer mp;
    int index;
    TextView songname;
    SeekBar seekBar;


    TextView elapsedTimeLabel, remainingTimeLabel;
    int totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        songname=findViewById(R.id.songName);
        play=findViewById(R.id.play);
        seekBar=findViewById(R.id.mSeekBarTime);
        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = findViewById(R.id.remainingTimeLabel);

        Intent intent=getIntent();
        index=intent.getIntExtra("index",0);
        songname.setText(Songs.current.get(index).getName());

        Uri path= Uri.parse(Songs.current.get(index).getAbsolutePath());
        mp=MediaPlayer.create(this,path);
        mp.setLooping(true);
        mp.seekTo(0);
        mp.setVolume(0.5f, 0.5f);
        totalTime = mp.getDuration();

        // Position Bar
        seekBar.setMax(totalTime);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mp.seekTo(progress);
                            seekBar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );


    
        // Thread (Update seekBar & timeLabel)
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {}
                }
            }
        }).start();

    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update seekBar.
            seekBar.setProgress(currentPosition);

            // Update Labels.
            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = "- " + createTimeLabel(totalTime - currentPosition);
            remainingTimeLabel.setText(remainingTime);

            return true;
        }
    });

    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    public void play(View view){
        if(mp.isPlaying()) {
            mp.pause();
            play.setImageResource(R.drawable.play);
        }
        else {
            mp.start();
            play.setImageResource(R.drawable.pause);
        }

    }

    public void previous(View view){
        if(index>0)
            --index;
        else
            index=Songs.current.size()-1;


        mp.stop();
        Uri path= Uri.parse(Songs.current.get(index).getAbsolutePath());
        mp=MediaPlayer.create(this,path);
        mp.start();
        mp.setLooping(true);
        play.setImageResource(R.drawable.pause);
        songname.setText(Songs.current.get(index).getName());


    }
    public void next(View view){
        if(index<Songs.current.size()-1)
            ++index;
        else
            index=0;


        mp.stop();
        Uri path= Uri.parse(Songs.current.get(index).getAbsolutePath());
        mp=MediaPlayer.create(this,path);
        mp.setLooping(true);
        mp.start();

        play.setImageResource(R.drawable.pause);
        songname.setText(Songs.current.get(index).getName());

    }


    @Override
    public void onBackPressed() {
        mp.stop();
        finish();

    }
}