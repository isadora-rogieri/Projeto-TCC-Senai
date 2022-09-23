package com.tccsenai.apihamburgueria.service;

import com.tccsenai.apihamburgueria.model.Produto;
import com.tccsenai.apihamburgueria.repository.ProdutoRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import util.Conversor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

@Component
public class Diretorio {

    @Autowired
    ProdutoRepository produtoRepository;


   public void salvarBytesImagem(MultipartFile foto, Integer id) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(foto.getBytes()));

        // convert BufferedImage to byte[]
        byte[] bytes = Conversor.toByteArray(bi, "jpg");


        //encode the byte array for display purpose only, optional
        String bytesBase64 = Base64.encodeBase64String(bytes);
        Optional<Produto> produto = produtoRepository.findById(id);
        produto.get().setByteImagem(bytesBase64);
        produtoRepository.save(produto.get());
    }

}