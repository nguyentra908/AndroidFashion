package com.example.android_myshop_admin.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_myshop_admin.Model.DonHang;
import com.example.android_myshop_admin.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DonHangAdapter  extends BaseAdapter {

    Context context;
    ArrayList<DonHang> arrayDonHang;

    public DonHangAdapter(Context context, ArrayList<DonHang> arrayDonHang) {
        this.context = context;
        this.arrayDonHang = arrayDonHang;
    }

    @Override
    public int getCount() {
        return arrayDonHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayDonHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public  class ViewHolder{
        public TextView txtmadonhang,txtmakhachhang,txtngaydathang,txtnote,txtstatus,txttongtien;

    }
    public void setfilter(List<DonHang> actorsList){
        arrayDonHang=new ArrayList<>();
        arrayDonHang.addAll(actorsList);
        notifyDataSetChanged();

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DonHangAdapter.ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_donhang,null);
            viewHolder.txtmadonhang=(TextView) convertView.findViewById(R.id.txtMaDonHang);
            viewHolder.txtmakhachhang=(TextView) convertView.findViewById(R.id.txtMaKhachHang);
            viewHolder.txtngaydathang=(TextView) convertView.findViewById(R.id.txtNgayDatHang);
            viewHolder.txttongtien=(TextView) convertView.findViewById(R.id.txtTongTien);
//            viewHolder.txtnote=(TextView) convertView.findViewById(R.id.txtNote);
            viewHolder.txtstatus=(TextView) convertView.findViewById(R.id.txtStatus);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (DonHangAdapter.ViewHolder) convertView.getTag();
        }
        DonHang donHang=(DonHang) getItem(position);
        viewHolder.txtmadonhang.setText(""+donHang.getId());
        viewHolder.txtmakhachhang.setText(""+donHang.getId_customer());
        viewHolder.txtngaydathang.setText(donHang.getDate_order());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txttongtien.setText(""+decimalFormat.format(donHang.getTotal())+"Đ");
     //   viewHolder.txtnote.setText(donHang.getNote());
        viewHolder.txtstatus.setText(donHang.getStatus());
        return convertView;
    }
}
