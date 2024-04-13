package com.devking.hotel_app_andoird;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationListStructureClass {
    @Expose
    @SerializedName("location")
    private String location;

    @Expose
    @SerializedName("location-id")
    private Integer locationId;

    public LocationListStructureClass() {
        // Required for Gson serialization/deserialization
    }

    public String getLocation() {
        return location;
    }


    public Integer getLocationId() {
        return locationId;
    }


}
