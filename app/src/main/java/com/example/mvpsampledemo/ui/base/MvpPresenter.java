package com.example.mvpsampledemo.ui.base;



public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
}
