package com.shinjinhun.jhtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_move;
    private EditText et_test;
    private String str;
    private ImageView imgView01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_test     = findViewById(R.id.editText01);
        btn_move    = findViewById(R.id.button01);
        imgView01   = findViewById(R.id.imgView01);

        imgView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"이미지 클릭 : " + et_test.getText().toString()  , Toast.LENGTH_LONG).show();
            }
        });


        btn_move.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                str = et_test.getText().toString();

                Toast.makeText(getApplicationContext(),"메인 버튼 클릭 : " + str  , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("str",str);

                startActivity(intent);
                finish();   // MainActivity 종료
            }
        });

    }
}
