package com.abedkhan.greatfood.Fragmrnt;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abedkhan.greatfood.R;
import com.abedkhan.greatfood.databinding.FragmentAddBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class AddFragment extends Fragment {

    public AddFragment() {
        // Required empty public constructor
    }
FragmentAddBinding binding;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    StorageReference storageReference;
    String foodName,restureNtname,foodImg,resturentLocation,foodPrice,foodDes;
    Uri uri;
    String foodID, ownerID,foodImgUrl="";







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     binding=FragmentAddBinding.inflate(getLayoutInflater(),container,false);



     firebaseAuth=FirebaseAuth.getInstance();
     firebaseUser=firebaseAuth.getCurrentUser();
     ownerID=firebaseUser.getUid();
     databaseReference= FirebaseDatabase.getInstance().getReference();
     storageReference= FirebaseStorage.getInstance().getReference("foodImg");




//        Handling on click button
        binding.addImgBtn.setOnClickListener(view -> {
//            !getting image from the device
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 102);
        });

        binding.foodImg.setOnClickListener(view -> {
//            !getting image from the device
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 102);
        });



        //        Handling save button
        binding.saveBtn.setOnClickListener(view -> {
//            Log.i("TAG", "saveBtnClicked ");
            foodName = binding.foodName.getText().toString().trim();
            restureNtname = binding.resturentName.getText().toString().trim();
            foodPrice = binding.foodPrice.getText().toString().trim();
            resturentLocation = binding.fullLocation.getText().toString().trim();
            foodDes = binding.foodDes.getText().toString().trim();


            if (foodName.equals("")) {
                binding.foodName.setError("Food Name cannot be empty!");
            } else if (restureNtname.equals("")) {
                binding.foodName.setError("Resturent Name cannot be empty!");
            } else if (foodPrice.equals("")) {
                binding.foodName.setError("Resturent Name cannot be empty!");
            } else if (foodDes.equals("")) {
                binding.foodName.setError("Resturent Name cannot be empty!");
            } else if (resturentLocation.equals("")) {
                binding.foodName.setError("Resturent Name cannot be empty!");




            }else {
                saveOrPublish("save");

                binding.saveBtn.setEnabled(false);
                binding.progressbar.setVisibility(View.VISIBLE);

            }
        });

























        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && resultCode == RESULT_OK && data != null){
            uri = data.getData();

//            if the process is successful than add img button will hide
            binding.addImgBtnCon.setVisibility(View.GONE);
            binding.postImgCon.setVisibility(View.VISIBLE);
            binding.foodImg.setImageURI(uri);
        }

    }

    private void saveOrPublish(String save) {

        foodName = binding.foodName.getText().toString().trim();
        restureNtname = binding.resturentName.getText().toString().trim();
        foodPrice = binding.foodPrice.getText().toString().trim();
        resturentLocation = binding.fullLocation.getText().toString().trim();
        foodDes = binding.foodDes.getText().toString().trim();

         foodID=databaseReference.push().getKey();

         if (uri!=null){

             storageReference.child(foodID).putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                     if (task.isSuccessful()){

                         storageReference.child(foodID).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                             @Override
                             public void onSuccess(Uri uri) {

                                 foodImgUrl = String.valueOf(uri);
                                 Glide.with(getActivity()).load(foodImgUrl).placeholder(R.drawable.food1).into(binding.foodImg);
                                 savingDataToDatabase(foodID,ownerID,foodImgUrl,foodDes,foodPrice,foodName,resturentLocation,restureNtname);
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {

                             }
                         });
                     }
                 }
             });
         }



    }

    private void savingDataToDatabase(String foodID, String ownerID, String foodImgUrl, String foodDes, String foodPrice, String foodName, String resturentLocation, String restureNtname) {


        Map<String,Object>foodMap=new HashMap<>();
        foodMap.put("foodId",foodID);
        foodMap.put("foodName",foodName);
        foodMap.put("resturentName",restureNtname);
        foodMap.put("foodImg",foodImgUrl);
        foodMap.put("foodDes",foodDes);
        foodMap.put("location",resturentLocation);
        foodMap.put("foodPrice",foodPrice);
        foodMap.put("ownerId",ownerID);

        databaseReference.child("food").child(foodID).setValue(foodMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Food Added successfully❤", Toast.LENGTH_LONG).show();

                    binding.progressbar.setVisibility(View.GONE);
                    binding.saveBtn.setEnabled(true);


                    binding.foodDes.setText("");
                    binding.foodName.setText("");
                    binding.resturentName.setText("");
                    binding.foodPrice.setText("");
                    binding.fullLocation.setText("");

                }else {
                    Toast.makeText(getActivity(), ""+task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("TAG", "Failed on main data: "+ e.getLocalizedMessage());

            }
        });











    }
};











