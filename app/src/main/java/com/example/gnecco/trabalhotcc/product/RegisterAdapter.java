package com.example.gnecco.trabalhotcc.product;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gnecco.trabalhotcc.R;

import java.util.List;

public class RegisterAdapter extends RecyclerView.Adapter<RegisterViewHolder> {

    private Context context;
    private List<RegisterModel> listaCadastro;
    private RegisterDAO cadastroDB;

    public RegisterAdapter(Context context, List<RegisterModel> listaCadastro) {

        this.context = context;
        this.listaCadastro = listaCadastro;
        cadastroDB = new RegisterDAO(context);
    }

    @Override
    public RegisterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.register_lista, parent, false);
        return new RegisterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RegisterViewHolder holder, int position) {

        final RegisterModel singleProduct = listaCadastro.get(position);
        holder.modelo.setText(singleProduct.getModelo());
        holder.editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarCadastro(singleProduct);
            }
        });
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastroDB.deleteCadastro(singleProduct.getId());
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCadastro.size();
    }

    private void editarCadastro(final RegisterModel cadastro){

        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.activity_register, null);
        final EditText etModelo = (EditText)subView.findViewById(R.id.etModelo);
        final EditText etValor = (EditText)subView.findViewById(R.id.etValor);
        final EditText etAno = (EditText)subView.findViewById(R.id.etAno);
        final Button btCadastrar = (Button)subView.findViewById(R.id.btCadastrar);

        btCadastrar.setVisibility(View.GONE);

        if(cadastro != null){
            etModelo.setText(cadastro.getModelo());
            etValor.setText(String.valueOf(cadastro.getValor()));
            etAno.setText(String.valueOf(cadastro.getAno()));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Editar Item");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("Edtar Item", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String modelo = etModelo.getText().toString();
                final String valor = etValor.getText().toString();
                final String ano = etAno.getText().toString();
                if(TextUtils.isEmpty(modelo) || TextUtils.isEmpty(valor) || TextUtils.isEmpty(ano)){
                    Toast.makeText(context, "Por favor verificar todos os campos", Toast.LENGTH_LONG).show();
                }
                else{

                    cadastroDB.updateCadastro(new RegisterModel(cadastro.getId(), modelo, valor, ano));
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
}
