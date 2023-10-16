package com.catalisa.cidadesegura.postagemTest;

import com.catalisa.cidadesegura.domain.model.CidadesModel;
import com.catalisa.cidadesegura.domain.model.PostagemModel;
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
}
