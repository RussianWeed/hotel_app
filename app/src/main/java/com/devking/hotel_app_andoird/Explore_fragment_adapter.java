package com.devking.hotel_app_andoird;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Explore_fragment_adapter extends RecyclerView.Adapter<Explore_fragment_adapter.View_Holder_Explore_Fragment> {

    ArrayList<hotel_list_structure_class> hotel_list;
    public Explore_fragment_adapter(ArrayList<hotel_list_structure_class> hotel_list) {
        this.hotel_list=hotel_list;
    }

    @NonNull
    @Override
    public View_Holder_Explore_Fragment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_layout,parent,false);
        return new View_Holder_Explore_Fragment(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder_Explore_Fragment holder, int position) {
        hotel_list_structure_class current_item = hotel_list.get(position);
        holder.hotel_name.setText(current_item.getHotel_name());
    }

    @Override
    public int getItemCount() {
        return (hotel_list != null) ? hotel_list.size() : 0;
    }

    public class View_Holder_Explore_Fragment extends RecyclerView.ViewHolder {

        TextView hotel_name;

        public View_Holder_Explore_Fragment(@NonNull View itemView) {
            super(itemView);
            hotel_name = itemView.findViewById(R.id.hotel_name);
        }
    }
}
