package com.example.mvpsampledemo.ui.dashboard;

import com.example.mvpsampledemo.data.DataManager;
import com.example.mvpsampledemo.ui.base.BasePresenter;

public class DashboardPresenter<V extends DashboardMvpView> extends BasePresenter<V>  {

    Integer position;

    public DashboardPresenter(DataManager dataManager) {
        super(dataManager);
    }
    public void openDialog() {
        getMvpView().openAddProductDialog();
    }
    public void addButtonClick() {
        getMvpView().addButtonClick();
    }
    public void cancelButtonClick() {
        getMvpView().cancelButtonClick();
    }

    public void setAdapter() {
        getMvpView().setProductAdapter();
    }
    public void setUserLogout() {
        getDataManager().clear();
        getMvpView().openSplashActivity();
    }

    public void deleteProductFromDB(int position) {
        getMvpView().deleteProductFromDB(position);
    }

}

