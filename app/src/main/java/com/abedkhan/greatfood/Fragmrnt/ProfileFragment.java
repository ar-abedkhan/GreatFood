package com.abedkhan.greatfood.Fragmrnt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abedkhan.greatfood.Model.UserModel;
import com.abedkhan.greatfood.R;
import com.abedkhan.greatfood.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }
FragmentProfileBinding binding;
    String currentUser;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding=FragmentProfileBinding.inflate(getLayoutInflater(),container,false);






        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUser=firebaseUser.getUid();



databaseReference.child("User").child(currentUser).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        UserModel userModel=snapshot.getValue(UserModel.class);
        if (userModel!=null){
            binding.userName.setText(userModel.getUserName());
            binding.userMail.setText(userModel.getUserMail());
            binding.userPass.setText(userModel.getUserPass());
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});








        return binding.getRoot();
    }
}