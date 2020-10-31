package com.example.nasa.src.main.interfaces;

import com.example.nasa.src.main.models.APOD;
import com.example.nasa.src.main.models.TechTransfer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {
    //Astronomy Picture ot the Day
    @GET("planetary/apod")
    Call<APOD> getApod(
            @Query("date") String date,
            @Query("api_key") String API_KEY
    );

    //Tech Transfer
    @GET("techtransfer/{search}/")
    Call<TechTransfer> getTechTransfer(
            @Path("search") String search,
            @Query("") String keyword,
            @Query("api_key") String API_KEY
    );

//    @GET("/test/{number}")
//    Call<DefaultResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );
//
//    @POST("/test")
//    Call<DefaultResponse> postTest(@Body RequestBody params);
}
