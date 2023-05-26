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

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewholder> {
    List<ProductModel>productModels;
    Context context;

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
    context.startActivity(intent);
});



         holder.favo.setOnClickListener(v -> {

         });

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }
}
