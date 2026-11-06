package com.example.seminar04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.seminar02.Fabrica;

import java.util.List;

public class FabricaAdapter extends BaseAdapter
{
    private Context ctx;
    private List<Fabrica> fabrici=null;
    private int resursaLayout;

    public FabricaAdapter(Context ctx, List<Fabrica> fabrici, int resursaLayout)
    {
        this.ctx=ctx;
        this.fabrici=fabrici;
        this.resursaLayout=resursaLayout;
    }

    @Override
    public int getCount() {
        return fabrici.size();
    }

    @Override
    public Object getItem(int i) {
        return fabrici.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout,parent,false);
        TextView nrAngajatiTV=v.findViewById(R.id.nrAngajatiTV);
        TextView numeFabricaTV=v.findViewById(R.id.numeFabricaTV);
        TextView codFabricaTV=v.findViewById(R.id.codFabricaTV);
        TextView domeniuFabricaTV=v.findViewById(R.id.domeniuActivitateTV);
        CheckBox lucreazaTureCB=v.findViewById(R.id.lucreazaTureCB);

        Fabrica fabrica=(Fabrica)getItem(i);

        nrAngajatiTV.setText(String.valueOf(fabrica.getNrAngajati()));
        numeFabricaTV.setText(fabrica.getNumeFabrica());
        codFabricaTV.setText(String.valueOf(fabrica.getCodFabrica()));
        domeniuFabricaTV.setText(fabrica.getDomeniuActivitate());
        lucreazaTureCB.setChecked(fabrica.isLucreazaInTure());
        return v;
    }
}
