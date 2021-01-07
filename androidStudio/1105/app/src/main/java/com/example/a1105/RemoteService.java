package com.example.a1105;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RemoteService {
    public static final String BASE_URL="http://192.168.0.106:8088/product/";

    //리스트는 VO에 있는 모든것이라서 List(배열)로 가져옴
    @GET("list")
    Call<List<ProductVO>> list(@Query("query") String query,
                               @Query("key") String key,
                               @Query("order") String order);


    //리턴이 없는 값이라서 void
    @GET("delete")
    Call<Void> delete(@Query("code") String code);

    //배열이 아니고 vo에서 가지고있는 값하나?라서
    @GET("read")
    Call<ProductVO> read(@Query("code") String code);

    @Multipart
    @POST("insert")
    Call<Void> insert(@Part("code") RequestBody strCode,
                              @Part("name") RequestBody strName,
                              @Part("price") RequestBody strPrice,
                              @Part MultipartBody.Part image);


}
