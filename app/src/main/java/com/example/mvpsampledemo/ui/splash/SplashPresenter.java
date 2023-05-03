package com.example.mvpsampledemo.ui.splash;


import com.example.mvpsampledemo.data.DataManager;
import com.example.mvpsampledemo.ui.base.BasePresenter;

public class SplashPresenter <V extends SplashMvpView> extends BasePresenter<V> {

    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void goNextActivity() {
        if (getDataManager().getLoggedInMode()) {
            getMvpView().openDashboard();
        } else {
            getMvpView().openLogin();
        }
    }
}