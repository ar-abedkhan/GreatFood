package com.abedkhan.greatfood.Viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abedkhan.greatfood.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductViewholder extends RecyclerView.ViewHolder {

    public ImageView foodImg,favo;
    public TextView foodName,foodProce,resturentName;
 DatabaseReference databaseReference;

    public ProductViewholder(@NonNull View itemView) {
        super(itemView);


        foodName=itemView.findViewById(R.id.foodName);
        foodImg=itemView.findViewById(R.id.foodImg);
        foodProce=itemView.findViewById(R.id.foodPrice);
        resturentName=itemView.findViewById(R.id.resturentName);
        favo=itemView.findViewById(R.id.favouriteBtn);
    }

//    public void favoStatus(final String foodId , final String userid){
//
//        databaseReference= FirebaseDatabase.getInstance().getReference();
//
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        if (snapshot.child(foodId).hasChild(userid)){
//                            favo.setImageResource(R.drawable.ic_baseline_favorite_border_24);
//                        }else {
//                            favo.setImageResource(R.drawable.ic_baseline_favorite_24);
//                        }
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                }
//            });
//
//
//    }
}
