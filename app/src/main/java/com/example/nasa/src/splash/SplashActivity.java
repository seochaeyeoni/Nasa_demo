package com.example.nasa.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nasa.R;
import com.example.nasa.src.main.ApodFrag;
import com.example.nasa.src.main.MainActivity;
import com.example.nasa.src.main.models.APOD;
import com.example.nasa.src.splash.interfaces.SplashActivityView;
import com.example.nasa.src.splash.models.EPIC;
import com.example.nasa.src.splash.models.EarthAsset;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity implements SplashActivityView {
    private FragmentManager fm;
    private FragmentTransaction ft;
    Intent intent;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        intent = new Intent(SplashActivity.this, MainActivity.class);

        tryGetEverything(); //여기서 해야 되네!


        startLoading();
    }

    private void startLoading() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tryGetEverything(); //여기는 안되네! 왜?
                startActivity(intent);
                finish();
            }
        }, 2000);
    }


    private void tryGetEverything() {

        final SplashService splashService = new SplashService(this);
        splashService.getApod();
        splashService.getEarthAssets();
        splashService.getEPIC();
    }

    @Override
    public void apodGetSuccess(APOD apod) {

        intent.putExtra("apod_title", apod.getTitle());
        intent.putExtra("apod_date", apod.getDate());
        intent.putExtra("apod_explanation", " " + apod.getExplanation());
        intent.putExtra("apod_image", apod.getUrl());


    }

    @Override
    public void apodGetFailure(@Nullable String message) {

    }

    @Override
    public void epicGetSuccess(ArrayList<EPIC> epic) {
        ArrayList<String> epic_caption = new ArrayList<>();
        ArrayList<String> epic_image = new ArrayList<>();
        ArrayList<String> epic_date = new ArrayList<>();
        ArrayList<String> epic_lat = new ArrayList<>();
        ArrayList<String> epic_lon = new ArrayList<>();

        for (int i=0; i<epic.size(); i++) {
            epic_caption.add(epic.get(i).getCaption());
            epic_image.add(epic.get(i).getImage());
            epic_date.add(epic.get(i).getDate());
            epic_lat.add(Double.toString(epic.get(i).getCentroidCoordinates().getLat()));
            epic_lon.add(Double.toString(epic.get(i).getCentroidCoordinates().getLon()));
        }


        intent.putExtra("epic_caption", epic_caption);
        intent.putExtra("epic_image", epic_image);
        intent.putExtra("epic_date", epic_date);
        intent.putExtra("epic_lat", epic_lat);
        intent.putExtra("epic_lon", epic_lon);

    }

    @Override
    public void epicGetFailure(String message) {

    }

    @Override
    public void earthAssetGetSuccess(EarthAsset earthAsset) {


        intent.putExtra("asset_date",earthAsset.getDate());
        intent.putExtra("asset_id",earthAsset.getId());
        intent.putExtra("asset_image",earthAsset.getUrl());

    }

    @Override
    public void earthAssetGetFailure(String message) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
