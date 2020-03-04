package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    FullScreenVideoView videoView;//全屏视频
    TextView textViewTimer;//倒计时
    ITimeCounter iTimeCounter;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iTimeCounter.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView=(FullScreenVideoView) findViewById(R.id.videoViewSplash);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ File.separator+R.raw.splash));
        textViewTimer=findViewById(R.id.textViewTimer);
        //播放视频监听
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
        iTimeCounter= new ITimeCounter(5, new ITimeCounter.GetBack() {
            @Override
            public void getTime(int time) {
                textViewTimer.setText(Integer.toString(time) );

            }

            @Override
            public void onFinish() {
                textViewTimer.setText("跳过");
            }
        });
        iTimeCounter.start();

        //点击跳过
        textViewTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textViewTimer.getText()=="跳过")
                {
                    //Log.i("aa",getString( textViewTimer.getText()));
                    Intent intent = new Intent(MainActivity.this,firtst_page.class);
                    startActivity(intent);
                }
            }
        });

    }
}
