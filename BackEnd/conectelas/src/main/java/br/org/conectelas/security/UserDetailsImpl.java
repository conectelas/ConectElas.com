package br.org.conectelas.security;

import br.org.conectelas.model.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

    public UserDetailsImpl(UsuarioModel usuario) {
        username = usuario.getEmail();
        password = usuario.getSenha();
        authorities.add(new SimpleGrantedAuthority("USER"));
    }

    public UserDetailsImpl() { };

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
