package com.example.seminar04;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.seminar02.Fabrica;

import java.util.ArrayList;
import android.content.Intent;

public class ListaActivity extends AppCompatActivity {

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
        ArrayList<Fabrica> lista=(ArrayList<Fabrica>) getIntent().getSerializableExtra("lista");
        ListView listView=findViewById(R.id.lista_lv);
        ArrayAdapter<Fabrica>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lista);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent,view,position,id)->{
            Toast.makeText(this,lista.get(position).toString(),Toast.LENGTH_LONG).show();});
        listView.setOnItemLongClickListener((parent,view,position,id)->{
            lista.remove(position);
            adapter.notifyDataSetChanged();
            Intent it = new Intent();
            it.putExtra("lista",lista);
            setResult(RESULT_OK,it);
            return true;
        });
    }

}