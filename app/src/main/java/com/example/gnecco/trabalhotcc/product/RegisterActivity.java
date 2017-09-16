package com.example.gnecco.trabalhotcc.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gnecco.trabalhotcc.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etModelo;
    private EditText etValor;
    private EditText etAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etModelo = (EditText) findViewById(R.id.etModelo);
        etValor = (EditText) findViewById(R.id.etValor);
        etAno = (EditText) findViewById(R.id.etAno);
    }

    public void Cadastrar(View view) {

        String modelo = etModelo.getText().toString();
        String valor = etValor.getText().toString();
        String ano = etAno.getText().toString();

        if(TextUtils.isEmpty(modelo) || TextUtils.isEmpty(valor) || TextUtils.isEmpty(ano)){
            Toast.makeText(getBaseContext(), "Por favor verificar todos os campos", Toast.LENGTH_LONG).show();
        }
        else
        {
            RegisterDAO registerDAO = new RegisterDAO(getBaseContext());
            long cadastro = registerDAO.insert(modelo,valor,ano);
            Toast.makeText(RegisterActivity.this,"Produto Adicionado com Sucesso!",Toast.LENGTH_LONG).show();
            etModelo.setText("");
            etAno.setText("");
            etValor.setText("");
        }
    }
}