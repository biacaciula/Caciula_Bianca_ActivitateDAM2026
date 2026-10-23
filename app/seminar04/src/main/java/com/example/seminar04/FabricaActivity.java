package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.seminar02.Fabrica;


public class FabricaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fabrica);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void preiaDate(View view) {
        EditText et1=findViewById(R.id.nrAngajati_et);
        EditText et2=findViewById(R.id.numeFabrica_et);
        EditText et3=findViewById(R.id.codFabrica_et);
        EditText et4=findViewById(R.id.domeniuActivitate_et);
        CheckBox cb = findViewById(R.id.Ture_cb);
        int nrAngajati = Integer.parseInt(et1.getText().toString());
        String numeFabrica = et2.getText().toString();
        int codFabrica=Integer.parseInt(et3.getText().toString());
        String domeniuActivitate=et4.getText().toString();
        Boolean lucreazaTure=cb.isChecked();
        Fabrica fabrica = new Fabrica(nrAngajati, numeFabrica, codFabrica,domeniuActivitate, lucreazaTure);
        Intent it = new Intent();
        it.putExtra("fabrica", fabrica);

        setResult(RESULT_OK,it);
        finish();
    }
}