package com.example.android_myshop_admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_myshop_admin.Adapter.SanPhamAdapter;
import com.example.android_myshop_admin.Adapter.TaiKhoanAdapter;
import com.example.android_myshop_admin.Model.Account;
import com.example.android_myshop_admin.Model.SanPham;
import com.example.android_myshop_admin.R;
import com.example.android_myshop_admin.Ultil.checkConnect;
import com.example.android_myshop_admin.Ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbarTs;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
    ListView listView;
    TaiKhoanAdapter taiKhoanAdapter;
    ArrayList<Account> mangTaiKhoan;
    private SearchView searchView;
    int page=1;
    View footerview;
    boolean isLoading=false;
    boolean limitData=false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        AnhXa();
        if (checkConnect.haveNetworkConnection(getApplicationContext())) {

            ActionToolbar();
            GetData(page);
            LoadMoreData();

        } else {
            checkConnect.ShowToast_Short(getApplicationContext(), "kiem tra ket noi");
            finish();
        }
    }

    private void LoadMoreData() {
        //chi tiet san pham
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //chuyển màn hình
                Intent intent = new Intent(getApplicationContext(), CapNhatTaiKhoanActivity.class);
                Account account = (Account) taiKhoanAdapter.getItem(position);
                intent.putExtra("thongtintaikhoan", account);
                startActivity(intent);
            }
        });
        //sự kiện kéo xuống
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount==totalItemCount && totalItemCount!=0 && isLoading==false &&limitData==false){
                    isLoading=true;
                    TaiKhoanActivity.ThreadData threadData=new TaiKhoanActivity.ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= server.getTaiKhoan+ String.valueOf(page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Integer user_id=0;
                String user_name="";
                String password="";
                String email="";
                String phone="";
                String address="";
                String quyen="";

                if(response!=null && response.length()!=2){
                    listView.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray =new JSONArray(response);
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            user_id=jsonObject.getInt("user_id");
                            user_name = jsonObject.getString("user_name");
                            password = jsonObject.getString("password");
                            email = jsonObject.getString("email");
                            phone = jsonObject.getString("phone");
                            address = jsonObject.getString("address");
                            quyen = jsonObject.getString("quyen");
                            mangTaiKhoan.add((new Account(user_id,user_name,password,email,phone,address,quyen)));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    limitData=true;
                    listView.removeFooterView(footerview);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
                ;
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
                    GetData(++page);
                    isLoading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message=mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_main, menu);
        getMenuInflater().inflate(R.menu.menuthem,menu);
        final MenuItem menuItem = menu.findItem(R.id.search);
        searchView = (SearchView) menuItem.getActionView();
        ((EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text)).
                setHintTextColor(getResources().getColor(R.color.white));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                menuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<Account> filterModList = filter(mangTaiKhoan, newText);
                taiKhoanAdapter.setfilter(filterModList);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuthem:
                Intent intent=new Intent(getApplicationContext(),ThemTaiKhoanActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Account> filter(ArrayList<Account> hi, String query) {
        query = query.toLowerCase();
        final List<Account> filterModeList = new ArrayList<>();
        for (Account modal : hi) {
            final String text = modal.getUserName().toLowerCase();
            if (text.startsWith(query)) {
                filterModeList.add(modal);
            }
        }

        return filterModeList;
    }
    private void AnhXa() {
        toolbarTs=(Toolbar) findViewById(R.id.toolbartaikhoan);
        listView=(ListView) findViewById(R.id.listviewtaikhoan);
        //truyền dữ liệu vào mảng
        mangTaiKhoan=new ArrayList<>();
        taiKhoanAdapter=new TaiKhoanAdapter(getApplicationContext(),mangTaiKhoan);
        listView.setAdapter(taiKhoanAdapter);
        //processbar footer
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview=inflater.inflate(R.layout.progressbar,null);
        mHandler=new mHandler();
    }
}
