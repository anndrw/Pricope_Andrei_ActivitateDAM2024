package com.example.androidtest5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

public class BurgerAdaptor extends BaseAdapter {

    private List<Burger> listaBurger;
    private Context ctx;
    private int resourceLayout;

    public BurgerAdaptor(List<Burger> listaBurger, Context ctx, int resourceLayout) {
        this.listaBurger = listaBurger;
        this.ctx = ctx;
        this.resourceLayout = resourceLayout;
    }

    @Override
    public int getCount() {
        return listaBurger.size();
    }

    @Override
    public Object getItem(int position) {
        return listaBurger.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resourceLayout, viewGroup, false);

        TextView denumireTV = v.findViewById(R.id.denumireTV);
        TextView pretTV = v.findViewById(R.id.pretTV);
        TextView gramajTV = v.findViewById(R.id.gramajTV);
        TextView dataTV = v.findViewById(R.id.dataExpirareTV);

        Burger burger = (Burger)getItem(position);

        denumireTV.setText(burger.getDenumire());
        pretTV.setText(String.valueOf(burger.getPret()));
        gramajTV.setText(String.valueOf(burger.getGramaj()));


        Date data = burger.getDataExpirare();
        if(data != null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String format = sdf.format(data);
            dataTV.setText(format);
        }
        return v;
    }
}
