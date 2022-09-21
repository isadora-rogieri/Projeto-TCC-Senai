package com.tccsenai.loucosporlanchesapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tccsenai.loucosporlanchesapp.R;
import com.tccsenai.loucosporlanchesapp.model.Produto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

public class ListaProdutosAdapter extends RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>{

    List<Produto> listProduto;


    public ListaProdutosAdapter(List<Produto> listProduto) {
        this.listProduto = listProduto;
    }

    @NonNull
    @Override
    public ListaProdutosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaProdutosAdapter.ViewHolder holder, int position) {

        Produto produto = listProduto.get(position);
        holder.vincula(produto);
    }

    @Override
    public int getItemCount() {
        return listProduto.size();
    }
    private Bitmap converteImagem(String imagem){
        byte[] bytes= Base64.decode(imagem,Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        // set bitmap on imageView

        return bitmap;
    }





    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagemItem;
        TextView nomeItem, descricaoItem, precoItem;
        private Produto produto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imagemItem = itemView.findViewById(R.id.imageView);
            this.nomeItem = itemView.findViewById(R.id.txtNomeProduto);
            this.descricaoItem = itemView.findViewById(R.id.txtDescricao);
            this.precoItem = itemView.findViewById(R.id.txtValorProduto);


        }
         void vincula(Produto produto) {
            this.produto = produto;
             nomeItem.setText(produto.getNome());
             precoItem.setText(String.valueOf(produto.getPreco()));
             descricaoItem.setText(produto.getDescricao());
             imagemItem.setImageBitmap(converteImagem(produto.getByteImagem()));
        }

    }
}

