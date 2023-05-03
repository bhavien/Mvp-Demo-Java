package com.example.mvpsampledemo.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import com.example.mvpsampledemo.MyApp;
import com.example.mvpsampledemo.R;
import com.example.mvpsampledemo.data.DataManager;
import com.example.mvpsampledemo.ui.dashboard.DashboardActivity;
import com.example.mvpsampledemo.ui.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity implements SplashMvpView {

    SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DataManager dataManager = ((MyApp) getApplication()).getDataManager();
        mSplashPresenter = new SplashPresenter(dataManager);
        mSplashPresenter.onAttach(this);

        mSplashPresenter.goNextActivity();
    }

    @Override
    public void openDashboard() {
        Intent intent = DashboardActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }
    @Override
    public void openLogin() {
        Intent intent = LoginActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

}