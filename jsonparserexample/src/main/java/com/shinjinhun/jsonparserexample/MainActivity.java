package com.shinjinhun.jsonparserexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private long backBtnTime = 0;
    private TextView tv;

    private  String str =
            "[{'name':'배트맨','age':43,'address':'고담'},"+
                    "{'name':'슈퍼맨','age':36,'address':'뉴욕'},"+
                    "{'name':'앤트맨','age':25,'address':'LA'}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tvResult1);
        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doJSONParser();
            }
        });


        Button btn_next = (Button)findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("str",str);

                startActivity(intent);
                finish();   // MainActivity 종료
            }
        });


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

    private void doJSONParser() {
        StringBuffer sb = new StringBuffer();

        try {
            JSONArray jarray = new JSONArray(str);   // JSONArray 생성

            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String address = jObject.getString("address");
                String name = jObject.getString("name");
                int age = jObject.getInt("age");

                sb.append(
                        "이름:" + name +
                        ", 주소:" + address +
                        ", 나이:" + age + "\n"
                );
            }
            tv.setText(sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
