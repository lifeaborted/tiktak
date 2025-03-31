package com.example.dedradura;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private VideoFragment videoFragment;
    private ViewPager2 viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);

        ViewPager2 viewPager = findViewById(R.id.viewPager); // Получаем ссылку на ViewPager2





        getSupportActionBar().setTitle("ПРОДАМ ГАРАЖ 8-(982)-371-13-45");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.serebro)));

        if (savedInstanceState == null) {
            videoFragment = new VideoFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.videoContainer, videoFragment)
                    .commit();
        } else {
            videoFragment = (VideoFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.videoContainer);
        }
    }


    public void vid(View view) {
        replaceFragment(new VideoFragment()); // Открываем видео-ленту
    }

    public void add(View view) {

    }
    public void pic(View view){
        replaceFragment(new PhotoFragment()); // Открываем фото-ленту
        }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.videoContainer, fragment) // Заменяем текущий фрагмент
                .commit();
    }


}
