package com.example.android_myshop_admin.Adapter;

import android.content.Context;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android_myshop_admin.Model.SanPham;
import com.example.android_myshop_admin.R;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> array;

    public SanPhamAdapter(Context context, ArrayList<SanPham> array) {
        this.context = context;
        this.array = array;
    }

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
    public void setfilter(List<SanPham> actorsList){
        array=new ArrayList<>();
        array.addAll(actorsList);
        notifyDataSetChanged();

    }

    public  class ViewHolder{
        public TextView txtTen,TxtGia,TxtMota,txtMau,txtChatLieu;
        public ImageView img;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_sanpham,null);
            viewHolder.txtTen=(TextView) convertView.findViewById(R.id.txtTen);
            viewHolder.TxtGia=(TextView) convertView.findViewById(R.id.txtGia);
            viewHolder.TxtMota=(TextView) convertView.findViewById(R.id.txtmota);
            viewHolder.txtMau=(TextView) convertView.findViewById(R.id.txtmau);
            viewHolder.txtChatLieu=(TextView) convertView.findViewById(R.id.txtchatlieu);
            viewHolder.img=convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        SanPham sanPham=(SanPham) getItem(position);
        viewHolder.txtTen.setText(sanPham.getName());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.TxtGia.setText(decimalFormat.format(sanPham.getPrice())+"Đ");
        viewHolder.txtMau.setText(sanPham.getColor());
        viewHolder.txtChatLieu.setText(sanPham.getMaterial());
        viewHolder.TxtMota.setMaxLines(2);
        viewHolder.TxtMota.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.TxtMota.setText(sanPham.getDescription() );
        Picasso.with(context).load(sanPham.getImage())
                //     .placeholder(R.drawable.loader)
                //     .error(R.drawable.close)
                .into(viewHolder.img);
        return convertView;
    }
}