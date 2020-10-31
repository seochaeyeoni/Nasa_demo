package com.example.nasa.src.main;

import android.util.Log;

import com.example.nasa.src.main.interfaces.MainActivityView;
import com.example.nasa.src.main.interfaces.MainRetrofitInterface;
import com.example.nasa.src.main.models.APOD;
import com.example.nasa.src.main.models.TechTransfer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.nasa.src.ApplicationClass.DATE_FORMAT;
import static com.example.nasa.src.ApplicationClass.date;
import static com.example.nasa.src.ApplicationClass.getRetrofit;
import static com.example.nasa.src.ApplicationClass.keyword;
import static com.example.nasa.src.ApplicationClass.search;

public class MainService {
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    String API_KEY = "N6z00feDaoh1VgRcZJwtCME4bOf6KdF1hMJc9G4U";

    // Astronomy Picture of the Day
    void getApod() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getApod(date,API_KEY).enqueue(new Callback<APOD>() {
            @Override
            public void onResponse(Call<APOD> call, Response<APOD> response) {
                final APOD apod = response.body();
                if (apod == null) {
                    mMainActivityView.apodGetFailure(null);
                    return;
                }

                mMainActivityView.apodGetSuccess(apod);
            }

            @Override
            public void onFailure(Call<APOD> call, Throwable t) {
                mMainActivityView.apodGetFailure(null);

            }
        });
    }

    // Tech Transfer
    void getTechTransfer() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTechTransfer(search,keyword,API_KEY).enqueue(new Callback<TechTransfer>() {
            @Override
            public void onResponse(Call<TechTransfer> call, Response<TechTransfer> response) {
                final TechTransfer techTransfer = response.body();
                Log.d("tech","1");
                if (techTransfer == null) {
                    Log.d("tech","2");

                    mMainActivityView.techTransferGetFailure(null);
                    return;
                }

                mMainActivityView.techTransferGetSuccess(techTransfer);
            }

            @Override
            public void onFailure(Call<TechTransfer> call, Throwable t) {
                Log.d("tech", String.valueOf(t));

                mMainActivityView.techTransferGetFailure(null);

            }
        });
    }
}
