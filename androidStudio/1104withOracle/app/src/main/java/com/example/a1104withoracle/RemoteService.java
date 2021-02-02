package com.example.a1104withoracle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService {
    public  static final String BASE_URL="http://192.168.0.106:8088/addr/";

    @GET("list")
    Call<List<addVO>> list(@Query("query") String query);


}
