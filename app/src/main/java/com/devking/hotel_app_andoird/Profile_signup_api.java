package com.devking.hotel_app_andoird;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Profile_signup_api {

    @FormUrlEncoded
    @POST("signup/")
    Call<List<Profile_signup_structure>> create_user(@Field("username") String username, @Field("gmail") String gmail, @Field("password1") String password1 , @Field("password2") String password2);
}
