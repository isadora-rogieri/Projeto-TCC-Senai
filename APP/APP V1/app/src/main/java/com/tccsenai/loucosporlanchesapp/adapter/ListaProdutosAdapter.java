package com.tccsenai.loucosporlanchesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tccsenai.loucosporlanchesapp.R;
import com.tccsenai.loucosporlanchesapp.model.Produto;
import com.tccsenai.loucosporlanchesapp.telas.Carrinho;
import com.tccsenai.loucosporlanchesapp.telas.DetalhesProduto;
import com.tccsenai.loucosporlanchesapp.telas.ListaProdutos;
import com.tccsenai.loucosporlanchesapp.telas.TelaAtualizaProduto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ListaProdutosAdapter extends RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>{

    List<Produto> listProduto = new ArrayList<>();
    Context context;
    View view;


    public ListaProdutosAdapter(Context context, List<Produto> listProduto) {
        this.context = context;
        this.listProduto =listProduto;
    }

    @NonNull
    @Override
    public ListaProdutosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListaProdutosAdapter.ViewHolder holder, int position) {

        Produto produto = listProduto.get(position);
        holder.vincula(produto);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detalhesProduto(produto.getId(), position);
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
        Button btAdicionar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imagemItem = itemView.findViewById(R.id.imgProduto);
            this.nomeItem = itemView.findViewById(R.id.txtNomeProduto);
            this.descricaoItem = itemView.findViewById(R.id.txtDescricao);
            this.precoItem = itemView.findViewById(R.id.txtValorProduto);
            this.btAdicionar = itemView.findViewById(R.id.btAdicionar);


        }
         void vincula(Produto produto) {
            this.produto = produto;
             nomeItem.setText(produto.getNome());
             precoItem.setText(String.valueOf(produto.getPreco()));
             descricaoItem.setText(produto.getDescricao());
             imagemItem.setImageBitmap(converteImagem(produto.getByteImagem()));
             btAdicionar.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent it = new Intent(context, Carrinho.class);
                     it.putExtra("id", produto.getId());
                     it.putExtra("nome", produto.getNome());
                     it.putExtra("descricao", produto.getDescricao());
                     it.putExtra("preco", produto.getPreco());
                     it.putExtra("byteImagem", produto.getByteImagem());
                     context.startActivity(it);
                 }
             });
        }
        private Bitmap converteImagem(String imagem){
            byte[] bytes= Base64.decode(imagem,Base64.DEFAULT);
            // Initialize bitmap
            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            // set bitmap on imageView

            return bitmap;
        }

    }

    private void detalhesProduto(int posicao, int id){

        Intent it = new Intent(context, DetalhesProduto.class);
        it.putExtra("id", id);
        it.putExtra("nome", listProduto.get(posicao).getNome());
        it.putExtra("descricao", listProduto.get(posicao).getDescricao());
        it.putExtra("preco", listProduto.get(posicao).getPreco());
        it.putExtra("byteImagem", listProduto.get(posicao).getByteImagem());
        context.startActivity(it);
    }
}

