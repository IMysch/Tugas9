package com.example.tugas9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1,imageView2;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1=findViewById(R.id.image_view1);
        imageView2=findViewById(R.id.image_view2);

        Glide.with(MainActivity.this)
                .load("https://images.app.goo.gl/gtrzJNzFoZFYLvRLA")
                .placeholder(R.drawable.progress_bar)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView1);

        Glide.with(MainActivity.this)
                .load("https://images.app.goo.gl/izwaAu7pj6EK3Rpi7")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView2);


        bottomNavigationView=findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener (bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name)
                .setMessage("Apakah Kamu yakin ?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        finish();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();

                    }
                })
                .show();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod=new
            BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override

        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

            Fragment fragment=null;
            switch (menuItem.getItemId())
            {
                case R.id.android :
                    fragment=new AndroidFragment();
                    break;

                case R.id.home :
                    fragment=new HomeFragment();
                    break;

               case R.id.search :
                    fragment=new SearchFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            return true;
        }
            };
}