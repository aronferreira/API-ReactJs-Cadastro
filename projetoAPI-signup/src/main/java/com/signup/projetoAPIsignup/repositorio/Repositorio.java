package com.signup.projetoAPIsignup.repositorio;

import com.signup.projetoAPIsignup.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends CrudRepository<Usuario, Integer> {

    int countByCnpj(int cnpj);

    boolean existsByNome(String nome);

    boolean existsBySenha(String senha);
}
