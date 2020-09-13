package com.example.android_myshop_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android_myshop_admin.R;

public class DoanhThuActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    Button btnTopSanPham,btnTopTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);
        AnhXa();
        ActionToolbar();
        EventButton();
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        //nút home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbar=(Toolbar) findViewById(R.id.toolbarDoanhThu);
        btnTopSanPham=(Button) findViewById(R.id.btntopSP);
        btnTopTaiKhoan=(Button) findViewById(R.id.btntopTK);
    }
    private void EventButton() {
        btnTopTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoanhThuActivity.this, TopTaiKhoanActivity.class);
                startActivity(intent);
            }
        });

        btnTopSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoanhThuActivity.this, TopSanPhamActivity.class);
                startActivity(intent);
            }
        });


    }
}
