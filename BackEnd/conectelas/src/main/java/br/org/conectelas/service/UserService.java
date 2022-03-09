package br.org.conectelas.service;

import br.org.conectelas.model.UsuarioModel;
import br.org.conectelas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class UserService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private UserDetailsServiceImpl service;

    public ResponseEntity<String> cadastrar(UsuarioModel usuario) {
        return ResponseEntity.created(URI.create("/registrar")).body(usuario.getEmail());
    }
}
