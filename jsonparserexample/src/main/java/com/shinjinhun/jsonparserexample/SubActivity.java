package com.shinjinhun.jsonparserexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class SubActivity extends AppCompatActivity {

    Button btn_object, btn_array;
    EditText et_nation, et_name, et_address, et_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        et_nation   = findViewById(R.id.et_nation);
        et_name     = findViewById(R.id.et_name);
        et_address  = findViewById(R.id.et_address);
        et_age      = findViewById(R.id.et_age);

        btn_object  = findViewById(R.id.btn_object);
        btn_array   = findViewById(R.id.btn_array);


        btn_object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("nation", et_nation.getText().toString());
                    jsonObject.put("name", et_name.getText().toString());
                    jsonObject.put("address", et_address.getText().toString());
                    jsonObject.put("age", et_age.getText().toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }
                receiveObject(jsonObject);

            }
        });
    }

    private void receiveObject(JSONObject jsonObject) {
        Toast.makeText(this,jsonObject.toString(), Toast.LENGTH_LONG).show();
    }
}
