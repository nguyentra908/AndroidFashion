package com.example.android_myshop_admin.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android_myshop_admin.Model.Account;
import com.example.android_myshop_admin.Model.SanPham;
import com.example.android_myshop_admin.R;
import com.squareup.picasso.Picasso;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanAdapter extends BaseAdapter {
    public TaiKhoanAdapter(Context context, ArrayList<Account> array) {
        this.context = context;
        this.array = array;
    }

    Context context;
    ArrayList<Account> array;
    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return array.indexOf(getItem(position));

    }
    public void setfilter(List<Account> actorsList){
        array=new ArrayList<>();
        array.addAll(actorsList);
        notifyDataSetChanged();

    }


    public  class ViewHolder{
        public TextView txtTen,TxtEmail,TxtDienThoai,txtDiaChi;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TaiKhoanAdapter.ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new TaiKhoanAdapter.ViewHolder();
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_taikhoan,null);
            viewHolder.txtTen=(TextView) convertView.findViewById(R.id.txtTenTK);
            viewHolder.TxtEmail=(TextView) convertView.findViewById(R.id.txtEmailTK);
            viewHolder.TxtDienThoai=(TextView) convertView.findViewById(R.id.txtDienThoaiTK);
            viewHolder.txtDiaChi=(TextView) convertView.findViewById(R.id.txtDiaChiTK);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (TaiKhoanAdapter.ViewHolder) convertView.getTag();
        }
        Account account=(Account) getItem(position);
        viewHolder.txtTen.setText(account.getUserName());
        viewHolder.TxtEmail.setText(account.getEmail());
        viewHolder.TxtDienThoai.setText(account.getPhone());
        viewHolder.txtDiaChi.setText(account.getAddress());

        return convertView;
    }
}
