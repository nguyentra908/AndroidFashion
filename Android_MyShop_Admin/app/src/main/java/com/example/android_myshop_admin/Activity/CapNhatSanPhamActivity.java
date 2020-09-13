package com.example.android_myshop_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_myshop_admin.Model.RegisterUserClass;
import com.example.android_myshop_admin.Model.SanPham;
import com.example.android_myshop_admin.R;
import com.example.android_myshop_admin.Ultil.checkConnect;
import com.example.android_myshop_admin.Ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CapNhatSanPhamActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbarTs;
    EditText editTenSP,editMaLoaiSP,editGiaSP,editMauSP,editChatLieuSP,editMoTaSP,editAnhSP;
    Button btnCapNhatSP,btnXoaSP;
    String tensanpham = "";
    String hinhanhsanpham = "";
    String mau = "";
    String chatlieu = "";
    String motasanpham = "";
    int giasanpham=0;
    int id_type = 0;
    int id =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_san_pham);
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

    private void AnhXa() {
        toolbarTs=(Toolbar) findViewById(R.id.toolbarCapNhat);
        editTenSP=(EditText) findViewById(R.id.editTenSP);
        editMaLoaiSP=(EditText) findViewById(R.id.editMaLoaiSP);
        editGiaSP=(EditText) findViewById(R.id.editGiaSP);
        editMauSP=(EditText) findViewById(R.id.editMauSP);
        editChatLieuSP=(EditText) findViewById(R.id.editChatLieuSP);
        editMoTaSP=(EditText) findViewById(R.id.editMoTaSP);
        editAnhSP=(EditText) findViewById(R.id.editAnhSP);
        btnCapNhatSP=(Button) findViewById(R.id.btnCapNhatSP);
        btnXoaSP=(Button) findViewById(R.id.btnxoaSP);
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

    private void getInformation() {

        SanPham sanPham= (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        id=sanPham.getId();
        tensanpham=sanPham.getName();
        giasanpham=sanPham.getPrice();
        hinhanhsanpham=sanPham.getImage();
        motasanpham=sanPham.getDescription();
        mau=sanPham.getColor();
        chatlieu=sanPham.getMaterial();
        id_type=sanPham.getId_type();

        //editMaLoaiSP.setText(String.valueOf(id));
        editMaLoaiSP.setText(String.valueOf(id_type));
        editTenSP.setText(tensanpham);
        editGiaSP.setText(String.valueOf(giasanpham));
        editAnhSP.setText(hinhanhsanpham);
        editMauSP.setText(mau);
        editChatLieuSP.setText(chatlieu);
        editMoTaSP.setText(motasanpham);

    }

    private void EventButton() {
        btnCapNhatSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tensanpham = editTenSP.getText().toString();
                id_type = Integer.parseInt(editMaLoaiSP.getText().toString());
                giasanpham = Integer.parseInt(editGiaSP.getText().toString());
                hinhanhsanpham = editAnhSP.getText().toString();
                mau = editMauSP.getText().toString();
                motasanpham = editMoTaSP.getText().toString();
                chatlieu = editChatLieuSP.getText().toString();

                GetData(id, tensanpham, id_type, giasanpham, mau,chatlieu,motasanpham);

            }
        });

        btnXoaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                XoaSP(id);

            }
        });
    }

    private void XoaSP(final int id) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= server.XoaSanPhamAD;
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
                data.put("id", String.valueOf(id));
                data.put("id_product ", String.valueOf(id));


                return data;
            }
        };
        requestQueue.add(stringRequest);
    }




    private void GetData(final Integer id, final String tensanpham, final Integer id_type,
                         final Integer giasanpham, final String mau, final String chatlieu, final String motasanpham) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= server.CapNhatSP;
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
                data.put("id", String.valueOf(id));
                data.put("name", tensanpham);
                data.put("id_type", String.valueOf(id_type));
                data.put("price",String.valueOf(giasanpham));
                data.put("color",mau);
                data.put("material", chatlieu);
                data.put("description", motasanpham);

                return data;
            }
        };
        requestQueue.add(stringRequest);
    }



}
