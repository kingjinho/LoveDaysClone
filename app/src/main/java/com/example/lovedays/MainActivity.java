package com.example.lovedays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.lovedays.Screens.InitialSetupScreen;
import com.example.lovedays.Screens.MainScreenCenterTab;
import com.example.lovedays.Utils.Const;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (isNewUser()) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new InitialSetupScreen(), InitialSetupScreen.TAG)
                    .commit();
        } else {
            //TODO: VIEWPAGER
            setViewPager();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean isNewUser(){
        SharedPreferences sf = getSharedPreferences(Const.FILE, MODE_PRIVATE);
        if(sf.getString(Const.USER, "").equals(""))
            return true;
        else
            return false;
    }


    private void setViewPager(){

    }
}
