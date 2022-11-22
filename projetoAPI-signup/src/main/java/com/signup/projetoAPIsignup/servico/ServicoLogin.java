package com.signup.projetoAPIsignup.servico;

import com.signup.projetoAPIsignup.modelo.Mensagem;
import com.signup.projetoAPIsignup.modelo.Usuario;
import com.signup.projetoAPIsignup.repositorio.Repositorio;
import com.signup.projetoAPIsignup.usuarioCheck.UsuarioCredenciais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServicoLogin {
    @Autowired
    private Repositorio banco;
    @Autowired
    private Mensagem mensagem;

    public ResponseEntity<?> checkCredentials(UsuarioCredenciais usuario) {
        if (banco.existsByNome(usuario.getNome()) && banco.existsBySenha(usuario.getSenha())) {
            mensagem.setMensagem("Usuário encontrado!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } else {
            mensagem.setMensagem("Usuário não encontrado!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
    }
}
