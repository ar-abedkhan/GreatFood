package com.abedkhan.greatfood.Fragmrnt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abedkhan.greatfood.MainActivity;
import com.abedkhan.greatfood.R;
import com.abedkhan.greatfood.databinding.FragmentRegistrationBinding;
import com.abedkhan.greatfood.drawer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrationFragment extends Fragment {

    public RegistrationFragment() {
        // Required empty public constructor
    }
FragmentRegistrationBinding binding;
    String fullName, userPass, email;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentRegistrationBinding.inflate(getLayoutInflater(),container,false);

//        Firebase settings
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        binding.logeinButton.setOnClickListener(view -> {

            Intent intent = new Intent(requireContext(), MainActivity.class);
            intent.putExtra("logein", true);
            startActivity(intent);

        });


        binding.registrationButton.setOnClickListener(v -> {

            fullName = binding.userName.getText().toString().trim();
            userPass = binding.signupPassword.getText().toString().trim();
            email = binding.userMail.getText().toString().trim();

            if (fullName.isEmpty()){
                binding.userName.setError("Field cannot be empty!");
            } else if (userPass.isEmpty()) {
                binding.signupPassword.setError("Field cannot be empty!");
            } else if (email.isEmpty()) {
                binding.userMail.setError("Field cannot be empty!");

            }else {
                saveData(email,userPass);
            }




        });






















        return binding.getRoot();
    }

    private void saveData(String email, String userPass) {

        binding.registrationButton.setVisibility(View.GONE);
        binding.progressbar.setVisibility(View.VISIBLE);
        Log.i("tag", "save data to: ");

      firebaseAuth.createUserWithEmailAndPassword(email,userPass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
          @Override
          public void onSuccess(AuthResult authResult) {
              firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
              Log.i("tag", "create: ");

              Map<String,Object> userMap=new HashMap<>();
              userMap.put("userId",firebaseUser.getUid());
              userMap.put("userName",fullName);
              userMap.put("UserMail", email);
              userMap.put("userPass", userPass);

              databaseReference.child("User").child(firebaseUser.getUid()).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      Log.i("tag", "send database: "+task);

                      if (task.isSuccessful()){
                          Toast.makeText(getContext(), fullName+" You are register successfully❤", Toast.LENGTH_LONG).show();
                          Toast.makeText(getContext(), fullName+" Welcome to Writer's Link❤", Toast.LENGTH_LONG).show();
                          Log.i("tag", "success ");


                          Intent intent = new Intent(requireContext(), drawer.class);
                          intent.putExtra("home", true);
                          startActivity(intent);
                      }
                      else {
                          Log.i("tag", "failed ");

                          showAlert("Error", task.getException().getLocalizedMessage());
                      }
                      binding.registrationButton.setVisibility(View.GONE);
                      binding.progressbar.setVisibility(View.VISIBLE);
                  }
              });
          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              showAlert("Failed", e.getLocalizedMessage());
              binding.registrationButton.setVisibility(View.VISIBLE);
              binding.progressbar.setVisibility(View.GONE);
          }
      });








    }
    private void showAlert(String title, String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setIcon(R.drawable.ic_baseline_warning_24);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}