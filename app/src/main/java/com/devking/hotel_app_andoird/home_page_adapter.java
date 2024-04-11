package com.devking.hotel_app_andoird;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devking.hotel_app_andoird.R;

import java.util.ArrayList;

public class home_page_adapter extends RecyclerView.Adapter<home_page_adapter.View_Holder> {

    public ArrayList<hotel_list_structure_class> hotel_list;

    Context context;

    public home_page_adapter(ArrayList<hotel_list_structure_class> hotel_list,Context context) {
        this.hotel_list=hotel_list;
        this.context = context;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_layout,parent,false);
        return new View_Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        hotel_list_structure_class currentitem = hotel_list.get(position);
        if(currentitem != null){
            holder.hotel_name.setText(currentitem.getHotel_name());
            holder.hotel_location.setText((currentitem.getHotel_location()));
        }

    }

    @Override
    public int getItemCount() {
        return (hotel_list != null) ? hotel_list.size() : 0;
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView hotel_name ,hotel_location;

        public View_Holder(@NonNull View itemView) {
            super(itemView);
            hotel_name = itemView.findViewById(R.id.hotel_name);
            hotel_location = itemView.findViewById(R.id.hotel_location);
        }
    }

    public void updateadapter(ArrayList<hotel_list_structure_class> newData){
            hotel_list = newData ;
            notifyDataSetChanged();

    }
}
