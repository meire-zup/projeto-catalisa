package com.catalisa.cidadesegura.domain.model;

import com.catalisa.cidadesegura.domain.repository.UsuarioRepository;
import com.catalisa.cidadesegura.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "usuario")
@NoArgsConstructor
public class UsuarioModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false)
    @NotBlank
    private String nomeUsuario;

    @Column(nullable = false)
    @NotBlank
    @Email
    private String emailUsuario;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles;

    public UsuarioModel(String nomeUsuario, String emailUsuario, String username, String password) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.username = username;
        this.password = password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public String getUsername(){
        return this.username;
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
    public boolean existeOutroUsuarioComMesmoEmail(UsuarioRepository repository){

        if(repository.findByEmailUsuario(this.getEmailUsuario()).isPresent()) {

            return true;
        }
        return false;
    }
    public boolean existeOutroUsuarioComMesmoPassword(UsuarioRepository repository){

        if(repository.findByPassword(this.getPassword()).isPresent()) {

            return true;
        }
        return false;
    }
    public boolean existeOutroUsuarioComMesmoUsername(UsuarioRepository repository){

        if(repository.findByUsername(this.getUsername()).isPresent()) {

            return true;
        }
        return false;
    }

}
