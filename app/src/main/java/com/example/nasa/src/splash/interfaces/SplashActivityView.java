package com.example.nasa.src.splash.interfaces;

import com.example.nasa.src.main.models.APOD;
import com.example.nasa.src.splash.models.EPIC;
import com.example.nasa.src.splash.models.EarthAsset;

import java.util.ArrayList;
import java.util.List;

public interface SplashActivityView {
    void apodGetSuccess(APOD apod);

    void apodGetFailure(String message);

    void earthAssetGetSuccess(EarthAsset earthAsset);

    void earthAssetGetFailure(String message);

    void epicGetSuccess(ArrayList<EPIC> epic);

    void epicGetFailure(String message);
}
