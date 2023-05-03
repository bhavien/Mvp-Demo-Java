package com.example.mvpsampledemo.ui.dashboard;

import com.example.mvpsampledemo.ui.base.MvpView;

public interface DashboardMvpView extends MvpView {
    void openSplashActivity();
    void openAddProductDialog();
    void addButtonClick();
    void cancelButtonClick();
    void setProductAdapter();



}
