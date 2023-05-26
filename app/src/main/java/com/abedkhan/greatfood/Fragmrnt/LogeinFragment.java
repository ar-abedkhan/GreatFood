package com.abedkhan.greatfood.Fragmrnt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abedkhan.greatfood.MainActivity;
import com.abedkhan.greatfood.R;
import com.abedkhan.greatfood.databinding.FragmentLogeinBinding;
import com.abedkhan.greatfood.drawer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LogeinFragment extends Fragment {
    public LogeinFragment() {
        // Required empty public constructor
    }
FragmentLogeinBinding binding;
    String userPass, email;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentLogeinBinding.inflate(getLayoutInflater(),container,false);




        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        checking if anyone has already logged in!
        if (firebaseUser != null){
            startActivity(new Intent(getActivity(), drawer.class));
            getActivity().finish();
        }

        firebaseAuth = FirebaseAuth.getInstance();



        binding.registerOption.setOnClickListener(view -> {

                Intent intent = new Intent(requireContext(), MainActivity.class);
                intent.putExtra("registration", true);
                startActivity(intent);

        });


        binding.loginButton.setOnClickListener(view -> {
            userPass = binding.signupPassword.getText().toString().trim();
            email = binding.userMail.getText().toString().trim();

            if (userPass.isEmpty()) {
                binding.signupPassword.setError("Field cannot be empty!");
            } else if (email.isEmpty()) {
                binding.userMail.setError("Field cannot be empty!");

            }else {
                saveData(email,userPass);
            }



//                Intent intent = new Intent(requireContext(), MainActivity.class);
//                intent.putExtra("registration", true);
//                startActivity(intent);

        });



















        return binding.getRoot();
    }

    private void saveData(String email, String userPass) {

        binding.loginButton.setVisibility(View.GONE);
        binding.progressbar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(email,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Intent intent = new Intent(requireContext(), drawer.class);
                    intent.putExtra("home", true);
                    startActivity(intent);
                }else {
                    binding.loginButton.setVisibility(View.VISIBLE);
                    binding.progressbar.setVisibility(View.GONE);

                    showAlert("Error!",task.getException().getLocalizedMessage());

                }

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