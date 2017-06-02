package com.example.root.jsonandretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by root on 5/29/17.
 */

public interface ApiService {

    @GET(MainActivity.URL_PATH)
    Call<TimeJson> getTime();

}
