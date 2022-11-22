package com.signup.projetoAPIsignup.controle;

import com.signup.projetoAPIsignup.modelo.Usuario;
import com.signup.projetoAPIsignup.servico.Servico;
import com.signup.projetoAPIsignup.servico.ServicoLogin;
import com.signup.projetoAPIsignup.usuarioCheck.UsuarioCredenciais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class Controle {

    @Autowired
    Servico servico;

    @Autowired
    ServicoLogin servicoLogin;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        return servico.cadastrar(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioCredenciais id) {
        return servicoLogin.checkCredentials(id);
    }
}
