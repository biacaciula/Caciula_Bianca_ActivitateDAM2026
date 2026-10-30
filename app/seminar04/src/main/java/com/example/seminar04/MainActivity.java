package com.example.seminar04;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.seminar02.Fabrica;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fabrica> listaFabrici = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void culegeDate(View view) {
        EditText et = findViewById(R.id.nume_et);
        CheckBox cb=findViewById(R.id.esteZi_CB);
        String nume = et.getText().toString();
        Boolean esteZi=cb.isChecked();
    }

    public void introduDate(View view) {
        Intent it = new Intent(this, FabricaActivity.class);
        startActivityForResult(it,45);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==45 && resultCode==RESULT_OK && data!=null) {
            Fabrica fabrica = (Fabrica) data.getExtras().getSerializable("fabrica");
            listaFabrici.add(fabrica);
            Toast.makeText(this, fabrica.toString(), Toast.LENGTH_LONG).show();
            TextView rezultat = findViewById(R.id.rezultat);
            String existing = rezultat.getText().toString();
            rezultat.setText(existing + (!existing.isEmpty() ? "\n\n" : "") + fabrica.toString());
        }
        if(requestCode==46&&resultCode==RESULT_OK&&data!=null)
        {
            listaFabrici=(ArrayList<Fabrica>)data.getSerializableExtra("lista");
        }
    }

    public void adaugaFabrici(View view) {
        Intent it = new Intent(this, ListaActivity.class);
        it.putExtra("lista", listaFabrici);
        startActivityForResult(it, 46);
    }
}