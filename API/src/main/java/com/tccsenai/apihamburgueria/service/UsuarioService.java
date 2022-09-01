package com.tccsenai.apihamburgueria.service;

import com.tccsenai.apihamburgueria.model.Usuario;
import com.tccsenai.apihamburgueria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;


    public Usuario cadastroNovoUser(Usuario usuario){
      this.usuarioRepository.save(usuario);
      return usuario;
    }

    public List<Usuario> litarTodos() {
        return usuarioRepository.findAll();
    }
    public void atualizarUser(Integer id, Usuario usuario){
        Usuario usuario1 = this.usuarioRepository.findOneById(id);
        usuario1.setNome(usuario.getNome());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setCep(usuario.getCep());
        usuario1.setEndereco(usuario.getEndereco());
        usuario1.setBairro(usuario.getBairro());
        usuario1.setCidade(usuario.getCidade());
        usuario1.setComplemento(usuario.getComplemento());
        usuario1.setNumero(usuario.getNumero());
        usuario1.setTelefone(usuario.getTelefone());

        this.usuarioRepository.save(usuario1);
    }

    public void deletarUser(Integer id){
        Usuario usuario = this.usuarioRepository.findOneById(id);
        this.usuarioRepository.delete(usuario);
    }

    public Usuario buscaUsuarioById(Integer id) {
        return this.usuarioRepository.findOneById(id);
    }
}
