package com.example.hashi.week3daily2alararmmanager.viewholder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hashi.week3daily2alararmmanager.R;


public class viewHolderPlaces extends RecyclerView.ViewHolder {

    public TextView categoryName;

    public viewHolderPlaces(@NonNull View itemView) {
        super(itemView);
        categoryName = itemView.findViewById(R.id.tvMajorCat);
    

    }
}
