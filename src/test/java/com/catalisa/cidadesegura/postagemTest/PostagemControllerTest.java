//package com.catalisa.cidadesegura.postagemTest;
//
//import com.catalisa.cidadesegura.api.controller.PostagemController;
//import com.catalisa.cidadesegura.api.mapper.postagem.PostagemMapperAssembler;
//import com.catalisa.cidadesegura.api.mapper.usuario.UsuarioMapperDisassembler;
//import com.catalisa.cidadesegura.domain.dto.response.PostagemResponse;
//import com.catalisa.cidadesegura.domain.model.CidadesModel;
//import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
//import com.catalisa.cidadesegura.domain.model.PostagemModel;
//import com.catalisa.cidadesegura.domain.model.UsuarioModel;
//import com.catalisa.cidadesegura.domain.repository.UsuarioRepository;
//import com.catalisa.cidadesegura.domain.service.CidadeService;
//import com.catalisa.cidadesegura.domain.service.PostagemService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import org.springframework.http.MediaType;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(PostagemController.class)
//public class PostagemControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private PostagemModel postagemModel;
//
//    @Mock
//    private UsuarioModel usuarioModel;
//
//    @Mock
//    private CidadesModel cidadesModel;
//
//    @Mock
//    private LocalidadeModel localidadeModel;
//
//    @MockBean
//    private PostagemService postagemService;
//
//    @MockBean
//    private CidadeService cidadeService;
//
//    @MockBean
//    private UsuarioMapperDisassembler usuarioMapperDisassembler;
//
//    @MockBean
//    private UsuarioRepository usuarioRepository;
//
//    @MockBean
//    private PostagemMapperAssembler postagemMapperAssembler;
//
//    @Test
//    public void testListarPostagens() throws Exception {
//        Mockito.when(postagemService.listar()).thenReturn(new ArrayList<>());
//
//        mockMvc.perform(get("/postagens"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Nenhuma postagem realizada ainda."))
//                .andDo(print());
//    }
//
//    @Test
//    public void testCadastrarPostagem() throws Exception {
//        when(cidadeService.buscarPorId(any())).thenReturn(Optional.of(cidadesModel));
//        when(usuarioMapperDisassembler.usuarioRequestParaUsuarioModel(any())).thenReturn(usuarioModel);
//        when(usuarioRepository.save(any())).thenReturn(usuarioModel);
//        when(cidadeService.salvar(any())).thenReturn(localidadeModel);
//        when(postagemService.cadastrar(any())).thenReturn(postagemModel);
//        when(postagemMapperAssembler.postagemModelParaPostagemResponse(any())).thenReturn(new PostagemResponse());
//
//        String jsonRequest = "{\n" +
//                "    \"usuario\":{\n" +
//                "        \"nomeUsuario\": \"Nicoly\",\n" +
//                "        \"emailUsuario\":\"nicoly@email.com\"\n" +
//                "    },\n" +
//                "    \"localidade\":{\n" +
//                "        \"ruaLocalidade\":\"rua tarara\",\n" +
//                "        \"numeroLocalidade\": 23,\n" +
//                "        \"bairroLocalidade\": \"Sao Tadeu\",\n" +
//                "        \"pontoReferenciaLocalidade\": \"perto do mercado\",\n" +
//                "        \"idCidade\": 1\n" +
//                "    },\n" +
//                "    \"tipo\": \"ROUBO\",\n" +
//                "    \"descricao\": \"ddddddd\",\n" +
//                "    \"dica\": \"não ande sozinho\"\n" +
//                "}";
//
//        mockMvc.perform(post("/postagens")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isCreated());
//
//
//        verify(usuarioMapperDisassembler).usuarioRequestParaUsuarioModel(any());
//        verify(usuarioRepository).save(any());
//        verify(cidadeService).salvar(any());
//        verify(postagemService).cadastrar(any());
//        verify(postagemMapperAssembler).postagemModelParaPostagemResponse(any());
//    }
//}
