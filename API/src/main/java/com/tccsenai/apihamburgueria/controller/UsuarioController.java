package com.tccsenai.apihamburgueria.controller;

import com.tccsenai.apihamburgueria.model.Usuario;
import com.tccsenai.apihamburgueria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hamburgueria/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listaUsuarios(){
        return usuarioService.litarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscaUsuarioById(Integer id){
        return usuarioService.buscaUsuarioById(id);
    }
    @PostMapping
    public ResponseEntity salvarUsuario(@RequestBody Usuario usuario){
        this.usuarioService.cadastroNovoUser(usuario);
        ResponseEntity response = new ResponseEntity("Usuario criado", HttpStatus.CREATED);
        return response;
    }
    @PutMapping("/{id}")
    public ResponseEntity alterarUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario){
        this.usuarioService.atualizarUser(id,usuario);
        ResponseEntity response = new ResponseEntity("Usuario alterado", HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarUsuario(@PathVariable("id") Integer id){
        this.usuarioService.deletarUser(id);
        ResponseEntity response = new ResponseEntity("Usuario Deletado", HttpStatus.OK);
        return response;
    }


}

