package com.example.nasa.src.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nasa.R;
import com.example.nasa.src.BaseActivity;
import com.example.nasa.src.main.interfaces.MainActivityView;
import com.example.nasa.src.main.models.APOD;
import com.example.nasa.src.main.models.TechTransfer;
import com.example.nasa.src.splash.SplashActivity;
import com.example.nasa.src.splash.SplashService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainActivityView {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;



    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);// 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요

        intent = getIntent();

        //Apod 인텐트 값 받아오기
        String apod_title = intent.getStringExtra("apod_title");
        String apod_date = intent.getStringExtra("apod_date");
        String apod_explanation= intent.getStringExtra("apod_explanation");
        String apod_image = intent.getStringExtra("apod_image");

        //EPIC 인텐트 값 받아오기
        ArrayList<String> epic_caption = (ArrayList<String>) intent.getSerializableExtra("epic_caption");
        ArrayList<String> epic_image = (ArrayList<String>) intent.getSerializableExtra("epic_image");
        ArrayList<String> epic_date = (ArrayList<String>) intent.getSerializableExtra("epic_date");
        ArrayList<String> epic_lat = (ArrayList<String>) intent.getSerializableExtra("epic_lat");
        ArrayList<String> epic_lon = (ArrayList<String>) intent.getSerializableExtra("epic_lon");

        //Earht Asset 인텐트 값 받아오기
        String asset_date = intent.getStringExtra("asset_date");
        String asset_id = intent.getStringExtra("asset_id");
        String asset_image = intent.getStringExtra("asset_image");

        final Fragment frag1 = new MyFrag();
        final Fragment frag2 = new ApodFrag();
        final Fragment frag3 = new EarthFrag();
        final Fragment frag4 = new MoonFrag();
        final Fragment frag5 = new TechFrag();
        final FragmentManager fm = getSupportFragmentManager();
        final Fragment[] active = {frag1};



        fm.beginTransaction().add(R.id.main_frame, frag5, "5").hide(frag5).commit();
        fm.beginTransaction().add(R.id.main_frame, frag4, "4").hide(frag4).commit();
        fm.beginTransaction().add(R.id.main_frame, frag3, "3").hide(frag3).commit();
        fm.beginTransaction().add(R.id.main_frame, frag2, "2").hide(frag2).commit();
        fm.beginTransaction().add(R.id.main_frame, frag1, "1").commit(); //첫 프래그먼트 화면만 hide 안 함.



        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        fm.beginTransaction().hide(active[0]).show(frag1).commit();
                        active[0] = frag1;
                        return true;
                    case R.id.menu2:
                        fm.beginTransaction().hide(active[0]).show(frag2).commit();
                        active[0] = frag2;
                        return true;
                    case R.id.menu3:
                        fm.beginTransaction().hide(active[0]).show(frag3).commit();
                        active[0] = frag3;
                        return true;
                    case R.id.menu4:
                        fm.beginTransaction().hide(active[0]).show(frag4).commit();
                        active[0] = frag4;
                        return true;
                    case R.id.menu5:
                        fm.beginTransaction().hide(active[0]).show(frag5).commit();
                        active[0] = frag5;
                        return true;
                }
                return false;
            }
        });



        //Apod 인텐트값 넣어서 번들값 넘겨주기
        Bundle bundle_apod = new Bundle();
        bundle_apod.putString("apod_title",apod_title);
        bundle_apod.putString("apod_date",apod_date);
        bundle_apod.putString("apod_explanation"," "+apod_explanation);
        bundle_apod.putString("apod_image",apod_image);
        frag2.setArguments(bundle_apod);

        //Earth Asset, EPIC 인텐트값 넣어서 번들값 넘겨주기
        Bundle bundle_earth = new Bundle();
        bundle_earth.putParcelableArrayList("epic_caption", (ArrayList) epic_caption);
        bundle_earth.putParcelableArrayList("epic_image", (ArrayList) epic_image);
        bundle_earth.putParcelableArrayList("epic_date", (ArrayList) epic_date);
        bundle_earth.putParcelableArrayList("epic_lat", (ArrayList) epic_lat);
        bundle_earth.putParcelableArrayList("epic_lon", (ArrayList) epic_lon);

        bundle_earth.putString("asset_date",asset_date);
        bundle_earth.putString("asset_id",asset_id);
        bundle_earth.putString("asset_image",asset_image);
        frag3.setArguments(bundle_earth);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_option_menu, menu) ;

        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alarm:
                Log.d("toolbar", "alarm");
                return true;
            case R.id.home:
                Log.d("toolbar", "home");
                return true;
        }
        return super.onOptionsItemSelected(item) ;
    }

    private void tryGetTest() {
        showProgressDialog();
        final MainService mainService = new MainService(this);
        mainService.getApod();
    }

    @Override
    public void apodGetSuccess(APOD apod) {
        hideProgressDialog();

//        String title = intent.getStringExtra("title");
//        String date = intent.getStringExtra("date");
//        String explanation= intent.getStringExtra("explanation");
//        String image = intent.getStringExtra("image");
//
//
//        Bundle bundle = new Bundle();
//        bundle.putString("title",title);
//        bundle.putString("date",apod.getDate());
//        bundle.putString("explanation"," "+apod.getExplanation());
//        bundle.putString("image",apod.getUrl());
//        frag2.setArguments(bundle);
    }

    @Override
    public void apodGetFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void techTransferGetSuccess(TechTransfer techTransfer) {

    }

    @Override
    public void techTransferGetFailure(String message) {

    }

//    public void customOnClick(View view) {
//        switch (view.getId()) {
//            case R.id.main_btn_hello_world:
//                tryGetTest();
//                break;
//            default:
//                break;
//        }
//    }

//    //프래그먼트 교체가 일어나는 실행문
//    private void setFrag(int n) {
//        fm = getSupportFragmentManager();
//        ft = fm.beginTransaction();
//        switch (n) {
//            case 0:
//                ft.replace(R.id.main_frame, frag1);
//                ft.commit();
//                break;
//            case 1:
//                ft.replace(R.id.main_frame, frag2);
//                ft.commit();
//                break;
//            case 2:
//                ft.replace(R.id.main_frame, frag3);
//                ft.commit();
//                break;
//            case 3:
//                ft.replace(R.id.main_frame, frag4);
//                ft.commit();
//                break;
//            case 4:
//                ft.replace(R.id.main_frame, frag5);
//                ft.commit();
//                break;
//        }
//    }
}