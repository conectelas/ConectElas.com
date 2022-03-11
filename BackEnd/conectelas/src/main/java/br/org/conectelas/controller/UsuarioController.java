package br.org.conectelas.controller;

import br.org.conectelas.model.UsuarioModel;
import br.org.conectelas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping
public class UsuarioController {

    @Autowired
    private UserService service;

//    @GetMapping("/cadastrar")
//    public String hello() {
//        return "hello";
//    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioModel>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioModel> getById(@PathVariable long id) {
        var usuario = service.getById(id);
        return usuario != null ? ResponseEntity.status(HttpStatus.OK).body(service.getById(id))
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/logar")
    public ResponseEntity<UsuarioModel> logar(@RequestBody UsuarioModel usuarioInfo) {
        var usuario = service.logar(usuarioInfo);
        return usuario
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioModel> cadastrar(@RequestBody UsuarioModel usuario) {
        // refatorar
        return service.cadastrar(usuario);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<UsuarioModel> atualizar(@RequestBody UsuarioModel usuario) {
        return service.atualizar(usuario)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletar(@RequestBody UsuarioModel usuario) {
        return ResponseEntity.status(service.deletar(usuario)).build();
    }
}
