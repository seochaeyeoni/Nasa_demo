package com.example.nasa.src.splash.interfaces;

import com.example.nasa.src.main.models.APOD;
import com.example.nasa.src.main.models.TechTransfer;
import com.example.nasa.src.splash.models.EPIC;
import com.example.nasa.src.splash.models.EarthAsset;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SplashRetrofitInterface {
    //Astronomy Picture ot the Day
    @GET("planetary/apod")
    Call<APOD> getApod(
            //@Query("date") String date, //default가 today
            @Query("api_key") String API_KEY
    );

    //EPIC
    @GET("EPIC/api/natural")
    Call<ArrayList<EPIC>> getEPIC(
            @Query("api_key") String API_KEY
    );

    //Earth Asset
    @GET("planetary/earth/assets")
    Call<EarthAsset> getEarthAssets(
            @Query("lat") float lat,
            @Query("lon") float lon,
            //@Query("date") String date, //default가 most recent image
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
