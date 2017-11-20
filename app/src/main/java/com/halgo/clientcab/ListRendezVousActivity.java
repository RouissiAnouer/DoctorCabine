package com.halgo.clientcab;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Halgo on 22/10/2017 nn.
 */

public class ListRendezVousActivity extends AppCompatActivity {

    ListView listView ;
    FloatingActionButton add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rendez_vous_list);
        listView = (ListView) findViewById(R.id.list);
        add=(FloatingActionButton) findViewById(R.id.addRDV);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ListRendezVousActivity.this, NouveauRendezVous.class));
            }
        });
    }
}
