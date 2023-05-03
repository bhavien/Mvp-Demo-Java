package com.example.mvpsampledemo.ui.login;

import com.example.mvpsampledemo.data.DataManager;
import com.example.mvpsampledemo.ui.base.BasePresenter;
import com.example.mvpsampledemo.ui.base.MvpView;

public class LoginPresenter<V extends LoginMvpView & MvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }
    @Override
    public void startLogin(String emailId) {
        getDataManager().saveEmailId(emailId);
        getDataManager().setLoggedIn();
        getMvpView().openDashBoardActivity();
    }
}
