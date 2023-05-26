package com.abedkhan.greatfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.abedkhan.greatfood.Fragmrnt.DetailsFragment;
import com.abedkhan.greatfood.Fragmrnt.HomeFragment;
import com.abedkhan.greatfood.Fragmrnt.LogeinFragment;
import com.abedkhan.greatfood.Fragmrnt.RegistrationFragment;
import com.abedkhan.greatfood.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent=getIntent();

      //  getSupportFragmentManager().beginTransaction().replace(R.id.frame,new LogeinFragment()).commit();

        if (intent.getBooleanExtra("registration",false))
        {
            replace(new RegistrationFragment());
        }else if (intent.getBooleanExtra("logein",false)){
             replace(new LogeinFragment());
        }else if (intent.getBooleanExtra("home",false)){
            replace(new HomeFragment());
        }else if (intent.getBooleanExtra("details",false)){
            replace(new DetailsFragment());
        }
        else {
            //startActivity(new Intent(this,drawer.class));
            replace(new LogeinFragment());
        }





















    }
    private void replace(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
    }
}