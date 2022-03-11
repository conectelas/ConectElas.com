package br.org.conectelas.service;

import br.org.conectelas.model.UsuarioModel;
import br.org.conectelas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private UserDetailsServiceImpl service;
    public ResponseEntity<UsuarioModel> cadastrar(UsuarioModel usuario) {
        String email = usuario.getEmail();
        String senha = usuario.getSenha();
        var criptografar = new BCryptPasswordEncoder();
        return repo.findUserByEmail(email)
                        .map(resp -> ResponseEntity.status(HttpStatus.CONFLICT).body(resp))
                        .orElseGet(() -> {
                            usuario.setSenha(criptografar.encode(senha));
                            return ResponseEntity.created(URI.create("/cadastrar")).body(repo.save(usuario));
                        });
    }

    public Optional<UsuarioModel> logar(UsuarioModel usuario) {
        return repo.findUserByEmail(usuario.getEmail())
            .map(resp -> {
                boolean passwordMatches = passwordEncoder.matches(usuario.getSenha(), resp.getSenha());
                return passwordMatches ? Optional.of(usuario) : Optional.<UsuarioModel>empty();
            })
            .orElseThrow(() -> new UsernameNotFoundException("Não existe conta com esse email."));
    }

    public Optional<UsuarioModel> atualizar(UsuarioModel usuario) {
        boolean usuarioExiste = repo.findUserByEmail(usuario.getEmail()).isPresent();
        if (usuarioExiste) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return Optional.of(repo.save(usuario));
        } else {
            return Optional.empty();
        }
    }

    public List<UsuarioModel> getAll() {
        return repo.findAll();
    }

    public UsuarioModel getById(long id) {
        //TODO: continuar achar por id e metodo delete
        var usuario = repo.findById(id);
        // equivalente a return usuario.isPresent() ? usuario.get() : null;
        return usuario.orElse(null);
    }

    public HttpStatus deletar(UsuarioModel usuario) {
        // mudar depois?
        var info = new UserInfo(usuario).getInfo();
        return repo.findUserByEmail(info.get(0))
                .map(resp -> {
                    boolean passwordMatch = passwordEncoder.matches(info.get(1), resp.getSenha());
                    if (passwordMatch) {
                        repo.delete(resp);
                        return HttpStatus.OK;
                    } else {
                        return HttpStatus.UNAUTHORIZED;
                    }
                }).orElseGet(() -> HttpStatus.NOT_FOUND);
    }
}

class UserInfo {

    private final String email;
    private final String senha;

    UserInfo(UsuarioModel usuario) {
        email = usuario.getEmail();
        senha = usuario.getSenha();
    }

    public List<String> getInfo() {
        return List.of(email, senha);
    }
}

