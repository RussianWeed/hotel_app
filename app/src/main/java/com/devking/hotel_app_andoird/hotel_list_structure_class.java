package com.devking.hotel_app_andoird;
import com.google.gson.annotations.SerializedName;
public class hotel_list_structure_class {

   @SerializedName("hotel_location")
   String hotel_Location;

   @SerializedName("hotel_name")
    String hotel_Name;

   public  hotel_list_structure_class(){

    }

    public String getHotel_name() {
        return hotel_Name;
    }

    public String getHotel_location() {
        return hotel_Location;
    }
}
