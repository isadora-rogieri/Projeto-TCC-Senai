package com.tccsenai.apihamburgueria.service;

import com.tccsenai.apihamburgueria.model.Produto;
import com.tccsenai.apihamburgueria.repository.ProdutoRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import util.Conversor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class Diretorio {

    @Autowired
    ProdutoRepository produtoRepository;

    @Value("${produto.diretorio.raiz}")
    private String raiz;

    @Value("${produto.diretorio.diretorio-imagens}")
    private String diretorioImagens;

   public void salvarImagem(MultipartFile foto, Integer id) {
        this.salvar(this.diretorioImagens, foto, id);
    }

   public void salvarBytesImagem(MultipartFile foto, Integer id) throws IOException {
        //BufferedImage bi = ImageIO.read(new File(foto.toString()));
        BufferedImage bi = ImageIO.read(new File(this.diretorioImagens));

        // convert BufferedImage to byte[]
        byte[] bytes = Conversor.toByteArray(bi, "jpg");

        //encode the byte array for display purpose only, optional
        String bytesBase64 = Base64.encodeBase64String(bytes);
        Optional<Produto> produto = produtoRepository.findById(id);
        produto.get().setByteImagem(bytes.toString());
        produtoRepository.save(produto.get());

    }


    public void salvar(String diretorio, MultipartFile arquivo, Integer id) {
        Path diretorioPath = Paths.get(this.raiz, diretorio);
        Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());

        try {

            Optional<Produto> produto = produtoRepository.findById(id);
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());

            // rename a file in the same directory
            Path newPath = arquivoPath.resolveSibling(produto.get().getNome() + ".jpg");

            // Get the file
            File f = new File(newPath.toString());
            if (f.exists()) {
                f.delete();
            }
            Files.createDirectories(Paths.get("pathTo"));

            Files.move(arquivoPath, newPath);
            produto.get().setCaminhoImagem((newPath.toString()));
            BufferedImage bi = ImageIO.read(new File(newPath.toString()));
            byte[] bytes = Conversor.toByteArray(bi, "jpg");

            //encode the byte array for display purpose only, optional
            String bytesBase64 = Base64.encodeBase64String(bytes);
            produto.get().setByteImagem(bytesBase64);

            produtoRepository.save(produto.get());

        } catch (IOException e) {
            throw new RuntimeException("Problems trying to save file.", e);
        }
    }

    public void getImagem(Integer id) {
        Produto produto= produtoRepository.findById(id).orElseThrow();
        Conversor.convertStringToImage(produto.getByteImagem());

   }
}