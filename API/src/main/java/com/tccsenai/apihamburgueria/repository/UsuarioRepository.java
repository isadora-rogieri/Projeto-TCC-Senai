package com.tccsenai.apihamburgueria.repository;

import com.tccsenai.apihamburgueria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {

    Usuario findOneById(Integer id);


}
