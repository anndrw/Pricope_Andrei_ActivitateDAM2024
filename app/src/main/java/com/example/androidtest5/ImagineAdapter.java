package com.example.androidtest5;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private List<ImaginiDomeniu> listaImagini;
    private Context ctx;
    private int resourceLayout;

    public ImagineAdapter(List<ImaginiDomeniu> listaImagini, Context ctx, int resourceLayout) {
        this.listaImagini = listaImagini;
        this.ctx = ctx;
        this.resourceLayout = resourceLayout;
    }

    @Override
    public int getCount() {
        return listaImagini.size();
    }

    @Override
    public Object getItem(int position) {
        return listaImagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resourceLayout, viewGroup, false);

        ImaginiDomeniu imagine = (ImaginiDomeniu)getItem(position);

        ImageView img = v.findViewById(R.id.imagineTV);
        TextView text = v.findViewById(R.id.textTV);
        text.setText(imagine.getText());
        img.setImageBitmap(imagine.getImagine());

        return v;
    }
}
