package com.abedkhan.greatfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.abedkhan.greatfood.Fragmrnt.HomeFragment;
import com.abedkhan.greatfood.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashScreenBinding binding;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        new Handler().postDelayed((Runnable) () -> {

//            Intent intent;
//
//            SharedPreferences preferences = getSharedPreferences("WElcomescreen",MODE_PRIVATE);
//            boolean isClicked = preferences.getBoolean("Uclick", false);
//
//            if (isClicked){
//                intent = new Intent(this, MainActivity.class);
//            }else {
//                intent = new Intent(this, MainActivity.class);}
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();

        }, 1000);




    }
}