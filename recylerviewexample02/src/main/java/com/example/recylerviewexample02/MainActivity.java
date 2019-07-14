package com.example.recylerviewexample02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    // 서버 URl 설정
    final static private String url = "http://mdoli.dothome.co.kr/goodwordList.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,linearLayoutManager.getOrientation()));
        recyclerView.setLayoutManager(linearLayoutManager);

        // ArrayList에 person 객체(이름과 번호) 넣기
        final List<Person> person = new ArrayList<>();

        final RequestQueue queue = Volley.newRequestQueue(this);

        final StringRequest request = new StringRequest(Request.Method.POST, url,
                //요청 성공 시jsonObject.getJSONObject("body").getJSONObject("list")
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("jhTest", "[" + response + "]");


                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);

                            // Log.d("jhshin", ""+ jsonObject);

                            JSONObject mBody = jsonObject.getJSONObject("body");
                            JSONArray mList = mBody.getJSONArray("list");

                            Log.d("jhTest", "length : "+ mList.length());


                            String mContents = "";
                            String mAnswer = "";

                            for(int i=0;i<mList.length();i++){

                                //JSONObject item = mlist.getJSONObject(i);

                                //String mword = item.toString();

                                // Log.d("jhTest"," i : " + mList.getJSONArray(i).toString());

                                // Log.d("jhTest","" + mList.toString(i));
                                // Log.d("jhTest",""+mList.getJSONObject(2).getString("word"));

                                // mList.getJSONObject(2).getString("word");
                                mContents = mList.getJSONObject(i).getString("contents");
                                mAnswer = mList.getJSONObject(i).getString("answer");

                                Log.d("jhTest", "" + i + ". word : " + mContents);
                                Log.d("jhTest", ""+ i + ". answer : " + mAnswer);

                                // person.add(new Person(mContents,mAnswer));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                // 에러 발생 시
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("jhTest", "[" + error.getMessage() + "]");
                    }
                }) {
            // 요청보낼 때 추가로 파라미터가 필요할 경우
            // url?a=xxx 이런식으로 보내는 대신에 아래처럼 가능.
            //  @Override
            //  protected Map<String, String> getParams() throws AuthFailureError {
            //      Map<String, String> params = new HashMap<>();
            //      params.put("param1", "isGood");
            //      return  params;
            //  }
        };

        queue.add(request);

        person.add(new Person("김덕배","010-1234-5678"));
        person.add(new Person("강구팔","010-5678-1234"));
        person.add(new Person("이배윤","010-3412-7856"));
        person.add(new Person("버기","123-1256-3478"));
        person.add(new Person("김덕배","010-1234-5678"));
        person.add(new Person("강구팔","010-5678-1234"));
        person.add(new Person("이배윤","010-3412-7856"));
        person.add(new Person("버기","123-1256-3478"));


        // Adapter생성
        recyclerViewAdapter = new RecyclerViewAdapter(this,person);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
