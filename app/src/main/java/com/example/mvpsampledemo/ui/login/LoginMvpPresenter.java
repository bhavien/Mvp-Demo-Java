package com.example.mvpsampledemo.ui.login;


import com.example.mvpsampledemo.ui.base.MvpPresenter;
import com.example.mvpsampledemo.ui.base.MvpView;


/**
 * Created by gaura on 22-08-2017.
 */

public interface LoginMvpPresenter<V extends LoginMvpView & MvpView> extends MvpPresenter<V> {

    void startLogin(String emailId);

}
