package com.example.android_myshop_admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.android_myshop_admin.Model.TopSanPham;
import com.example.android_myshop_admin.R;
import java.util.ArrayList;
public class TopSPAdapter  extends BaseAdapter {
    public TopSPAdapter(Context context, ArrayList<TopSanPham> array) {
        this.context = context;
        this.array = array;
    }

    Context context;
    ArrayList<TopSanPham> array;
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
        public TextView txtId,TxtTen,txtSL;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TopSPAdapter.ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new TopSPAdapter.ViewHolder();
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_topsanpham,null);
            viewHolder.txtId=(TextView) convertView.findViewById(R.id.txtIdTopSP);
            viewHolder.TxtTen=(TextView) convertView.findViewById(R.id.txtTenTopSP);
            viewHolder.txtSL=(TextView) convertView.findViewById(R.id.txtTongTopSP);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (TopSPAdapter.ViewHolder) convertView.getTag();
        }
        TopSanPham sanPham=(TopSanPham) getItem(position);
        viewHolder.txtId.setText(""+sanPham.getId());
        viewHolder.TxtTen.setText(sanPham.getName());
        viewHolder.txtSL.setText(""+sanPham.getSoluongsp());
        return convertView;
    }
}