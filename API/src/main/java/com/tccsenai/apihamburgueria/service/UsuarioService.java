package com.tccsenai.apihamburgueria.service;


import com.tccsenai.apihamburgueria.dto.UsuarioDto;
import com.tccsenai.apihamburgueria.enums.TipoUsuario;
import com.tccsenai.apihamburgueria.model.Usuario;
import com.tccsenai.apihamburgueria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.TrataStrings;

import java.text.ParseException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;


    public Usuario cadastroNovoUsuario(UsuarioDto usuarioDto) throws ParseException {
        var usuario = fromDto(usuarioDto);
      this.usuarioRepository.save(usuario);
      return usuario;
    }
    private Usuario fromDto(UsuarioDto usuarioDto) throws ParseException {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.getNome());
        usuario.setCep(usuarioDto.getCep());
        usuario.setCpf(TrataStrings.removePontuacao(usuarioDto.getCpf()));
        usuario.setCidade(usuarioDto.getCidade());
        usuario.setBairro(usuarioDto.getBairro());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setTelefone(usuarioDto.getTelefone());
        usuario.setDataNascimento(TrataStrings.converteData(usuarioDto.getDataNascimento()));
        usuario.setSenha(TrataStrings.criptografaSenha(usuarioDto.getSenha()));
        usuario.setNome(usuarioDto.getNome());
        usuario.setNumero(usuarioDto.getNumero());
        usuario.setEndereco(usuarioDto.getEndereco());
        usuario.setTipoUsuario(TipoUsuario.CLIENTE);
        usuario.setComplemento(usuarioDto.getComplemento());

        return usuario;
    }


    public List<Usuario> litarTodos() {
        return usuarioRepository.findAll();
    }

    public void atualizarUsuario(Integer id, UsuarioDto usuarioDto){
        Usuario usuario1 = this.usuarioRepository.findOneById(id);
        usuario1.setNome(usuarioDto.getNome());
        usuario1.setEmail(usuarioDto.getEmail());
        usuario1.setCep(usuarioDto.getCep());
        usuario1.setEndereco(usuarioDto.getEndereco());
        usuario1.setBairro(usuarioDto.getBairro());
        usuario1.setCidade(usuarioDto.getCidade());
        usuario1.setComplemento(usuarioDto.getComplemento());
        usuario1.setNumero(usuarioDto.getNumero());
        usuario1.setTelefone(usuarioDto.getTelefone());

        this.usuarioRepository.save(usuario1);
    }

    public void deletarUsuario(Integer id){
        Usuario usuario = this.usuarioRepository.findOneById(id);
        this.usuarioRepository.delete(usuario);
    }

    public Usuario buscaUsuarioById(Integer id) {
        return this.usuarioRepository.findOneById(id);
    }


    public Usuario login (String cpfUsuario, String senha){
        Usuario u = this.usuarioRepository.findOneByCpf(cpfUsuario);
        if (cpfUsuario.equals(u.getCpf()) && TrataStrings.criptografaSenha(senha).equals(u.getSenha())){
            return u;
        }else
            return null;

    }
}
