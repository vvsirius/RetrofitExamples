package com.example.vv.retrofitexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetTimeActivity extends AppCompatActivity {

    TextView mDateTextView;
    TextView mTimeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_time);
        mDateTextView = (TextView) findViewById(R.id.date);
        mTimeTextView = (TextView) findViewById(R.id.time);

        fetchTime();

    }

    void fetchTime() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://date.jsontest.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsontestAPI api = retrofit.create(JsontestAPI.class);
        Call<ServerTime> serverTimeCall = api.getServerDateTime();
        serverTimeCall.enqueue(new Callback<ServerTime>() {
            @Override
            public void onResponse(Call<ServerTime> call, Response<ServerTime> response) {
                ServerTime serverTime = response.body();
                mDateTextView.setText("Date: " + serverTime.getDate());
                mTimeTextView.setText("Time: " + serverTime.getTime());
            }

            @Override
            public void onFailure(Call<ServerTime> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        R.string.Error + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
