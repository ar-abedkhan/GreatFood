package com.abedkhan.greatfood.Viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abedkhan.greatfood.R;

public class ProductViewholder extends RecyclerView.ViewHolder {

    public ImageView foodImg,favo;
    public TextView foodName,foodProce,resturentName;


    public ProductViewholder(@NonNull View itemView) {
        super(itemView);


        foodName=itemView.findViewById(R.id.foodName);
        foodImg=itemView.findViewById(R.id.foodImg);
        foodProce=itemView.findViewById(R.id.foodPrice);
        resturentName=itemView.findViewById(R.id.resturentName);
        favo=itemView.findViewById(R.id.favouriteBtn);
    }
}
