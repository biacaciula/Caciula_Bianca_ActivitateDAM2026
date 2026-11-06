package com.example.seminar04;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.seminar02.Fabrica;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;

public class ListaActivity extends AppCompatActivity {

    private ArrayList<Fabrica> lista;
    private int idModificat=0;
    private FabricaAdapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lista=getIntent().getParcelableArrayListExtra("lista");
        ListView listView=findViewById(R.id.lista_lv);
        //ArrayAdapter<Fabrica>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lista);
        //listView.setAdapter(adapter);
        adapter=new FabricaAdapter(this,lista,R.layout.item_layout);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent=new Intent(getApplicationContext(),FabricaActivity.class);
                intent.putExtra("fabrica",lista.get(i));
                idModificat=i;
                startActivityForResult(intent,209);
            }
        });

        listView.setOnItemLongClickListener((parent,view,position,id)->{
            lista.remove(position);
            adapter.notifyDataSetChanged();
            Intent it = new Intent();
            it.putParcelableArrayListExtra("lista",lista);
            setResult(RESULT_OK,it);
            return true;
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==209)
            {

                lista.set(idModificat,data.getParcelableExtra("fabrica"));
                adapter.notifyDataSetChanged();

                Intent it = new Intent();
                it.putParcelableArrayListExtra("lista",lista);
                setResult(RESULT_OK,it);
            }
        }
    }

}