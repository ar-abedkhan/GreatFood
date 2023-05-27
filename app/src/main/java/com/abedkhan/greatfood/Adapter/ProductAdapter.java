package com.abedkhan.greatfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abedkhan.greatfood.MainActivity;
import com.abedkhan.greatfood.Model.ProductModel;
import com.abedkhan.greatfood.R;
import com.abedkhan.greatfood.Viewholder.ProductViewholder;
import com.abedkhan.greatfood.drawer;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewholder> {
    List<ProductModel>productModels;
    Context context;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    Boolean clicked =false;



    public ProductAdapter(List<ProductModel> productModels, Context context) {
        this.productModels = productModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.foodrecyclerdesign,parent,false);
        return new ProductViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewholder holder, int position) {
         ProductModel productModel=productModels.get(position);

         try {
            holder.foodProce.setText(productModel.getFoodPrice());
             holder.foodName.setText(productModel.getFoodName());
             holder.resturentName.setText(productModel.getResturentName());
             Glide.with(context).load(productModel.getFoodImg()).into(holder.foodImg);

         }catch (Exception e){

         }




holder.itemView.setOnClickListener(v -> {
    Intent intent = new Intent(context, MainActivity.class);
    intent.putExtra("details", true);
    intent.putExtra("Id", productModel.getFoodId());
    context.startActivity(intent);
});

        databaseReference = FirebaseDatabase.getInstance().getReference("favo");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
      String foodId=productModel.getFoodId();
        //String foodId= databaseReference.getKey();

        String userid=firebaseUser.getUid();

         holder.favo.setOnClickListener(v -> {

             clicked=true;
       holder.favo.setImageResource(R.drawable.ic_baseline_favorite_24);

             databaseReference.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String userId = snapshot.getKey();
                     if(clicked==true){
                         if (snapshot.child(foodId).hasChild(userid)){

                             databaseReference.child(foodId).removeValue();
                             clicked=false;
                        holder.favo.setImageResource(R.drawable.ic_baseline_favorite_border_24);

                         }else {
                             databaseReference.child(foodId).child(userid).setValue(true);;
                             clicked=true;
                          holder.favo.setImageResource(R.drawable.ic_baseline_favorite_24);
                         }
                     }
                 }
                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {
                 }
             });



//             databaseReference.child("favo").child(foodId).addValueEventListener(new ValueEventListener() {
//                 @Override
//                 public void onDataChange(@NonNull DataSnapshot snapshot) {
////                TODO: get total length
////                Log.i("TAG", "Notification snapshot: "+ snapshot.getChildren().toString());
//                     List<String> likeSize = new ArrayList<>();
//                     for (DataSnapshot snap: snapshot.getChildren()) {
//                         String userId = snap.getKey();
//                         likeSize.add(userId);
//
//                         if (userId.equals(currentUser)){
//                             binding.postNonReact.setVisibility(View.GONE);
//                             binding.postReacted.setVisibility(View.VISIBLE);
//                         }
//                     }
//
////                     try {
////                         totalLikes = likeSize.size();
////                         binding.postReactCount.setText(likeSize.size()+"");
////                     }catch (Exception e){
////                         binding.postReactCount.setText("0");
////                     }
//                 }
//                 @Override
//                 public void onCancelled(@NonNull DatabaseError error) {
//
//                 }
//             });
//


         });


//         holder.favoStatus(foodId,userid);

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }
}
