package com.example.gnecco.trabalhotcc.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.gnecco.trabalhotcc.R;

import java.util.List;

public class ListProductActivity extends AppCompatActivity {

    private RegisterDAO CadastroDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        FrameLayout fLayout = (FrameLayout) findViewById(R.id.activity_to_do);
        RecyclerView rvCadastro = (RecyclerView) findViewById(R.id.listaCadastro);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvCadastro.setLayoutManager(linearLayoutManager);
        rvCadastro.setHasFixedSize(true);
        CadastroDB = new RegisterDAO(this);

        List<RegisterModel> listarProdutos = CadastroDB.retornarListaProdutos();

        if (listarProdutos.size() > 0) {

            rvCadastro.setVisibility(View.VISIBLE);
            RegisterAdapter mAdapter = new RegisterAdapter(this, listarProdutos);
            rvCadastro.setAdapter(mAdapter);

        } else {

            rvCadastro.setVisibility(View.GONE);
            Toast.makeText(this, "Nenhum Produto Adicionado.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (CadastroDB != null) {
            CadastroDB.close();
        }
    }
}
