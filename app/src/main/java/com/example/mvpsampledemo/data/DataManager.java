package com.example.mvpsampledemo.data;

public class DataManager {
    SharedPref mSharedPref;
    public DataManager(SharedPref sharedPrefsHelper) {
        mSharedPref = sharedPrefsHelper;
    }
    public void clear() {
        mSharedPref.clear();
    }
    public void saveEmailId(String email) {
        mSharedPref.putEmail(email);
    }
    public String getEmailId() {
        return mSharedPref.getEmail();
    }
    public void setLoggedIn() {
        mSharedPref.setLoggedInMode(true);
    }
    public Boolean getLoggedInMode() {
        return mSharedPref.getLoggedInMode();
    }
}
