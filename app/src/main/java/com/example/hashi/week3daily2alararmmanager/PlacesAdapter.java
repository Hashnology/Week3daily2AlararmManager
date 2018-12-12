package com.example.hashi.week3daily2alararmmanager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.hashi.week3daily2alararmmanager.model.MyPlaces;
import com.example.hashi.week3daily2alararmmanager.viewholder.viewHolderPlaces;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<viewHolderPlaces> {

    //    private String[] data;
    private Context context;
    private LayoutInflater inflater;
    private MyPlaces current;
    private List<MyPlaces> data;
    private viewHolderPlaces viewHolder;
    private Bundle savedInstanceState;

    public PlacesAdapter(Context context, List<MyPlaces> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolderPlaces onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view, parent, false);


        viewHolder = new viewHolderPlaces(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolderPlaces holder, int position) {
//        String title = data[position];
//        holder.categoryName.setText(title);

        current = data.get(position);

        final String name = current.getName();


        viewHolder.categoryName.setText(name);





        viewHolder.categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                Fragment fragment = new Detailed_Fragment();
//                int pos=holder.getAdapterPosition();
//                String add=current.getMyplace();
//                String lat=current.getLat();
//                String lang=current.getLang();
//                Bundle bundle=new Bundle();
//                bundle.putString("address",add);
//                bundle.putString("lat",lat);
//                bundle.putString("lang",lang);
//                fragment.setArguments(bundle);
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
