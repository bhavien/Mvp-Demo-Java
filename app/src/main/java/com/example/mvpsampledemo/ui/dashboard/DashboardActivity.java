package com.example.mvpsampledemo.ui.dashboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpsampledemo.MyApp;
import com.example.mvpsampledemo.R;
import com.example.mvpsampledemo.adapter.ProductAdapter;
import com.example.mvpsampledemo.data.DataManager;
import com.example.mvpsampledemo.roomdatabase.database.AppDataBase;
import com.example.mvpsampledemo.roomdatabase.entity.Product;
import com.example.mvpsampledemo.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


public class DashboardActivity extends AppCompatActivity implements DashboardMvpView, ProductAdapter.DeleteClickListener {

    AppCompatButton btnAddProduct, btnLogOut, dialogAddButton, dialogCancelButton;
    RecyclerView rvProduct;
    ProductAdapter productAdapter;
    DashboardPresenter dashboardPresenter;
    AppCompatTextView tvNoProduct;
    ArrayList<Product> productList = new ArrayList<>();
    TextInputEditText etProduct;
    String productName;
    AlertDialog.Builder productRemoveAlert;
    Dialog addProductDialog;
    DataManager dataManager;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, DashboardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.dashboard);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnLogOut = findViewById(R.id.btnLogOut);
        rvProduct = findViewById(R.id.rvProduct);
        tvNoProduct = findViewById(R.id.tvNoProduct);

        manageProductList();

        dataManager = ((MyApp) getApplication()).getDataManager();
        dashboardPresenter = new DashboardPresenter(dataManager);
        dashboardPresenter.onAttach(this);

        btnAddProduct.setOnClickListener(view -> dashboardPresenter.openDialog());
        btnLogOut.setOnClickListener(view -> dashboardPresenter.setUserLogout());

    }

    private void manageProductList() {
        AppDataBase dataBase = AppDataBase.getAppDatabase(this);
        productList = new ArrayList(dataBase.productDao().loadAllProductList());
        Collections.reverse(productList);
        if (productList.isEmpty()) {
            tvNoProduct.setVisibility(View.VISIBLE);
            rvProduct.setVisibility(View.GONE);
        } else {
            tvNoProduct.setVisibility(View.GONE);
            rvProduct.setVisibility(View.VISIBLE);
            dashboardPresenter.setAdapter();
        }
    }

    @Override
    public void openSplashActivity() {
        Intent intent = LoginActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openAddProductDialog() {
        addProductDialog = new Dialog(this);
        addProductDialog.setContentView(R.layout.add_product_dialog);
        addProductDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addProductDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addProductDialog.setCancelable(false);
        dialogAddButton = addProductDialog.findViewById(R.id.btnAdd);
        dialogCancelButton = addProductDialog.findViewById(R.id.btnCancel);
        etProduct = addProductDialog.findViewById(R.id.etProduct);
        etProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(""))
                    dialogAddButton.setVisibility(View.VISIBLE);
                else
                    dialogAddButton.setVisibility(View.GONE);
            }
        });
        dialogAddButton.setOnClickListener(view -> dashboardPresenter.addButtonClick());
        dialogCancelButton.setOnClickListener(view -> dashboardPresenter.cancelButtonClick());
        addProductDialog.show();
    }

    @Override
    public void addButtonClick() {
        productName = Objects.requireNonNull(etProduct.getText()).toString();
        AppDataBase dataBase = AppDataBase.getAppDatabase(this);
        Product product = new Product(productName);
        dataBase.productDao().insert(product);
        manageProductList();
        addProductDialog.dismiss();
    }

    @Override
    public void cancelButtonClick() {
        addProductDialog.dismiss();
    }

    @Override
    public void setProductAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProduct.setLayoutManager(layoutManager);
        rvProduct.setHasFixedSize(false);
        productAdapter = new ProductAdapter(productList, this, this);
        rvProduct.setAdapter(productAdapter);
    }

    @Override
    public void deleteProductFromDB(int position) {
        Log.e("TAG", "deleteProductFromDB: " + position);
        AppDataBase dataBase = AppDataBase.getAppDatabase(this);
        dataBase.productDao().deleteById(productList.get(position).id);
        productList.remove(position);
        productAdapter.notifyDataSetChanged();
        if (productList.isEmpty()) {
            tvNoProduct.setVisibility(View.VISIBLE);
            rvProduct.setVisibility(View.GONE);
        }
    }


    @Override
    public void onDeleteButton(int position) {
        productRemoveAlert = new AlertDialog.Builder(this);
        productRemoveAlert.setMessage(R.string.delete_message)
                .setTitle(R.string.remove_product)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    dashboardPresenter.deleteProductFromDB(position);
                })
                .setNegativeButton(R.string.no, (dialog, id) -> dialog.cancel());
        AlertDialog alert = productRemoveAlert.create();
        alert.show();
    }


}