package com.abedkhan.greatfood.Fragmrnt;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abedkhan.greatfood.Model.ProductModel;
import com.abedkhan.greatfood.Model.UserModel;
import com.abedkhan.greatfood.R;
import com.abedkhan.greatfood.databinding.FragmentDetailsBinding;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailsFragment extends Fragment {

    public DetailsFragment() {
    }
FragmentDetailsBinding binding;
    String foodId, currentUser;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentDetailsBinding.inflate(getLayoutInflater(),container,false);

        intent =getActivity().getIntent();
        foodId=intent.getStringExtra("Id");




        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUser=firebaseUser.getUid();




databaseReference.child("food").child(foodId).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

        ProductModel productModel=snapshot.getValue(ProductModel.class);

        if (productModel != null) {

            binding.foodName.setText(productModel.getFoodName());
            binding.resturentName.setText(productModel.getResturentName());
            binding.fullLocation.setText(productModel.getLocation());
            binding.foodPrice.setText(productModel.getFoodPrice());
            binding.foodDes.setText(productModel.getFoodDes());

            Glide.with(getActivity()).load(productModel.getFoodImg()).into(binding.foodImg);

        }else {
            binding.foodName.setText("Food Name");
            binding.resturentName.setText("Resturent Name");
            binding.fullLocation.setText("Resturent Location");
            binding.foodPrice.setText("Food Price");
            binding.foodDes.setText("Food Fill Description");

            Glide.with(getActivity()).load(productModel.getFoodImg()).placeholder(R.drawable.food1).into(binding.foodImg);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});



















        return binding.getRoot();
    }
}