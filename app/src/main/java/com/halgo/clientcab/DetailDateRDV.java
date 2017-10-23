package com.halgo.clientcab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Halgo on 23/10/2017.
 */

public class DetailDateRDV extends AppCompatActivity {

    Button heurescreen;

    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_rdv_date_activity);

        heurescreen = (Button)findViewById(R.id.heurescreen);
        heurescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailDateRDV.this, DetailHeureRDV.class));
            }
        });
    }
}
