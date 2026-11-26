package com.example.seminar04;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiActivity extends AppCompatActivity {

    private List<Imagini> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<String> linkuriImagini=new ArrayList<>();
        linkuriImagini.add("https://s.iw.ro/gateway/g/ZmlsZVNvdXJjZT1odHRwJTNBJTJGJTJG/c3RvcmFnZTAzdHJhbnNjb2Rlci5yY3Mt/cmRzLnJvJTJGc3RvcmFnZSUyRjIwMTMl/MkYxMCUyRjE4JTJGMTUwOTU5XzE1MDk1/OV9lbGJhX0EuanBnJnc9NzgwJmg9NDQw/Jmhhc2g9OTNiNGI2NzNmNjMxNzlmNDFhZmI1ZTU2ZGYxMThmNzU=.thumb.jpg?tmp=2026-11-10-14-25-51&app=req-6af30d4f86d13&retry=1");
        linkuriImagini.add("https://www.tevi-laminate.ro/blog/wp-content/uploads/2021/06/industria-siderurgica.jpg");
        linkuriImagini.add("https://bizbrasov.ro/wp-content/uploads/2016/11/industrie-fabrica-muncitori.jpg");
        linkuriImagini.add("https://www.ahkrumaenien.ro/var/site/storage/images/_aliases/image_1920/6/4/9/2/152946-2-ger-DE/27702448de79-Industrie.jpg");
        linkuriImagini.add("https://www.sap.com/romania/resources/media_10a478f53e2c63dce483efbf17f039c449aa71fa1.jpg?width=1200&format=pjpg&optimize=medium");

        String[] texte = {"Descriere 1", "Descriere 2", "Descriere 3", "Descriere 4", "Descriere 5"};
        String[] linkuriPagini = {"LINK_PAGINA_1", "LINK_PAGINA_2", "LINK_PAGINA_3",
                "LINK_PAGINA_4", "LINK_PAGINA_5"};

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<Bitmap> bitmaps = new ArrayList<>();
                for (String link : linkuriImagini) {
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        bitmaps.add(BitmapFactory.decodeStream(is));
                    } catch (IOException e) {
                        Log.e("ImaginiActivity", "Eroare la descarcare: " + link);
                        bitmaps.add(null);
                    } finally {
                        if (con != null) con.disconnect();
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista = new ArrayList<>();
                        for (int i = 0; i < linkuriImagini.size(); i++) {
                            lista.add(new Imagini(texte[i], bitmaps.get(i), linkuriPagini[i]));
                        }

                        ListView lv = findViewById(R.id.imagini);
                        ImaginiAdapter adapter = new ImaginiAdapter(getApplicationContext(),
                                lista, R.layout.item_imagini);
                        lv.setAdapter(adapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                                it.putExtra("link", lista.get(i).getLink());
                                startActivity(it);
                            }
                        });
                    }
                });
            }
        });
    }
}