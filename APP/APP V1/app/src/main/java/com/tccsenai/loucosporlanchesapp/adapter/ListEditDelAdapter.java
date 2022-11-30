package com.tccsenai.loucosporlanchesapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tccsenai.loucosporlanchesapp.R;
import com.tccsenai.loucosporlanchesapp.model.Produto;
import com.tccsenai.loucosporlanchesapp.telas.TelaAtualizaProduto;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListEditDelAdapter extends RecyclerView.Adapter<ListEditDelAdapter.ViewHolder>{
        List<Produto> listProduto = new ArrayList<>();
        Context context;
        View view;
        AlertDialog alert;

    public ListEditDelAdapter(Context context, List<Produto> listProduto) {
        this.context = context;
        this.listProduto =listProduto;
        }

@NonNull
@Override
public ListEditDelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_edititem, parent, false);
        return new ViewHolder(view);
        }


@Override
public void onBindViewHolder(@NonNull ListEditDelAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

       Produto produto = listProduto.get(position);

        holder.vincula(produto);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                montaAlert("Excluir item", "Você tem certeza que gostaria excluir ?", produto.getId() , position);
            }
        });
}


@Override
public int getItemCount() {
        return listProduto.size();
        }


class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imagemItem;
    TextView nomeItem, descricaoItem, precoItem;
    private Produto produto;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imagemItem = itemView.findViewById(R.id.imgProdutoed);
        this.nomeItem = itemView.findViewById(R.id.txtNomeProdutoed);
        this.descricaoItem = itemView.findViewById(R.id.txtDescricaoed);
        this.precoItem = itemView.findViewById(R.id.txtValorProdutoed);


    }
    void vincula(Produto produto) {
        this.produto = produto;
        int id = produto.getId();
        nomeItem.setText(produto.getNome());
        precoItem.setText(String.valueOf(produto.getPreco()));
        descricaoItem.setText(produto.getDescricao());
        imagemItem.setImageBitmap(converteImagem(produto.getByteImagem()));
    }
    private Bitmap converteImagem(String imagem){
        byte[] bytes= Base64.decode(imagem,Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        // set bitmap on imageView

        return bitmap;
    }

}
    //alert ao clicar no item
    private void montaAlert(String titulo, String mensagem, int id, int posicao){

        AlertDialog.Builder configAlert = new AlertDialog.Builder(context);
        configAlert.setTitle(titulo);
        configAlert.setMessage(mensagem);


        configAlert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                removerProduto(id, posicao);

            }
        });

        configAlert.setNeutralButton("Editar item", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                atualizarProduto(posicao,id);
            }
        });

        configAlert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alert.cancel();
            }
        });

        alert = configAlert.create();
        alert.show();
    }
    //deletar um produto
    private void removerProduto(int id, int posicao){

        String url = "http://192.168.43.232:8080/api/hamburgueria/produtos/" + id;


        JsonObjectRequest configRequisicao = new JsonObjectRequest(Request.Method.DELETE,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getInt("code") == 200){

                                listProduto.remove(posicao);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Deletado com sucesso!", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Erro ao deletar", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(context, "Erro ao acessar API", Toast.LENGTH_LONG).show();
                    }
                }
        );

        RequestQueue requisicao = Volley.newRequestQueue(context);
        requisicao.add(configRequisicao);
    }
    //enviando dados do produto para tela de editar
    private void atualizarProduto(int posicao, int id){

        Intent it = new Intent(context, TelaAtualizaProduto.class);
        it.putExtra("id", id);
        it.putExtra("nome", listProduto.get(posicao).getNome());
        it.putExtra("descricao", listProduto.get(posicao).getDescricao());
        it.putExtra("preco", listProduto.get(posicao).getPreco());
        it.putExtra("byteImagem", listProduto.get(posicao).getByteImagem());
        context.startActivity(it);
    }


}
