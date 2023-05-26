package com.abedkhan.greatfood.Fragmrnt;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abedkhan.greatfood.Adapter.ProductAdapter;
import com.abedkhan.greatfood.MainActivity;
import com.abedkhan.greatfood.Model.ProductModel;
import com.abedkhan.greatfood.R;
import com.abedkhan.greatfood.databinding.FragmentHomeBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }
FragmentHomeBinding binding;
    List<ProductModel>productModelList=new ArrayList<>();
    ProductAdapter adapter;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
String currentUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    binding=FragmentHomeBinding.inflate(getLayoutInflater(),container,false);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        currentUser=firebaseUser.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference();


        binding.progressbar.setVisibility(View.VISIBLE);


        try {
            databaseReference.child("food").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
           productModelList.clear();
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                ProductModel productModel=dataSnapshot.getValue(ProductModel.class);

                                    productModelList.add(productModel);
                                    binding.progressbar.setVisibility(View.GONE);
                        adapter=new ProductAdapter(productModelList,getContext());
                        binding.postRecycler.setAdapter(adapter);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }catch (Exception e){


        }




        return binding.getRoot();
    }
}