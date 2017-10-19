package com.halgo.clientcab;

import android.app.Activity;
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


import com.android.volley.RequestQueue;

import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

    TextView erreur;
    AppCompatEditText login;
    AppCompatEditText pwd;
    AppCompatButton btn;
    RequestQueue requestQueue;
    TextInputLayout loginLayout;
    TextInputLayout pwdLayout;




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


    }
}
