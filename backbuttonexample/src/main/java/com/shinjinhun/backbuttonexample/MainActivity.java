package com.shinjinhun.backbuttonexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

// #23 (뒤로가기 두번눌러 앱종료)
public class MainActivity extends AppCompatActivity {

    private long backBtnTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {

        long curTime = System.currentTimeMillis();
        long gatTime = curTime - backBtnTime;

        if(0 <= gatTime && 2000 >= gatTime) {   // 2초안에 백버튼 2번 연속 클릭시 앱 종료 실행
            super.onBackPressed();
        } else {
            backBtnTime = curTime;
            Toast.makeText(this,"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }

    }
}
