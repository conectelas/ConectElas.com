package br.org.conectelas.controller;

import br.org.conectelas.model.UsuarioModel;
import br.org.conectelas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
public class UsuarioController {

    @Autowired
    private UserService service;

    @GetMapping("/cadastrar")
    public String hello() {
        return "hello";
    }

    @GetMapping
    public String teste() {
        return "Rota de teste, apenas acessível se estiver logado!!";
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(UsuarioModel usuario) {
        return service.cadastrar(usuario);
    }
}
