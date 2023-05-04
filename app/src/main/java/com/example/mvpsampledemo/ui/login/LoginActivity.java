package com.example.mvpsampledemo.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.mvpsampledemo.MyApp;
import com.example.mvpsampledemo.R;
import com.example.mvpsampledemo.data.DataManager;
import com.example.mvpsampledemo.ui.dashboard.DashboardActivity;
import com.example.mvpsampledemo.util.Utils;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity implements LoginMvpView {

    LoginPresenter loginPresenter;
    TextInputEditText etEmail, etPassword;
    AppCompatButton btnLogIn;
    String emailId;
    String password;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataManager dataManager = ((MyApp) getApplication()).getDataManager();
        loginPresenter = new LoginPresenter(dataManager);

        loginPresenter.onAttach(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener(view -> {
            if (isValid()) {
                onLoginButtonClick();
            }
        });

    }

    private boolean isValid() {
        emailId = Objects.requireNonNull(etEmail.getText()).toString();
        password = Objects.requireNonNull(etPassword.getText()).toString();
        if (Objects.equals(emailId, "")) {
            Toast.makeText(this, getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Utils.isEmailValid(emailId)) {
            Toast.makeText(this, getString(R.string.please_enter_valid_email), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 6) {
            Toast.makeText(this, getString(R.string.password_error_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    public void openDashBoardActivity() {
    }

    @Override
    public void onLoginButtonClick() {
        Intent intent = DashboardActivity.getStartIntent(this);
        startActivity(intent);
        finish();
        loginPresenter.startLogin(emailId);
    }
}