package com.halgo.clientcab;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {



    TextView erreur;
    AppCompatEditText login;
    AppCompatEditText pwd;
    AppCompatButton btn;
    RequestQueue requestQueue;
    TextInputLayout loginLayout;
    TextInputLayout pwdLayout;
    TextView pw_oubli;
    TextView inscription;
    String url = "http://10.0.2.2:8080/GestionCabinet/webapi/utilisateur/androidConnect/";
    Long id=0L;




    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };

    void checkFieldsForEmptyValues(){
        Button b = (Button) findViewById(R.id.ok);

        String s1 = login.getText().toString();
        String s2 = pwd.getText().toString();

        if(s1.equals("")|| s2.equals("")){
            b.setEnabled(false);
        } else {
            b.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);

        login=(AppCompatEditText)findViewById(R.id.loginvalue);
        pwd=(AppCompatEditText)findViewById(R.id.pwdv);
        erreur=(TextView) findViewById(R.id.erreur);
        btn= (AppCompatButton) findViewById(R.id.ok);
        loginLayout=(TextInputLayout)findViewById(R.id.login_text);
        pwdLayout=(TextInputLayout)findViewById(R.id.pwd_text);
        pw_oubli=(TextView)findViewById(R.id.pw_oubli);
        inscription=(TextView)findViewById(R.id.inscription);

        pw_oubli.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,VerificationPass.class));
            }
        });

        inscription.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,InscriptionActivity.class));
            }
        });

        login.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(login.getText().toString().isEmpty()){
                    loginLayout.setErrorEnabled(true);
                    loginLayout.setError("*Tapez votre login svp!!");
                }else{
                    loginLayout.setErrorEnabled(false);
                }
            }
        });

        login.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after){
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                if(login.getText().toString().isEmpty()){
                    loginLayout.setErrorEnabled(true);
                    loginLayout.setError("*Tapez votre login svp!!");
                }else{
                    loginLayout.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s){

            }
        });

        pwd.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(pwd.getText().toString().isEmpty()){
                    pwdLayout.setErrorEnabled(true);
                    pwdLayout.setError("*Tapez votre mot de passe svp!!");
                }else{
                    pwdLayout.setErrorEnabled(false);
                }
            }
        });

        pwd.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after){
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                if(pwd.getText().toString().isEmpty()){
                    pwdLayout.setErrorEnabled(true);
                    pwdLayout.setError("*Tapez votre mot de passe svp!!");
                }else{
                    pwdLayout.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s){

            }
        });
        // set listeners
        login.addTextChangedListener(mTextWatcher);
        pwd.addTextChangedListener(mTextWatcher);

        // run once to disable if empty
        checkFieldsForEmptyValues();

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Intent intent=new Intent(MainActivity.this,ListRendezVousActivity.class);
                String JsonURL = url + login.getText().toString()+"/"+pwd.getText().toString();
                StringRequest obreg = new StringRequest(Request.Method.GET,JsonURL,
                        new Response.Listener<String>(){
                            @Override
                            public void onResponse(String response){
                                try {
                                    id = Long.parseLong(response);
                                    intent.putExtra("idCompte",id);
                                    if(id != 0L){
                                        startActivity(intent);
                                    }
                                } catch (NumberFormatException e) {
                                    erreur.setText("verifier svp!!");
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                erreur.setText(error.getMessage());

                            }
                        }
                );
                requestQueue.add(obreg);
            }
        });


    }
}
