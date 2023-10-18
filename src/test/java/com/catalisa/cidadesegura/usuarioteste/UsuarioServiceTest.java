package com.catalisa.cidadesegura.usuarioteste;

import com.catalisa.cidadesegura.domain.dto.response.UsuarioResponse;
import com.catalisa.cidadesegura.domain.exception.UsuarioNaoEncontradoException;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import com.catalisa.cidadesegura.domain.repository.UsuarioRepository;
import com.catalisa.cidadesegura.domain.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListar() {

        List<UsuarioModel> usuarios = new ArrayList<>();

        usuarios.add(new UsuarioModel("Usuário1", "usuario1@example.com", "user1", "senha1"));
        usuarios.add(new UsuarioModel("Usuário2", "usuario2@example.com", "user2", "senha2"));

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<UsuarioModel> result = usuarioService.listar();
        assertNotNull(result);
        assertEquals(usuarios.size(), result.size());

        verify(usuarioRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void testBuscarPorId() {
        Long idUsuario = 1L;
        UsuarioModel usuario = new UsuarioModel("Usuário1", "usuario1@example.com", "user1", "senha1");

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.of(usuario));

        UsuarioModel result = usuarioService.buscarPorId(idUsuario);

        assertNotNull(result);
        assertEquals(usuario, result);

        verify(usuarioRepository, Mockito.times(1)).findById(idUsuario);
    }

    @Test
    public void testBuscarPorIdUsuarioNaoEncontrado() {
        Long idUsuario = 2L;

        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.buscarPorId(idUsuario));

        verify(usuarioRepository, Mockito.times(1)).findById(idUsuario);
    }


    @Test
    public void testSalvarUsuarioComSucesso() {
        UsuarioModel usuario = new UsuarioModel("Novo Usuário", "novo@example.com", "novo_user", "nova_senha");
        UsuarioModel usuarioSalvo = new UsuarioModel("Novo Usuário", "novo@example.com", "novo_user", "nova_senha");
        usuarioSalvo.setIdUsuario(1L); // Simulando que o usuário foi salvo no banco de dados e recebeu um ID

       when(usuarioRepository.save(usuario)).thenReturn(usuarioSalvo);

        ResponseEntity<?> response = usuarioService.salvar(usuario, HttpStatus.OK);

      assertEquals(HttpStatus.OK, response.getStatusCode());

 UsuarioResponse usuarioSalvoNaResposta = (UsuarioResponse) response.getBody();
        assert usuarioSalvoNaResposta != null;
        assertEquals(usuarioSalvo.getIdUsuario(), usuarioSalvoNaResposta.getIdUsuario());
        assertEquals(usuarioSalvo.getNomeUsuario(), usuarioSalvoNaResposta.getNomeUsuario());
        assertEquals(usuarioSalvo.getEmailUsuario(), usuarioSalvoNaResposta.getEmailUsuario());

    }


    @Test
    public void testSalvarUsuarioComConflitoDeEmail() {
        UsuarioModel usuario = new UsuarioModel("Usuário Existente", "usuario1@example.com", "user1", "senha1");

        when(usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario())).thenReturn(Optional.of(usuario));

        ResponseEntity<?> response = usuarioService.salvar(usuario, HttpStatus.OK);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Já existe usuário cadastrado com mesmo e-mail..", response.getBody());
    }


    @Test
    public void testSalvarUsuarioComConflitoDeUsername() {
        UsuarioModel usuario = new UsuarioModel("Usuário Existente", "novo@example.com", "user1", "senha1");

        when(usuarioRepository.findByUsername(usuario.getUsername())).thenReturn(usuario);

        ResponseEntity<?> response = usuarioService.salvar(usuario, HttpStatus.OK);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Já existe usuário cadastrado com mesmo username.", response.getBody());
    }

    @Test
    public void testSalvarUsuarioComConflitoDePassword() {
        UsuarioModel usuario = new UsuarioModel("Usuário Existente", "novo@example.com", "user1", "senha1");

        when(usuarioRepository.findByPassword(usuario.getPassword())).thenReturn(Optional.of(usuario));

        ResponseEntity<?> response = usuarioService.salvar(usuario, HttpStatus.OK);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Já existe usuário cadastrado com mesma senha.", response.getBody());
    }


}
