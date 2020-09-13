package com.example.android_myshop_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_myshop_admin.Adapter.TopSPAdapter;
import com.example.android_myshop_admin.Model.TopSanPham;
import com.example.android_myshop_admin.R;
import com.example.android_myshop_admin.Ultil.checkConnect;
import com.example.android_myshop_admin.Ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TopSanPhamActivity extends AppCompatActivity {
    ListView listView;
    Toolbar toolbar;
    TopSPAdapter topSPAdapter;
    ArrayList<TopSanPham> mangSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_san_pham);
        AnhXa();
        if (checkConnect.haveNetworkConnection(getApplicationContext())) {

            ActionToolbar();
            GetData();

        } else {
            checkConnect.ShowToast_Short(getApplicationContext(), "kiem tra ket noi");
            finish();
        }

    }
    private void AnhXa() {
        toolbar=(Toolbar) findViewById(R.id.toolbarTopSP);
        listView=(ListView) findViewById(R.id.listviewTopSP);
        //truyền dữ liệu vào mảng
        mangSanPham=new ArrayList<>();
        topSPAdapter=new TopSPAdapter(getApplicationContext(),mangSanPham);
        listView.setAdapter(topSPAdapter);
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
    private void GetData() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= server.TopSP;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Integer id=0;
                String name="";
                int soluongsp=0;



                try {
                    JSONArray jsonArray =new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        id=jsonObject.getInt("id");
                        name = jsonObject.getString("name");

                        soluongsp = jsonObject.getInt("soluongsp");

                        mangSanPham.add((new TopSanPham(id,name,soluongsp)));

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
}
