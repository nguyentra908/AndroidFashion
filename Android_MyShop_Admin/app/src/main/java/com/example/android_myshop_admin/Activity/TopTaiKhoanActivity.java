package com.example.android_myshop_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_myshop_admin.Adapter.TopTKAdapter;

import com.example.android_myshop_admin.Model.TopTaiKhoan;
import com.example.android_myshop_admin.R;
import com.example.android_myshop_admin.Ultil.checkConnect;
import com.example.android_myshop_admin.Ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TopTaiKhoanActivity extends AppCompatActivity {
    ListView listView;
    Toolbar toolbar;
    TopTKAdapter topTKAdapter;
    ArrayList<TopTaiKhoan> mangTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_tai_khoan);
        AnhXa();
        if (checkConnect.haveNetworkConnection(getApplicationContext())) {

            ActionToolbar();
            GetData();

        } else {
            checkConnect.ShowToast_Short(getApplicationContext(), "kiem tra ket noi");
            finish();
        }
    }
    private void GetData() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= server.TopTK;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Integer user_id=0;
                String user_name="";
                String date_order="";
                int totalprice=0;
                int soluongdonhang=0;



                    try {
                        JSONArray jsonArray =new JSONArray(response);
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            user_id=jsonObject.getInt("user_id");
                            user_name = jsonObject.getString("user_name");

                            totalprice = jsonObject.getInt("totalprice");
                            soluongdonhang = jsonObject.getInt("soluongdonhang");

                            mangTaiKhoan.add((new TopTaiKhoan(user_id,user_name,totalprice,soluongdonhang)));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }
    private void AnhXa() {
        toolbar=(Toolbar) findViewById(R.id.toolbarTopTK);
        listView=(ListView) findViewById(R.id.listviewTopTK);
        //truyền dữ liệu vào mảng
        mangTaiKhoan=new ArrayList<>();
        topTKAdapter=new TopTKAdapter(getApplicationContext(),mangTaiKhoan);
        listView.setAdapter(topTKAdapter);
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
}
