
package com.example.nasa.src.splash.models;


import com.google.gson.annotations.SerializedName;

public class EarthAsset {

    @SerializedName("date")
    private String mDate;
    @SerializedName("id")
    private String mId;
    @SerializedName("resource")
    private Resource mResource;
    @SerializedName("service_version")
    private String mServiceVersion;
    @SerializedName("url")
    private String mUrl;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Resource getResource() {
        return mResource;
    }

    public void setResource(Resource resource) {
        mResource = resource;
    }

    public String getServiceVersion() {
        return mServiceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        mServiceVersion = serviceVersion;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }


    public static class Resource {

        @SerializedName("dataset")
        private String mDataset;
        @SerializedName("planet")
        private String mPlanet;

        public String getDataset() {
            return mDataset;
        }

        public void setDataset(String dataset) {
            mDataset = dataset;
        }

        public String getPlanet() {
            return mPlanet;
        }

        public void setPlanet(String planet) {
            mPlanet = planet;
        }

    }
}
