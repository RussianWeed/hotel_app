package com.devking.hotel_app_andoird;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment {

    EditText username_tv,gmail_tv,password1_tv,password2_tv;
    public String username,gmail,password1,password2;
    List<Profile_signup_structure> message;
    Button signup;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_profile, container, false);

        username_tv = rootview.findViewById(R.id.signup_name);
        gmail_tv = rootview.findViewById(R.id.signup_email);
        password1_tv = rootview.findViewById(R.id.signup_password);
        password2_tv = rootview.findViewById(R.id.signup_confirm_password);
        signup = rootview.findViewById(R.id.signup_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = username_tv.getText().toString();
                gmail = gmail_tv.getText().toString();
                password1 = password1_tv.getText().toString();
                password2 = password2_tv.getText().toString();
                signup_method();
                FragmentTransaction tr = getParentFragmentManager().beginTransaction();
                tr.replace(R.id.framelayout,new ProfileFragment());
                tr.commit();

            }
        });
        return rootview;
    }

    private void signup_method() {

        String url = "http://10.0.2.2:3000/auth/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Profile_signup_api call = retrofit.create(Profile_signup_api.class);

        call.create_user(username,gmail,password1,password2).enqueue(new Callback<List<Profile_signup_structure>>() {
            @Override
            public void onResponse(Call<List<Profile_signup_structure>> call, Response<List<Profile_signup_structure>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    message = response.body();
                    if (message.isEmpty()== false){
                        String toast_msg = message.get(0).getMessage().toString();
                        Log.d("ToastMessage", toast_msg);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Profile_signup_structure>> call, Throwable throwable) {

            }
        });

    }

}