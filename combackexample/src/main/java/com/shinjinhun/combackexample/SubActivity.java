package com.shinjinhun.combackexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    private EditText et_comback;
    private Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        et_comback = findViewById(R.id.et_comback);
        btn_close = findViewById(R.id.btn_close);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("comback",et_comback.getText().toString()); // 입력폼에 적은 값 담아주기
                setResult(RESULT_OK, intent); // 결과 값 설정
                finish(); // 현재 액티비티 종료
            }
        });


    }
}
