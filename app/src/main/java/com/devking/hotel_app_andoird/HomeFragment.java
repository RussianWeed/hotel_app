package com.devking.hotel_app_andoird;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<hotel_list_structure_class> hotel_list;

    home_page_adapter adapter;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.hotel_list);
        adapter = new home_page_adapter(hotel_list,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        getData();
        return v;
    }

    private void getData() {

        String url = "http://10.0.2.2:3000/data/";

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(hotel_list_structure_class.class, new Hotel_item_instance())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        hotel_list_api_call call = retrofit.create(hotel_list_api_call.class);

        Call<List<hotel_list_structure_class>> unsorted_hotel_list_data = call.get_hotel_list();

        unsorted_hotel_list_data.enqueue(new Callback<List<hotel_list_structure_class>>() {
            @Override
            public void onResponse(Call<List<hotel_list_structure_class>> call, Response<List<hotel_list_structure_class>> response) {
                if(response.isSuccessful() && response.body() != null){
                    hotel_list = new ArrayList<>(response.body());
                    adapter.updateadapter(hotel_list);
                }
            }

            @Override
            public void onFailure(Call<List<hotel_list_structure_class>> call, Throwable t) {
                Log.e("API Call", "Failed: " + t.getMessage());
            }
        });


    }
}