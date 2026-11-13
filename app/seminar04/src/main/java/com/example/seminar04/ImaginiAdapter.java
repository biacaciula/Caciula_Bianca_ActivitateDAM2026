package com.example.seminar04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImaginiAdapter extends BaseAdapter {

    private Context ctx;
    private List<Imagini> imagini;
    private int resursaLayout;

    public ImaginiAdapter(Context ctx, List<Imagini> imagini, int resursaLayout)
    {
        this.ctx=ctx;
        this.imagini=imagini;
        this.resursaLayout=resursaLayout;
    }


    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int i) {
        return imagini.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout,parent,false);

        ImageView imagineIV = v.findViewById(R.id.imagine_iv);
        TextView textTV = v.findViewById(R.id.descriere_tv);

        Imagini item = imagini.get(position);
        textTV.setText(item.getTextAfisat());
        if (item.getImagine() != null) {
            imagineIV.setImageBitmap(item.getImagine());
        }
        return v;
    }
}
