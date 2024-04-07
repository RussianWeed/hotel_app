package com.devking.hotel_app_andoird;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface hotel_list_api_call {

    @GET("hotel-list/")
    Call<List<hotel_list_structure_class>> get_hotel_list();
}
