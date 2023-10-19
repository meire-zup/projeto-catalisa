package com.catalisa.cidadesegura.postagemTest;

import com.catalisa.cidadesegura.domain.model.CidadesModel;
import com.catalisa.cidadesegura.domain.model.PostagemModel;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import com.catalisa.cidadesegura.domain.repository.PostagemRepository;
import com.catalisa.cidadesegura.domain.service.PostagemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostagemServiceTest {
    @InjectMocks
    private PostagemService postagemService;

    @Mock
    private PostagemRepository postagemRepository;

    @Mock
    private PostagemModel postagemModel;

    public PostagemServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {

        List<PostagemModel> postagens = new ArrayList<>();
        postagens.add(postagemModel);

        when(postagemRepository.findAll()).thenReturn(postagens);

        List<PostagemModel> result = postagemService.listar();

        Assertions.assertEquals(postagens.size(), result.size());
        Assertions.assertEquals(postagens.get(0), result.get(0));

        verify(postagemRepository, times(1)).findAll();
    }

    @Test
    void testCadastrar() {

        when(postagemRepository.save(postagemModel)).thenReturn(postagemModel);

        PostagemModel result = postagemService.cadastrar(postagemModel);

        Assertions.assertEquals(postagemModel, result);

        verify(postagemRepository, times(1)).save(postagemModel);
    }

    @Test
    void testListarPorId() {
        Long idPostagem = 1L;
        PostagemModel postagemModel = new PostagemModel();
        postagemModel.setIdPostagem(idPostagem);
        Optional<PostagemModel> postagemOptional = Optional.of(postagemModel);


        when(postagemRepository.findById(idPostagem)).thenReturn(postagemOptional);

        Optional<PostagemModel> result = postagemService.listarPorId(idPostagem);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(idPostagem, result.get().getIdPostagem());

        verify(postagemRepository, times(1)).findById(idPostagem);
    }

    @Test
    void testBuscarPostagemPorIdEUsername() {
        Long idPostagem = 1L;
        UsuarioModel username = new UsuarioModel();
        PostagemModel postagemModel = new PostagemModel();
        postagemModel.setIdPostagem(idPostagem);
        postagemModel.setUsuario(username);
        Optional<PostagemModel> postagemOptional = Optional.of(postagemModel);

        when(postagemRepository.findByIdAndUsername(idPostagem, username.getUsername())).thenReturn(postagemOptional);

        Optional<PostagemModel> result = postagemService.buscarPostagemPorIdEUsername(idPostagem, username.getUsername());

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(idPostagem, result.get().getIdPostagem());
        Assertions.assertEquals(username, result.get().getUsuario());

        verify(postagemRepository, times(1)).findByIdAndUsername(idPostagem, username.getUsername());
    }

    @Test
    void testDeletarPostagem() {
        Long idPostagem = 1L;

       postagemService.deletarPostagem(idPostagem);

        verify(postagemRepository, times(1)).deleteById(idPostagem);
    }

    @Test
    void testSalvar() {
        PostagemModel postagemModel = new PostagemModel();

        when(postagemRepository.save(postagemModel)).thenReturn(postagemModel);

        PostagemModel result = postagemService.salvar(postagemModel);

        Assertions.assertEquals(postagemModel, result);

        verify(postagemRepository, times(1)).save(postagemModel);
    }

    @Test
    void testBuscarPorCidade() {
        String cidade = "ExemploCidade";
        List<PostagemModel> postagens = new ArrayList<>();


        when(postagemRepository.findByCidade(cidade)).thenReturn(postagens);


        List<PostagemModel> result = postagemService.buscarPorCidade(cidade);


        Assertions.assertEquals(postagens, result);


        verify(postagemRepository, times(1)).findByCidade(cidade);
    }

    @Test
    void testBuscarPorBairro() {
        String bairro = "ExemploBairro";
        List<PostagemModel> postagens = new ArrayList<>();

        when(postagemRepository.findByBairro(bairro)).thenReturn(postagens);


        List<PostagemModel> result = postagemService.buscarPorBairro(bairro);


        Assertions.assertEquals(postagens, result);


        verify(postagemRepository, times(1)).findByBairro(bairro);
    }

}
