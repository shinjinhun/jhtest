package com.example.musicplayerexample;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// #22 (음악재생 mp3)
// 앱을 종료하면 재생 중지됨 ( #17과 serviceexample 차이점 )
public class MainActivity extends AppCompatActivity {

    Button btn_play, btn_stop;

    MediaPlayer mediaPlayer;

    // 액티비티가 종료될때... 이곳을 실행함...
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 미디어 플레이어가 실행중이 아닐때 자원을 초기화 시키는게 좋음
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = findViewById(R.id.btn_play);
        btn_stop = findViewById(R.id.btn_stop);

        // 재생 버튼 눌렀을때
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.fantasyland);
                mediaPlayer.start();
            }
        });

        // 정지 버튼 눌렀을때
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }


            }
        });


    }
}
