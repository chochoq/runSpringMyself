package com.example.a1106;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService {

    public static final String BASE_URL="http://192.168.0.106:8088/addr/";

    @GET("list")
    Call<List<AddrVO>> list(@Query("query") String query);

    @GET("insert")
    Call<Void> insert(@Query("name") String name,
                      @Query("tel") String tel,
                      @Query("addr") String addr);

    @GET("update")
    Call<Void> update(@Query("name") String name,
                      @Query("tel") String tel,
                      @Query("addr") String addr, @Query("seq") int seq);

    @GET("delete")
    Call<Void> delete(@Query("seq") int seq);

}
