package com.example.androidtest5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Database;
import androidx.room.Room;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BurgerActivitate extends AppCompatActivity {

    private BurgerAbstract database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_burger_activitate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        EditText etDenumire = findViewById(R.id.etDenumire);
//        EditText etPret = findViewById(R.id.etPret);
//        EditText etGramaj = findViewById(R.id.etGramaj);
//        DatePicker dataExp = findViewById(R.id.dataExpirareD);
//        Intent it = getIntent();
//
//        if(it.hasExtra("BRG")) {
//            Burger b = it.getParcelableExtra("BRG");
//            etDenumire.setText(b.getDenumire());
//            etPret.setText(String.valueOf(b.getPret()));
//            etGramaj.setText(String.valueOf((b.getGramaj())));
//
//        }


        Button plusBtn = findViewById(R.id.plusBtn);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etDenumire = findViewById(R.id.etDenumire);
                EditText etPret = findViewById(R.id.etPret);
                EditText etGramaj = findViewById(R.id.etGramaj);
                DatePicker dataExp = findViewById(R.id.dataExpirareD);

                String denumire = etDenumire.getText().toString();
                int pret = Integer.parseInt(etPret.getText().toString());
                float gramaj = Float.parseFloat(etGramaj.getText().toString());

                int day = dataExp.getDayOfMonth();
                int month = dataExp.getMonth();
                int year = dataExp.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                long millis = calendar.getTimeInMillis();
                Date data = new Date(millis);

                Burger burger = new Burger(denumire, pret, gramaj, data);

                Executor executors = Executors.newSingleThreadExecutor();
                database = Room.databaseBuilder(getApplicationContext(), BurgerAbstract.class, "Burger").build();
                executors.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.getInterface().insert(burger);
                    }
                });


                Intent it = new Intent();
                it.putExtra("Burger", burger);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}