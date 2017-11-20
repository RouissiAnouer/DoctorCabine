package com.halgo.clientcab;


import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Halgo on 22/10/2017 nn.
 */

public class ListRendezVousActivity extends AppCompatActivity {

    String url ="http://10.0.2.2:8080/GestionCabinet/webapi/utilisateur/androidConnect?";
    RequestQueue request;
    ListView listView ;
    FloatingActionButton add;
    String [] values;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rendez_vous_list);
        listView = (ListView) findViewById(R.id.list);
        Long idCompte=getIntent().getLongExtra("idCompte",0L);
        request=Volley.newRequestQueue(this);
        JsonArrayRequest obreq=new JsonArrayRequest(Request.Method.GET,url+idCompte.toString(),null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if ((response != null) && (response.length() > 0)) {
                                values = new String[response.length()];
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject rdv = response.getJSONObject(i);
                                    Date dateRdv = rdv.getDate("dateRdv");
                                    Date heureRdv = rdv.getDate("heureRdv");
                                    String etatRdv = rdv.getString("etatRdv");
                                    values[i] = etatRdv + "\n" + dateRdv + "\n" + heureRdv;

                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listviewexemple, values);
                                listView.setAdapter(adapter);

                            } else {
                                Toast.makeText(getApplicationContext(), "Vous n'avez aucun Rendez-Vous !!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
                );
        request.add(obreq);
        add=(FloatingActionButton) findViewById(R.id.addRDV);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ListRendezVousActivity.this, NouveauRendezVous.class));
            }
        });
    }
}
