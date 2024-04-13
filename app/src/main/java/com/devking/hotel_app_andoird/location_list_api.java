package com.devking.hotel_app_andoird;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface location_list_api {
    @GET("locations/")
    Call<List<LocationListStructureClass>> get_location_list();
}
