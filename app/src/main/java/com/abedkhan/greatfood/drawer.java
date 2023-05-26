package com.abedkhan.greatfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.abedkhan.greatfood.Fragmrnt.AddFragment;
import com.abedkhan.greatfood.Fragmrnt.DevoloperFragment;
import com.abedkhan.greatfood.Fragmrnt.FavouriteFragment;
import com.abedkhan.greatfood.Fragmrnt.HomeFragment;
import com.abedkhan.greatfood.Fragmrnt.LogeinFragment;
import com.abedkhan.greatfood.Fragmrnt.ProfileFragment;
import com.abedkhan.greatfood.Model.UserModel;
import com.abedkhan.greatfood.databinding.ActivityDrawerBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
ActivityDrawerBinding binding;
    DrawerLayout drawerLayout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String currentUserID,userName,userMail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


         replace(new HomeFragment());


         setdatatonav();

        Toolbar toolbar =findViewById(R.id.tool);
        setSupportActionBar(toolbar);


         drawerLayout=findViewById(R.id.drawer);
        //set tool bar/ action bar................................................
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close
        );drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView1=findViewById(R.id.navvie);
        navigationView1.setNavigationItemSelectedListener(this);
















    }

    private void setdatatonav() {






        firebaseAuth = FirebaseAuth.getInstance();
//        currentUserID=firebaseUser.getUid();

        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseUser=firebaseAuth.getCurrentUser();

        NavigationView navigationView=findViewById(R.id.navvie);
        View view=navigationView.getHeaderView(0);
        TextView name=view.findViewById(R.id.user_name);
        TextView mail=view.findViewById(R.id.userMail);

        databaseReference.child("User").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel=snapshot.getValue(UserModel.class);


                    if(userModel!=null){

                        try {
                        name.setText(userModel.getUserName());
                        mail.setText(userModel.getUserMail());
                        }catch (Exception e){

                        }
                    }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemid=item.getItemId();

        if(itemid==R.id.hommenue){
            replace(new HomeFragment());
        }else if (itemid==R.id.additem){
           replace(new AddFragment());
        }else if (itemid==R.id.profile){
           replace(new ProfileFragment());
        }else if (itemid==R.id.favourite){
           replace(new FavouriteFragment());
        }else if (itemid==R.id.devoloperinfo){
       replace(new DevoloperFragment());

        }else {
          //    replace(new HomeFragment());
              startActivity(new Intent(this,drawer.class));
        }
      drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void replace(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.homeframe, fragment).commit();
    }


    //topcorner menu........
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.topcornermenu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        firebaseAuth=FirebaseAuth.getInstance();
        int itemid=item.getItemId();

        if(itemid==R.id.loge_outmenu){
            firebaseAuth.signOut();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("logein", true);
            startActivity(intent);
            finish();
        }else {
            replace(new HomeFragment());
        }
        return super.onOptionsItemSelected(item);
    }
}