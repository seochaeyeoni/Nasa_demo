package com.example.nasa.src.main.interfaces;

import com.example.nasa.src.main.models.APOD;
import com.example.nasa.src.main.models.TechTransfer;

public interface MainActivityView {
    void apodGetSuccess(APOD apod);

    void apodGetFailure(String message);

    void techTransferGetSuccess(TechTransfer techTransfer);

    void techTransferGetFailure(String message);
}
