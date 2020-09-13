package com.example.android_myshop_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_myshop_admin.R;
import com.example.android_myshop_admin.Ultil.checkConnect;
import com.example.android_myshop_admin.Ultil.server;

import java.util.HashMap;
import java.util.Map;

public class ThemTaiKhoanActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbarTs;
    Spinner  spinner;
    EditText editTenTK,editMatKhau,editEmail,editDienThoai,editDiaChi;
    Button btnThemTK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tai_khoan);
        Anhxa();
        if (checkConnect.haveNetworkConnection(getApplicationContext())) {
            ActionToolbar();
            catchEventSpinner();
            EventButton();
        } else {
            checkConnect.ShowToast_Short(getApplicationContext(), "kiem tra ket noi");
            finish();
        }
    }
    private void catchEventSpinner() {
        String[] quyen=new String[]{"khachhang","admin"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,quyen);
        spinner.setAdapter(arrayAdapter);
    }
    private void Anhxa() {

        spinner=(Spinner)findViewById(R.id.quyen);
        toolbarTs=(Toolbar) findViewById(R.id.toolbarThemTK);
        editTenTK=(EditText) findViewById(R.id.editTenTK);
        editMatKhau=(EditText) findViewById(R.id.editMatKhau);
        editEmail=(EditText) findViewById(R.id.editEmail);
        editDienThoai=(EditText) findViewById(R.id.editDienThoai);
        editDiaChi=(EditText) findViewById(R.id.editDiaChi);
        btnThemTK=(Button) findViewById(R.id.btnThemTK);

    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarTs);
        //nút home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTs.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void EventButton() {


        btnThemTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_name = editTenTK.getText().toString().trim();
                final String password = editMatKhau.getText().toString().trim();
                final String email = editEmail.getText().toString().trim();
                final String phone = editDienThoai.getText().toString().trim();
                final String address = editDiaChi.getText().toString().trim();
                final String quyen = spinner.getSelectedItem().toString().trim();

                RequestQueue requestQueue= Volley.newRequestQueue((getApplicationContext()));
                StringRequest taikhoanRequest = new StringRequest(Request.Method.POST, server.ThemTaiKhoanAD,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                  Toast.makeText(ThemTaiKhoanActivity.this, response, Toast.LENGTH_LONG).show();
                                checkConnect.ShowToast_Short(getApplicationContext(),"Succes ");
                                Intent intent=new Intent(getApplicationContext(),TaiKhoanActivity.class);
                                startActivity(intent);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                VolleyLog.d( "Error: " + error.getMessage());
                            }
                        }) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("user_name", user_name);
                        params.put("password", password);
                        params.put("email", email);
                        params.put("phone", phone);
                        params.put("address", address);
                        params.put("quyen", quyen);
                        return params;
                    }

                };

                requestQueue.add(taikhoanRequest);
            }

        });

    }

}
