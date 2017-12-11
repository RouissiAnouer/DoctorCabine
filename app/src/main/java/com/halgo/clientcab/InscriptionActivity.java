package com.halgo.clientcab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//
public class InscriptionActivity extends Activity {

    EditText nom;
    EditText prenom;
    EditText cin;
    EditText dateNaiss;
    EditText login;
    EditText pwd;
    EditText tel;
    AppCompatButton inscript;
    RadioGroup sexe;
    RadioButton femal;
    RadioButton male;
    String sexeValue;
    String url = "http://10.0.3.2:8080/GestionCabinet/webapi/patient/addpatient";
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        nom=(EditText)findViewById(R.id.nom);
        prenom=(EditText)findViewById(R.id.prenom);
        cin=(EditText)findViewById(R.id.cin);
        dateNaiss=(EditText)findViewById(R.id.dateNaiss);
        login=(EditText)findViewById(R.id.login);
        pwd=(EditText)findViewById(R.id.pwd);
        tel=(EditText)findViewById(R.id.tel);
        inscript=(AppCompatButton)findViewById(R.id.inscrit);
        sexe=(RadioGroup)findViewById(R.id.sexe);
        femal=(RadioButton)findViewById(R.id.femal);
        male=(RadioButton)findViewById(R.id.male);

        sexe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.femal)
                    sexeValue = "Feminin";
                else
                    sexeValue = "Masculin";
            }


        });
        inscript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sending();
                //startActivity(new Intent(NouveauRendezVous.this, ListRendezVousActivity.class));//ajouter un bouton vers la liste
            }

        });
    }
    private void sending(){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL =  url+"/"+nom.getText().toString()+"/"+prenom.getText().toString()+"/"+cin.getText().toString()+"/"+
                    dateNaiss.getText().toString()+"/"+login.getText().toString()+"/"+pwd.getText().toString()+
                    "/"+tel.getText().toString()+"/"+sexeValue;
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("nom",nom.getText().toString());
            jsonBody.put("prenom",prenom.getText().toString());
            int cin_num=Integer.parseInt(cin.getText().toString());
            jsonBody.put("cin",cin_num);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date=new Date();
            try {
                date = df.parse(dateNaiss.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            jsonBody.put("dateNaiss",date);
            jsonBody.put("login",login.getText().toString());
            jsonBody.put("pwd",pwd.getText().toString());
            int tel_num=Integer.parseInt(tel.getText().toString());
            jsonBody.put("tel",tel_num);
            jsonBody.put("sexe",sexeValue);
            final String requestBody = jsonBody.toString();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                    Toast.makeText(InscriptionActivity.this,"Inscription avec succés",Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    }

