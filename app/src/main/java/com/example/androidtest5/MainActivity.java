package com.example.androidtest5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Burger> listaBurger = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2004 && resultCode == RESULT_OK && data != null)
        {
            Burger burger = data.getParcelableExtra("Burger");
            if(data != null)
            {
                listaBurger.add(burger);
            }
        }
    }

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

        Button nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), BurgerActivitate.class);
                startActivityForResult(it, 2004);
            }
        });

        Button listaBtn = findViewById(R.id.listaBtn);
        listaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListaBurger.class);
                it.putParcelableArrayListExtra("listaBurger", (ArrayList<? extends Parcelable>) listaBurger);
                startActivity(it);
            }
        });

        Button imaginiBtn = findViewById(R.id.imagini);
        imaginiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ImaginiActivitate.class);
                startActivityForResult(it, 2003);
            }
        });

        Button cityBtn = findViewById(R.id.cityBtn);
        cityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), CityActivity.class);
                startActivityForResult(it, 2003);
            }
        });
    }
}