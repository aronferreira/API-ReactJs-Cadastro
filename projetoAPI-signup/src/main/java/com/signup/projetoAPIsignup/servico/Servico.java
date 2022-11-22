package com.signup.projetoAPIsignup.servico;

import com.signup.projetoAPIsignup.modelo.Mensagem;
import com.signup.projetoAPIsignup.modelo.Usuario;
import com.signup.projetoAPIsignup.repositorio.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Servico {
    @Autowired
    private Repositorio banco;

    @Autowired
    private Mensagem mensagem;

    public ResponseEntity<?> cadastrar(Usuario usuario) {
        if (usuario.getSenha().equals("")) {
            mensagem.setMensagem("Erro, por favor defina uma senha.");
            return new ResponseEntity<Mensagem>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            if (usuario.getEmpreendedor()) {
                if (usuario.getCnpj() == null) {
                    mensagem.setMensagem("Erro, o usuário empreendedor precisa definir um CNPJ.");
                    return new ResponseEntity<Mensagem>(mensagem, HttpStatus.BAD_REQUEST);
                } else if (banco.countByCnpj(usuario.getCnpj()) >= 1) {
                    mensagem.setMensagem("Este CNPJ já foi cadastrado.");
                    return new ResponseEntity<Mensagem>(mensagem, HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<Usuario>(banco.save(usuario), HttpStatus.CREATED);
                }
            } else {
                return new ResponseEntity<Usuario>(banco.save(usuario), HttpStatus.CREATED);
            }
        }
}

}