package com.devking.hotel_app_andoird;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Explore_hotel_list_api {
    @POST("hotel-filter/")
    Call<ArrayList<hotel_list_structure_class>> get_hotel_list(@Body Explore_request_post requestPost);
}
