package com.example.hashi.week3daily2alararmmanager.viewholder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hashi.week3daily2alararmmanager.R;


public class viewHolderPlaces extends RecyclerView.ViewHolder {

    public TextView uname,uemail,uphone;
    public ImageView uimage;
    public LinearLayout linearLayout;

    public TextView categoryName;

    public viewHolderPlaces(@NonNull View itemView) {
        super(itemView);
        categoryName = itemView.findViewById(R.id.tvMajorCat);

        uname=itemView.findViewById(R.id.tvName);
        uemail=itemView.findViewById(R.id.tvEmail);
        uphone=itemView.findViewById(R.id.tvPhone);
        uimage=itemView.findViewById(R.id.iv_image);

        linearLayout = itemView.findViewById(R.id.layout);


    }
}
