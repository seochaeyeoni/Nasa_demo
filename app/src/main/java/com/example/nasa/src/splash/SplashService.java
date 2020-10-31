package com.example.nasa.src.splash;

import android.util.Log;

import com.example.nasa.src.main.models.APOD;
import com.example.nasa.src.splash.interfaces.SplashActivityView;
import com.example.nasa.src.splash.interfaces.SplashRetrofitInterface;
import com.example.nasa.src.splash.models.EPIC;
import com.example.nasa.src.splash.models.EarthAsset;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.nasa.src.ApplicationClass.getRetrofit;

public class SplashService {
    private final SplashActivityView mSplashActivityView;

    SplashService(final SplashActivityView splashActivityView) {
        this.mSplashActivityView = splashActivityView;
    }

    String API_KEY = "N6z00feDaoh1VgRcZJwtCME4bOf6KdF1hMJc9G4U";

    // Astronomy Picture of the Day
    void getApod() {
        final SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);
        splashRetrofitInterface.getApod(API_KEY).enqueue(new Callback<APOD>() {
            @Override
            public void onResponse(Call<APOD> call, Response<APOD> response) {
                final APOD apod = response.body();
                if (apod == null) {
                    mSplashActivityView.apodGetFailure(null);
                    return;
                }

                mSplashActivityView.apodGetSuccess(apod);
            }

            @Override
            public void onFailure(Call<APOD> call, Throwable t) {
                mSplashActivityView.apodGetFailure(null);

            }
        });
    }

    // EPIC
    void getEPIC() {
        final SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);
        splashRetrofitInterface.getEPIC(API_KEY).enqueue(new Callback<ArrayList<EPIC>>() {
            @Override
            public void onResponse(Call<ArrayList<EPIC>> call, Response<ArrayList<EPIC>> response) {
                final ArrayList<EPIC> epic = response.body();
                if (epic == null) {
                    Log.d("epic", String.valueOf(epic));
                    mSplashActivityView.epicGetFailure(null);
                    return;
                }
                mSplashActivityView.epicGetSuccess(epic);
            }

            @Override
            public void onFailure(Call<ArrayList<EPIC>> call, Throwable t) {
                Log.d("epic", String.valueOf(t));
                mSplashActivityView.epicGetFailure(null);

            }
        });
    }


    // Earth Asset
    void getEarthAssets() {
        final SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);
        splashRetrofitInterface.getEarthAssets(37.56f,127.05f,API_KEY).enqueue(new Callback<EarthAsset>() {
            @Override
            public void onResponse(Call<EarthAsset> call, Response<EarthAsset> response) {
                final EarthAsset earthAsset = response.body();
                if (earthAsset == null) {
                    mSplashActivityView.earthAssetGetFailure(null);
                    return;
                }
                mSplashActivityView.earthAssetGetSuccess(earthAsset);
            }

            @Override
            public void onFailure(Call<EarthAsset> call, Throwable t) {
                mSplashActivityView.earthAssetGetFailure(null);

            }
        });
    }
}
