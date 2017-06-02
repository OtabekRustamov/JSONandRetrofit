package com.example.root.jsonandretrofit;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private FloatingActionButton floatingActionButton;
    private ProgressBar progressBar;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        textView = (TextView) findViewById(R.id.tvTime);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fbRefresh);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        floatingActionButton.setOnClickListener(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        refresh();
    }

    private void refresh() {
        progressBar.setVisibility(View.VISIBLE);

        ApiService apiService = retrofit.create(ApiService.class);

        Call<TimeJson> timeJsonCall = apiService.getTime();
        timeJsonCall.enqueue(new Callback<TimeJson>() {
            @Override
            public void onResponse(Call<TimeJson> call, Response<TimeJson> response) {
                progressBar.setVisibility(View.GONE);
                Date date = new Date(response.body().getMilliseconds_since_epoch());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm ss");
                textView.setText(simpleDateFormat.format(date));
            }
            @Override
            public void onFailure(Call<TimeJson> call, Throwable t) {}
        });
    }

    @Override
    public void onClick(View v) {
        refresh();
    }
}
