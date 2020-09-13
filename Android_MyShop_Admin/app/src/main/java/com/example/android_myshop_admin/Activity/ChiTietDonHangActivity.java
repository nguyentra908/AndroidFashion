package com.example.android_myshop_admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_myshop_admin.Adapter.ChiTietdonHangAdapter;
import com.example.android_myshop_admin.Adapter.DonHangAdapter;
import com.example.android_myshop_admin.Model.ChiTietDonHang;
import com.example.android_myshop_admin.Model.DonHang;
import com.example.android_myshop_admin.R;
import com.example.android_myshop_admin.Ultil.checkConnect;
import com.example.android_myshop_admin.Ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChiTietDonHangActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ListView listView;
    ChiTietdonHangAdapter chiTietdonHangAdapter;
    ArrayList<ChiTietDonHang> mangChiTietDonHang;
    private SearchView searchView;
    int idDonHang=0;
    int page=1;
    View footerview;
    boolean isLoading=false;
    boolean limitData=false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
        AnhXa();
        if (checkConnect.haveNetworkConnection(getApplicationContext())) {
            GetIdDonHang();
            ActionToolbar();
            getdulieuDonHang(page);
//LoadMoreData();

        } else {
            checkConnect.ShowToast_Short(getApplicationContext(), "kiem tra ket noi");
            finish();
        }
    }

    private void AnhXa() {
        toolbar=(Toolbar) findViewById(R.id.toolbarCTDH);
        listView=(ListView) findViewById(R.id.listviewCTDH);
        //truyền dữ liệu vào mảng
        mangChiTietDonHang=new ArrayList<>();
        chiTietdonHangAdapter=new ChiTietdonHangAdapter(getApplicationContext(),mangChiTietDonHang);
        listView.setAdapter(chiTietdonHangAdapter);
    }
    private void GetIdDonHang() {
        idDonHang=getIntent().getIntExtra("thongtindonhang",-1);
        Log.d("",idDonHang+"");
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

    private void getdulieuDonHang(int Page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= server.getChiTietDonHangAD+String.valueOf(Page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                int id_bill=0;
                int id_product=0;
                int quantity=0;
                double  price=0;
                String name="";

                if(response!=null && response.length()!=2){
                    listView.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray =new JSONArray(response);
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            id_bill = jsonObject.getInt("id_bill");
                            id_product =  jsonObject.getInt("id_product");
                            quantity = jsonObject.getInt("quantity");
                            price = jsonObject.getDouble("price");
                            name = jsonObject.getString("name");

                            mangChiTietDonHang.add((new ChiTietDonHang(id,id_bill,id_product,quantity,price,name)));
                            chiTietdonHangAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    limitData=true;
                    listView.removeFooterView(footerview);
                    checkConnect.ShowToast_Short(getApplicationContext(),"No data");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Đẩy và truyền dữ liệu lên server
                HashMap<String, String> map=new HashMap<String, String>();
                map.put("id_bill",String.valueOf(idDonHang));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    listView.addFooterView(footerview);
                    break;
                case 1:
                    getdulieuDonHang(++page);
                    isLoading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

}
