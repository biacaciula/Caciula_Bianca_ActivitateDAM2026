package com.example.seminar09;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Weather extends AppCompatActivity {

    ExecutorService executor= Executors.newSingleThreadExecutor();
    Handler handler=new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void searchCity(View view) {
        EditText cityET=findViewById(R.id.CityET);
        String city=cityET.getText().toString();
       // String URL="https://dataservice.accuweather.com/locations/v1/cities/search?apikey=<API_KEY>"+"&q="+ city;
       // Toast.makeText(this, URL, Toast.LENGTH_LONG).show();
        //Log.d("WEATHER",city);
        TextView textView=findViewById(R.id.textView);
        Spinner spinner=findViewById(R.id.spinnerSP);
        String metoda=(spinner.getSelectedItemPosition()==0) ? "1day" : "5day";

        String apiKey = "<API_KEY>";
        String link = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey + "&q=" + city;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }

                    JSONArray vector = new JSONArray(builder.toString());
                    JSONObject obiect = vector.getJSONObject(0);
                    String cheieOras = obiect.getString("Key");

                    String link2 = "https://dataservice.accuweather.com/forecasts/v1/daily/" + metoda + "/" + cheieOras
                            + "?apikey=" + apiKey + "&metric=true";

                    URL url2 = new URL(link2);
                    HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
                    StringBuilder builder2 = new StringBuilder();
                    String line2;
                    while ((line2 = reader2.readLine()) != null) {
                        builder2.append(line2);
                    }

                    JSONObject radacina = new JSONObject(builder2.toString());
                    JSONArray zile = radacina.getJSONArray("DailyForecasts");

                    StringBuilder rezultat = new StringBuilder();
                    for (int i = 0; i < zile.length(); i++) {
                        JSONObject zi = zile.getJSONObject(i);
                        String data = zi.getString("Date").substring(0, 10);
                        JSONObject temperatura = zi.getJSONObject("Temperature");
                        double min = temperatura.getJSONObject("Minimum").getDouble("Value");
                        double max = temperatura.getJSONObject("Maximum").getDouble("Value");
                        rezultat.append(data).append(": min ").append(min)
                                .append(" °C, max ").append(max).append(" °C\n");
                    }
                    String text = rezultat.toString();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(text);
                        }
                    });
                } catch (Exception e) {
                    Log.e("WEATHER", "eroare", e);
                }
            }
        });
    }
}