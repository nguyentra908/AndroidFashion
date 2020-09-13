package com.example.android_myshop_admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.android_myshop_admin.Model.ChiTietDonHang;
import com.example.android_myshop_admin.R;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietdonHangAdapter  extends BaseAdapter {
    Context context;
    ArrayList<ChiTietDonHang> arrayChiTietDonHang;

    public ChiTietdonHangAdapter(Context context, ArrayList<ChiTietDonHang> arrayChiTietDonHang) {
        this.context = context;
        this.arrayChiTietDonHang = arrayChiTietDonHang;
    }

    @Override
    public int getCount() {
        return arrayChiTietDonHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayChiTietDonHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public  class ViewHolder{
        public TextView txtmasanpham,txttensanpham,txtsoluong,txtgia;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChiTietdonHangAdapter.ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ChiTietdonHangAdapter.ViewHolder();
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_chitietdonhang,null);
            viewHolder.txtmasanpham=(TextView) convertView.findViewById(R.id.txtMaSanPham);
            viewHolder.txttensanpham=(TextView) convertView.findViewById(R.id.txtTenSanPham);
            viewHolder.txtsoluong=(TextView) convertView.findViewById(R.id.txtSoLuong);
            viewHolder.txtgia=(TextView) convertView.findViewById(R.id.txtGia);

            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ChiTietdonHangAdapter.ViewHolder) convertView.getTag();
        }
        ChiTietDonHang chiTietDonHang=(ChiTietDonHang) getItem(position);
        viewHolder.txtmasanpham.setText(""+chiTietDonHang.getId());

        viewHolder.txttensanpham.setText(chiTietDonHang.getName());
        viewHolder.txtsoluong.setText(""+chiTietDonHang.getQuantity());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgia.setText(""+decimalFormat.format(chiTietDonHang.getPrice())+"Đ");

        return convertView;
    }
}
