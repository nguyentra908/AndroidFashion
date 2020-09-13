package com.example.android_myshop_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_myshop_admin.Model.Account;
import com.example.android_myshop_admin.R;
import com.example.android_myshop_admin.Ultil.checkConnect;
import com.example.android_myshop_admin.Ultil.server;

import java.util.HashMap;
import java.util.Map;

public class CapNhatTaiKhoanActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbarTs;
    EditText editTenTK,editMatKhau,editEmail,editDienThoai,editDiaChi,editQuyen;
    Button btnCapNhatTK,btnXoaTK;
    String user_name = "";
    String password = "";
    String email = "";
    String phone = "";
    String address = "";
    String quyen = "";
    int user_id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_tai_khoan);
        AnhXa();
        if (checkConnect.haveNetworkConnection(getApplicationContext())) {
            ActionToolbar();
            getInformation();
            EventButton();


        } else {
            checkConnect.ShowToast_Short(getApplicationContext(), "kiem tra ket noi");
            finish();
        }
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
    private void AnhXa() {
        toolbarTs=(Toolbar) findViewById(R.id.toolbarCapNhatTK);
        editTenTK=(EditText) findViewById(R.id.updateTenTK);
        editMatKhau=(EditText) findViewById(R.id.updateMatKhau);
        editEmail=(EditText) findViewById(R.id.updateEmail);
        editDienThoai=(EditText) findViewById(R.id.updateDienThoai);
        editDiaChi=(EditText) findViewById(R.id.updateDiaChi);
        editQuyen=(EditText) findViewById(R.id.updatequyen);
        btnCapNhatTK=(Button) findViewById(R.id.btnCapNhatTK);
        btnXoaTK=(Button) findViewById(R.id.btnXoaTK);
    }

    private void getInformation() {

        Account account= (Account) getIntent().getSerializableExtra("thongtintaikhoan");

         user_id=account.getUser_id();
        user_name=account.getUserName();
        email=account.getEmail();
        password=account.getPassword();
        phone=account.getPhone();
        address=account.getAddress();
        quyen=account.getQuyen();


        editTenTK.setText(user_name);
        editMatKhau.setText(password);
        editEmail.setText(email);
        editDienThoai.setText(phone);
        editDiaChi.setText(address);
        editQuyen.setText(quyen);


    }
    private void EventButton() {
        btnCapNhatTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_name = editTenTK.getText().toString();
                email = editEmail.getText().toString();
                password = editMatKhau.getText().toString();
                phone = editDienThoai.getText().toString();
                address = editDiaChi.getText().toString();
                quyen = editQuyen.getText().toString();


                GetData(user_id, user_name, password, email, phone,address,quyen);

            }
        });

        btnXoaTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                XoaTaiKhoan(user_id);

            }
        });


    }

    private void XoaTaiKhoan(final int user_id) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= server.XoaTaiKhoanAD;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),TrangChuActivity.class);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Đẩy và truyền dữ liệu lên server
                HashMap<String, String> data=new HashMap<String, String>();
                data.put("user_id", String.valueOf(user_id));


                return data;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void GetData(final int user_id, final String user_name, final String password, final String email, final String phone, final String address, final String quyen) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= server.CapNhatTaiKhoanAD;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),TrangChuActivity.class);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Đẩy và truyền dữ liệu lên server
                HashMap<String, String> data=new HashMap<String, String>();
                data.put("user_id", String.valueOf(user_id));
                data.put("user_name", user_name);
                data.put("password", password);
                data.put("email",email);
                data.put("phone",phone);
                data.put("address", address);
                data.put("quyen", quyen);

                return data;
            }
        };
        requestQueue.add(stringRequest);
    }

}
