package com.tccsenai.apihamburgueria.controller;

import com.tccsenai.apihamburgueria.service.Diretorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/imagens")
@CrossOrigin
public class imagemController {

    @Autowired
    Diretorio diretorio;

    @PostMapping
    public  void salvartesteImagem(@RequestParam MultipartFile imagem, @RequestParam Integer produto_id) throws IOException {
        diretorio.salvarBytesImagem(imagem, produto_id);
    }

}
