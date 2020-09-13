package com.example.android_myshop_admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.android_myshop_admin.Model.TopTaiKhoan;
import com.example.android_myshop_admin.R;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class TopTKAdapter  extends BaseAdapter {
    public TopTKAdapter(Context context, ArrayList<TopTaiKhoan> array) {
        this.context = context;
        this.array = array;
    }

    Context context;
    ArrayList<TopTaiKhoan> array;
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


    public  class ViewHolder{
        public TextView txtId,TxtTen,txtDate,TxtTotalprice,txtSL;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TopTKAdapter.ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new TopTKAdapter.ViewHolder();
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_toptaikhoan,null);
            viewHolder.txtId=(TextView) convertView.findViewById(R.id.txtIdTopTK);
            viewHolder.TxtTen=(TextView) convertView.findViewById(R.id.txtTenTopTK);

            viewHolder.TxtTotalprice=(TextView) convertView.findViewById(R.id.txtTongTienTopTK);
            viewHolder.txtSL=(TextView) convertView.findViewById(R.id.txtSLTopTK);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (TopTKAdapter.ViewHolder) convertView.getTag();
        }
        TopTaiKhoan taiKhoan=(TopTaiKhoan) getItem(position);
        viewHolder.txtId.setText(""+taiKhoan.getUser_id());
        viewHolder.TxtTen.setText(taiKhoan.getUser_name());

        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.TxtTotalprice.setText(decimalFormat.format(taiKhoan.getTotalprice())+"Đ");
        viewHolder.txtSL.setText(""+taiKhoan.getSoluongdonhang());
        return convertView;
    }
}
