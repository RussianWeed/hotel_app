package com.devking.hotel_app_andoird;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

public class Hotel_item_instance implements InstanceCreator<hotel_list_structure_class> {
    @Override
    public hotel_list_structure_class createInstance(Type type) {
        return new hotel_list_structure_class();
    }
}
