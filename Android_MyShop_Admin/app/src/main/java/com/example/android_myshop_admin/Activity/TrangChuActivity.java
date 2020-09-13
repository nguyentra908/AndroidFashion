package com.example.android_myshop_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android_myshop_admin.R;

public class TrangChuActivity extends AppCompatActivity {
    Button btnDonHang,btnSanPham,btnDoanhThu,btnTaiKhoan;
    Toolbar toolbar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        AnhXa();
        ActionToolbar();
        EventButton();
    }

    private void EventButton() {
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChuActivity.this, DoanhThuActivity.class);
                startActivity(intent);
            }
        });

        btnDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChuActivity.this, DonHangActivity.class);
                startActivity(intent);
            }
        });

        btnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChuActivity.this, TaiKhoanActivity.class);
                startActivity(intent);
            }
        });
        btnSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChuActivity.this, SanPhamActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar1);
        //nút home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();;
            }
        });
    }
    private void AnhXa() {
        toolbar1=(Toolbar) findViewById(R.id.toolbar1);
        btnDonHang=(Button) findViewById(R.id.btndonhang);
        btnSanPham=(Button) findViewById(R.id.btnsanpham);
        btnDoanhThu=(Button) findViewById(R.id.btndoanhthu);
        btnTaiKhoan=(Button) findViewById(R.id.btntaikhoan);
    }
}
