package com.shinjinhun.jhtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    private TextView textView02;
    private Button button02;

    // BackPressHandler 객체 선언, 할당
    private BackPressHandler backPressHandler = new BackPressHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView02 = findViewById(R.id.textView02);

        button02 = findViewById(R.id.button02);

        Intent  intent = getIntent();
        String str = intent.getStringExtra("str");

        Log.d("jhTEST",str);

        Toast.makeText(getApplicationContext(),"버튼 클릭: " + str  , Toast.LENGTH_LONG).show();

        textView02.setText(str);

        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"메인으로 돌아가기"   , Toast.LENGTH_LONG).show();


                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // SubActivity 종료


            }
        });


    }
    @Override
    public void onBackPressed() {

        //backPressHandler.onBackPressed();    // Default

        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료");    // Toast 메세지 사용자 지정

        //backPressHandler.onBackPressed(3000);      // 뒤로가기 간격 사용자 지정

        //backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료", 3000);  // Toast, 간격 사용자 지정
    }

}
