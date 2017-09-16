package com.example.gnecco.trabalhotcc.splash;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gnecco.trabalhotcc.R;
import com.example.gnecco.trabalhotcc.login.LoginActivity;
import com.example.gnecco.trabalhotcc.login.LoginDAO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        BuscarItemTask task = new BuscarItemTask();
        task.execute();

        carregar();
    }

    private void carregar() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.ivlogoSplash);
        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,
                        LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private class BuscarItemTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {
            try
            {
                URL url = new URL("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                if(connection.getResponseCode() == 200)
                {
                    BufferedReader stream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String linha = "";
                    StringBuilder resposta = new StringBuilder();
                    while((linha = stream.readLine()) != null){
                        resposta.append(linha);
                    }

                    connection.disconnect();

                    return resposta.toString();
                }
            }
            catch ( Exception e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String s){
            if(s != null) {
                try {
                    JSONObject json = new JSONObject(s);
                    String username = json.getString("usuario");
                    String password = json.getString("senha");
                    LoginDAO login = new LoginDAO(getBaseContext());
                    login.insert(username,password);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
            else {
                Toast.makeText(SplashActivity.this, "Error",Toast.LENGTH_LONG).show();
            }
        }
    }
}