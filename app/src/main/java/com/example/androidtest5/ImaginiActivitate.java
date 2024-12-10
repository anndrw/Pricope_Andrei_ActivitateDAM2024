package com.example.androidtest5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiActivitate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<Bitmap> listaImagini = new ArrayList<>();
        List<ImaginiDomeniu> lista = new ArrayList<>();
        List<String> linkuri = new ArrayList<>();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagini_activitate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        linkuri.add("https://static.mega-image.ro/site/binaries/_ht_1620397528117/lg/content/gallery/image-components/mega-image-romania-ro/articole-inspiratie/burgeri/image002.jpg?imwidth=690");
        linkuri.add("https://static.mega-image.ro/site/binaries/_ht_1620397528986/lg/content/gallery/image-components/mega-image-romania-ro/articole-inspiratie/burgeri/image004.jpg?imwidth=690");
        linkuri.add("https://static.mega-image.ro/site/binaries/_ht_1620397529893/lg/content/gallery/image-components/mega-image-romania-ro/articole-inspiratie/burgeri/image006.jpg?imwidth=690");
        linkuri.add("https://static.mega-image.ro/site/binaries/_ht_1620397530459/lg/content/gallery/image-components/mega-image-romania-ro/articole-inspiratie/burgeri/image008.jpg?imwidth=690");
        linkuri.add("https://static.mega-image.ro/site/binaries/_ht_1620397530965/lg/content/gallery/image-components/mega-image-romania-ro/articole-inspiratie/burgeri/image010.jpg?imwidth=690");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(() -> {
            for (String link : linkuri) {
                try {
                    URL url = new URL(link);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    InputStream is = con.getInputStream();
                    listaImagini.add(BitmapFactory.decodeStream(is));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (listaImagini.size() == linkuri.size()) {
                lista.add(new ImaginiDomeniu("Burger Killer", listaImagini.get(0), "https://www.mega-image.ro/inspiratie/idei-pentru-tine/hamburgeri-de-casa"));
                lista.add(new ImaginiDomeniu("Burger Cosmos", listaImagini.get(1), "https://www.mega-image.ro/inspiratie/idei-pentru-tine/hamburgeri-de-casa"));
                lista.add(new ImaginiDomeniu("Burger Gorp", listaImagini.get(2), "https://www.mega-image.ro/inspiratie/idei-pentru-tine/hamburgeri-de-casa"));
                lista.add(new ImaginiDomeniu("Burger GOAT", listaImagini.get(3), "https://www.mega-image.ro/inspiratie/idei-pentru-tine/hamburgeri-de-casa"));
                lista.add(new ImaginiDomeniu("Burger Cheesy", listaImagini.get(4), "https://www.mega-image.ro/inspiratie/idei-pentru-tine/hamburgeri-de-casa"));
            }

            handler.post(() -> {
                ListView lv = findViewById(R.id.imaginiLV);
                ImagineAdapter adapter = new ImagineAdapter(lista, ImaginiActivitate.this, R.layout.res_imagini);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener((parent, view, position, id) -> {
                    Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                    it.putExtra("link", lista.get(position).getLink());
                    startActivity(it);
                });
            });
        });






    }
}