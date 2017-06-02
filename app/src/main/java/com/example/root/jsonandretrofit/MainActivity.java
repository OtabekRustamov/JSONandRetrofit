package com.example.root.jsonandretrofit;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String URL_PATH = "http://date.jsontest.com/";

    private TextView textView;
    private FloatingActionButton floatingActionButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tvTime);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fbRefresh);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        floatingActionButton.setOnClickListener(this);
        refresh();
    }

    @Override
    public void onClick(View v) {
        refresh();
    }

    private void refresh () {
        (new TimeAsycTask()).execute();
    }

    class TimeAsycTask extends AsyncTask<Void, Void, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Long doInBackground(Void... params) {
            String resp = null;
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(URL_PATH);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                int t;
                resp = "";
                while ((t = in.read()) != -1) {
                    resp += (char) t;
                }

                Log.d("sss", resp);

                JSONObject jsonObject = new JSONObject(resp);

                return jsonObject.getLong("milliseconds_since_epoch");

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Long timeLong) {
            super.onPostExecute(timeLong);
            progressBar.setVisibility(View.GONE);

            if (timeLong != null) {
                Date date = new Date(timeLong);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm ss");

                textView.setText("" + simpleDateFormat.format(date));
            }
        }
    }
}