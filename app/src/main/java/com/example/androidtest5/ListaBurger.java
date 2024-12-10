package com.example.androidtest5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaBurger extends AppCompatActivity {
    List<Burger> listaBurger;
    private BurgerAbstract database;
    private int idModificat = 0;

    private BurgerAdaptor adaptor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_burger);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        listaBurger = it.getParcelableArrayListExtra("listaBurger");
        ListView lv = findViewById(R.id.listViewBurger);
//        ArrayAdapter<Burger> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listaBurger);
//        lv.setAdapter(adapter);


        database = Room.databaseBuilder(getApplicationContext(), BurgerAbstract.class, "Burger").build();
        Executor executors = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        executors.execute(new Runnable() {
            @Override
            public void run() {
                listaBurger = database.getInterface().getBurgeriLista();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        BurgerAdaptor adaptor = new BurgerAdaptor(listaBurger, getApplicationContext(), R.layout.res_nou);
                        lv.setAdapter(adaptor);
                    }
                });
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                database.getInterface().delete(listaBurger.get(position));
                adaptor.notifyDataSetChanged();
            }
        });



//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent it = new Intent(getApplicationContext(), BurgerActivitate.class);
//                it.putExtra("BRG", listaBurger.get(position));
//                idModificat = position;
//                startActivityForResult(it, 333);
//            }
//        });


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK && requestCode == 333) {
//            Burger b = data.getParcelableExtra("BRG");
//            listaBurger.set(this.idModificat, b);
//            adaptor.notifyDataSetChanged();
//        }
//    }
}