package com.example.nasa.src.splash.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EPIC {

    @SerializedName("identifier")
    @Expose
    private String identifier;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("centroid_coordinates")
    @Expose
    private CentroidCoordinates centroidCoordinates;
    @SerializedName("date")
    @Expose
    private String date;


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public CentroidCoordinates getCentroidCoordinates() {
        return centroidCoordinates;
    }

    public void setCentroidCoordinates(CentroidCoordinates centroidCoordinates) {
        this.centroidCoordinates = centroidCoordinates;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static class CentroidCoordinates {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lon")
        @Expose
        private Double lon;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

    }
}