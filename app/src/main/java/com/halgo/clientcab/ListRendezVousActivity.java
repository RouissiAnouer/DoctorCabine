package com.halgo.clientcab;


import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Halgo on 22/10/2017 nn.
 */

public class ListRendezVousActivity extends AppCompatActivity {

    String url ="http://10.0.3.2:8080/GestionCabinet/webapi/rendezvous/getByPatient?id=";
    RequestQueue request;
    //ListView listView ;
    FloatingActionButton add;
    String [] values;

    private List<Example> rdvList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RendezVousAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rendez_vous_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        add=(FloatingActionButton) findViewById(R.id.addRDV);

        mAdapter = new RendezVousAdapter(rdvList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        final Long idCompte=getIntent().getLongExtra("idCompte",0L);
        request= Volley.newRequestQueue(this);
        JsonArrayRequest obreq=new JsonArrayRequest(Request.Method.GET,url+idCompte.toString(),null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if ((response != null) && (response.length() > 0)) {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject rdv = response.getJSONObject(i);
                                    String heureRdv = rdv.getString("heureRdv");
                                    String dateRdv = rdv.getString("dateRdv");
                                    String etatRdv = rdv.getString("etatRdv");
                                    Long idRdv=rdv.getLong("idRdv");
                                    Example item=new Example(idRdv,etatRdv,dateRdv,heureRdv);
                                    rdvList.add(item);
                                }
                                mAdapter.notifyDataSetChanged();
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
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent inten=new Intent(ListRendezVousActivity.this, NouveauRendezVous.class);
                inten.putExtra("idCompte",idCompte);
                startActivity(inten);
            }
        });

    }
}
