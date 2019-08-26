package com.example.lovedays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.lovedays.Screens.InitialSetupScreen;
import com.example.lovedays.Screens.MainScreenCenterTab;
import com.example.lovedays.Screens.MainScreenViewPagerGroup;
import com.example.lovedays.Utils.Const;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (!isNewUser())
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new InitialSetupScreen(), InitialSetupScreen.TAG)
                    .commit();
        else
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new MainScreenViewPagerGroup(), MainScreenViewPagerGroup.TAG)
                    .addToBackStack(MainScreenViewPagerGroup.TAG)
                    .commit();
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
        if (getSupportFragmentManager().getFragments().size() > 1) {
            getSupportFragmentManager().popBackStack();
        }
    }

    private boolean isNewUser() {
        return getSharedPreferences(Const.USER, MODE_PRIVATE)
                .getBoolean(Const.IS_REGISTERED, false);
    }
}
