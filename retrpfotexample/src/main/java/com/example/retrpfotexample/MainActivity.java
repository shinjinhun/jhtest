package com.example.retrpfotexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewHeroes);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {

            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroes = response.body();


                for(Hero h: heroes) {
                    Log.d("jhTest","name :" + h.getRealname() );
                    Log.d("jhTest","realname :" + h.getRealname() );
                    Log.d("jhTest","firstappearance :" + h.getFirstappearance() );
                    Log.d("jhTest","createdby :" + h.getCreatedby() );
                    Log.d("jhTest","publisher :" + h.getPublisher() );
                    Log.d("jhTest","imageurl :" + h.getImageurl() );
                    Log.d("jhTest","bio :" + h.getBio() );
                }

                //Creating an String array for the ListView
                String[] heroNames = new String[heroes.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroes.size(); i++) {
                    heroNames[i] = heroes.get(i).getName();
                }

                //displaying the string array into listview
                listView.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                heroNames
                        )
                );
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
