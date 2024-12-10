package com.example.androidtest5;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CityActivity extends AppCompatActivity {


    private String linkAPI = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=wMBA7gjZW1uNoaaBqVzMNDfyuMdTVp5B&q=";
    private String APILocatie = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/";
    private String metric = "?apikey=wMBA7gjZW1uNoaaBqVzMNDfyuMdTVp5B&metric=true";
    private String tempMin;
    private String tempMax;
    private String cheieOras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_city);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        Button obtine = findViewById(R.id.obtineBtn);
        obtine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etCity = findViewById(R.id.cityET);
                String oras = etCity.getText().toString();
                linkAPI += oras;

                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());


                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(linkAPI);
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();

                            StringBuilder sb = new StringBuilder();
                            Scanner scanner = new Scanner(con.getInputStream());
                            if(scanner.hasNext())
                            {
                                sb.append(scanner.nextLine());
                            }

                            JSONArray vector = new JSONArray(sb.toString());
                            JSONObject obiect = vector.getJSONObject(0);
                            cheieOras = obiect.getString("Key");
                           // linkAPI+=cheieOras;

                            APILocatie = APILocatie + cheieOras + metric;
                            URL url2 = new URL(APILocatie);
                            HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
                            StringBuilder sb2 = new StringBuilder();
                            Scanner scanner2 = new Scanner(con2.getInputStream());
                            if(scanner2.hasNext()) {
                                sb2.append(scanner2.nextLine());
                            }
                            JSONObject raspuns = new JSONObject(sb2.toString());
                            JSONArray vreme = raspuns.getJSONArray("DailyForecasts");
                            JSONObject day = vreme.getJSONObject(0);
                            JSONObject temp = day.getJSONObject("Temperature");
                            JSONObject minObj = temp.getJSONObject("Minimum");
                            tempMin = String.valueOf(minObj.getDouble("Value"));
                            JSONObject maxObj = temp.getJSONObject("Maximum");
                            tempMax = String.valueOf(maxObj.getDouble("Value"));




                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                TextView tvCod = findViewById(R.id.afisareCity);
                                tvCod.setText(cheieOras);
                                TextView tvTempMin = findViewById(R.id.afisareVremeMin);
                                tvTempMin.setText(tempMin);
                                TextView tvTempMax = findViewById(R.id.afisareVremeMax);
                                tvTempMax.setText(tempMax);

                            }
                        });
                    }
                });
            }
        });
    }
}
