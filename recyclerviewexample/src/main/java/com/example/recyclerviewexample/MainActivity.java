package com.example.recyclerviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView)findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        mainAdapter = new MainAdapter(arrayList);

        recyclerView.setAdapter(mainAdapter);


        MainData mainData = new MainData(R.mipmap.ic_launcher,"홍드로이드" + arrayList.size(),"리사이클러뷰"  + arrayList.size());
        arrayList.add(mainData);
        mainAdapter.notifyDataSetChanged(); // 새로고침침


        Button btn_add = (Button)findViewById(R.id.btn_add);

        // 추가 버튼 클릭시 리사이클 추가하기
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData mainData = new MainData(R.mipmap.ic_launcher,"홍드로이드" + arrayList.size(),"리사이클러뷰"  + arrayList.size());
                arrayList.add(mainData);
                mainAdapter.notifyDataSetChanged(); // 새로고침침

            }
        });


    }
}
