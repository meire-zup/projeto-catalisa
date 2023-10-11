package com.catalisa.cidadesegura.domain.model;

import com.catalisa.cidadesegura.domain.repository.UsuarioRepository;
import com.catalisa.cidadesegura.security.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public UsuarioModel(String nomeUsuario, String emailUsuario, String username, String password) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.username = username;
        this.password = password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(this.role == RoleEnum.ADMIN) {

            return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("USER"));

        }

        else return List.of(new SimpleGrantedAuthority("USER"));
    }



    @Override // aqui retorna o login do usuário
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
    public boolean existeOutroUsuarioComMesmoUsername(UsuarioRepository repository) {
        UserDetails userDetails = repository.findByUsername(this.getUsername());
        return userDetails != null && userDetails.isEnabled();
    }
}
