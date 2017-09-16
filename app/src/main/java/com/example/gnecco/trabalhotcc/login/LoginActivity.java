package com.example.gnecco.trabalhotcc.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gnecco.trabalhotcc.MainActivity;
import com.example.gnecco.trabalhotcc.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;


    private final String KEY_APP_PREFERENCES = "login";
    private final String KEY_LOGIN = "login";
    private final String KEY_PASS = "password";
    private final String KEY_STAY = "staysigned";


    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbStaySigned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etSenha);
        cbStaySigned = (CheckBox) findViewById(R.id.cbConnectado);

        ler();

        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (cbStaySigned.isChecked())
                {
                    staySigned();
                }
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, R.string.facebook_cancel,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, R.string.facebook_error,Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

    }

    public void Logar(View view) {
        LoginDAO loginDAO = new LoginDAO(getBaseContext());
        if(loginDAO.IsValid(etUsername.getText().toString(),etPassword.getText().toString()))
        {
            if (cbStaySigned.isChecked())
            {
                staySigned();
            }
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("USERNAME",etUsername.getText().toString());
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(LoginActivity.this, R.string.login_senha_incorretos,Toast.LENGTH_LONG).show();
        }
    }

    private void ler() {
        SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        etUsername.setText(pref.getString(KEY_LOGIN, ""));
        etPassword.setText(pref.getString(KEY_PASS, ""));
        cbStaySigned.setChecked(pref.getBoolean(KEY_STAY, false));
        if (cbStaySigned.isChecked())
        {
            Logar(null);
        }
    }

    private void staySigned() {
        String login = etUsername.getText().toString();
        String senha = etPassword.getText().toString();
        Boolean continuar = cbStaySigned.isChecked();

        SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_LOGIN, login);
        editor.putString(KEY_PASS, senha);
        editor.putBoolean(KEY_STAY, continuar);
        editor.apply();
    }

}
