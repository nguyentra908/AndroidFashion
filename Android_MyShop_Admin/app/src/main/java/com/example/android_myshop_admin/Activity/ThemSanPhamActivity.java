package com.example.android_myshop_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_myshop_admin.Model.SanPham;
import com.example.android_myshop_admin.R;
import com.example.android_myshop_admin.Ultil.checkConnect;
import com.example.android_myshop_admin.Ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ThemSanPhamActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbarTs;
    EditText editTenSP,editMaLoaiSP,editGiaSP,editMauSP,editChatLieuSP,editMoTaSP,editAnhSP;
    Button btnThemSP;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        AnhXa();
        if (checkConnect.haveNetworkConnection(getApplicationContext())) {
            ActionToolbar();
            EventButton();

        } else {
            checkConnect.ShowToast_Short(getApplicationContext(), "kiem tra ket noi");
            finish();
        }
    }

    private void AnhXa() {
        toolbarTs=(Toolbar) findViewById(R.id.toolbarThem);
        editTenSP=(EditText) findViewById(R.id.editTenSP);
        editMaLoaiSP=(EditText) findViewById(R.id.editMaLoaiSP);
        editGiaSP=(EditText) findViewById(R.id.editGiaSP);
        editMauSP=(EditText) findViewById(R.id.editMauSP);
        editChatLieuSP=(EditText) findViewById(R.id.editChatLieuSP);
        editMoTaSP=(EditText) findViewById(R.id.editMoTaSP);
        editAnhSP=(EditText) findViewById(R.id.editAnhSP);
        btnThemSP=(Button) findViewById(R.id.btnThemSP);

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


    btnThemSP.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String name = editTenSP.getText().toString().trim();
            final String id_type = editMaLoaiSP.getText().toString().trim();
            final String price = editGiaSP.getText().toString().trim();
            final String color = editMauSP.getText().toString().trim();
            final String material = editChatLieuSP.getText().toString().trim();
            final String description = editMoTaSP.getText().toString().trim();
            final String link = editAnhSP.getText().toString().trim();
            RequestQueue requestQueue=Volley.newRequestQueue((getApplicationContext()));
                StringRequest sanphamRequest = new StringRequest(Request.Method.POST, server.ThemSanPhamAD,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String response) {

                                //them ảnh vào sản phẩm
                                RequestQueue requestQueue=Volley.newRequestQueue((getApplicationContext()));

                                StringRequest anhRequest=new StringRequest(Request.Method.POST, server.ThemAnhSanPhamAD,
                                        new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
//                                        if(response.equals("1")){
                                          //  Toast.makeText(ThemSanPhamActivity.this, response, Toast.LENGTH_LONG).show();
                                            checkConnect.ShowToast_Short(getApplicationContext(),"Succes ");
                                            Intent intent=new Intent(getApplicationContext(),SanPhamActivity.class);
                                            startActivity(intent);


//                                        }else {
//                                            checkConnect.ShowToast_Short(getApplicationContext(),"Error");
//                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        VolleyLog.d( "Error: " + error.getMessage());
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<>();
                                        params.put("link", link);
                                        params.put("id_product", response);

                                        return params;
                                    }
                                };
                                requestQueue.add(anhRequest);
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
                        params.put("name", name);
                        params.put("id_type", id_type);
                        params.put("price", price);
                        params.put("color", color);
                        params.put("material", material);
                        params.put("description", description);
                        return params;
                    }

                };

                requestQueue.add(sanphamRequest);
            }

    });

    }

}
