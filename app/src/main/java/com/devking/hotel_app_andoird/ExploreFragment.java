package com.devking.hotel_app_andoird;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExploreFragment extends Fragment {

    Calendar calendar;
    TextView check_in, check_out;
    ImageButton checkin_img, checkout_img;
    DatePickerDialog datePickerDialog;
    AutoCompleteTextView drop_down_menu;
    String[] location_list_string;
    ArrayList<LocationListStructureClass> location_list;
    ArrayAdapter<String> arrayAdapter;

    public ExploreFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);
        check_in = rootView.findViewById(R.id.ckeck_in);
        check_out = rootView.findViewById(R.id.check_out);
        checkin_img = rootView.findViewById(R.id.checkin_img);
        checkout_img = rootView.findViewById(R.id.checkout_img);
        calendar = Calendar.getInstance();
        drop_down_menu = rootView.findViewById(R.id.drop_down_menu);

        // data initialization for location list array
        get_location_list_array();

        // check_in calendar setup
        checkin_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        check_in.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        // check_out calendar setup
        checkout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        check_out.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });


        // drop down menu setup
        drop_down_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click if needed
            }
        });

        return rootView;
    }



    private void get_location_list_array() {
        String url = "http://10.0.2.2:3000/data/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        location_list_api apiCall = retrofit.create(location_list_api.class);

        Call<List<LocationListStructureClass>> unsorted_location_list = apiCall.get_location_list();

        unsorted_location_list.enqueue(new Callback<List<LocationListStructureClass>>() {
            @Override
            public void onResponse(Call<List<LocationListStructureClass>> call, Response<List<LocationListStructureClass>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    location_list = new ArrayList<>(response.body());
                    set_location_list();
                }
            }

            @Override
            public void onFailure(Call<List<LocationListStructureClass>> call, Throwable throwable) {
                Log.e("API Call", "Failed: " + throwable.getMessage());
            }
        });
    }



    private void set_location_list() {
        if (location_list != null) {
            location_list_string = new String[location_list.size()]; // Initialize the array with the correct size

            for (int i = 0; i < location_list.size(); i++) {
                location_list_string[i] = location_list.get(i).getLocation();
            }
        }

        else {
            location_list_string = new String[]{"[]"};
        }

        // Set up ArrayAdapter and attach it to drop_down_menu
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, location_list_string);
        drop_down_menu.setAdapter(arrayAdapter);

    }
}
