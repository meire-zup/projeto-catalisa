package com.catalisa.cidadesegura.api.controller;

import com.catalisa.cidadesegura.api.mapper.postagem.PostagemMapperAssembler;
import com.catalisa.cidadesegura.api.mapper.postagem.PostagemMapperDisassembler;
import com.catalisa.cidadesegura.api.mapper.usuario.UsuarioMapperDisassembler;
import com.catalisa.cidadesegura.domain.dto.request.PostagemRequest;
import com.catalisa.cidadesegura.domain.dto.response.PostagemResponse;
import com.catalisa.cidadesegura.domain.exception.PostagemNaoEncontradaException;
import com.catalisa.cidadesegura.domain.model.CidadesModel;
import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import com.catalisa.cidadesegura.domain.model.PostagemModel;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import com.catalisa.cidadesegura.domain.repository.UsuarioRepository;
import com.catalisa.cidadesegura.domain.service.CidadeService;
import com.catalisa.cidadesegura.domain.service.PostagemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private PostagemMapperDisassembler postagemMapperDisassembler;

    @Autowired
    private UsuarioMapperDisassembler usuarioMapperDisassembler;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostagemMapperAssembler postagemMapperAssembler;

    @GetMapping
    public ResponseEntity<?> listarPostagens() {

        List<PostagemModel> postagens = postagemService.listar();

        if (postagens.isEmpty()) {
            return ResponseEntity.ok("Nenhuma postagem realizada ainda.");
        }

        return ResponseEntity.ok(postagemMapperAssembler.toCollectionPostagemResponse(postagens));
    }

    @GetMapping(path = "/{idPostagem}")
    public ResponseEntity<?> listarPostagemPorId(@PathVariable Long idPostagem) {

        Optional<PostagemModel> postagemModel = postagemService.listarPorId(idPostagem);

        if (!postagemModel.isPresent()) {
            throw new PostagemNaoEncontradaException("Id de postagem inválido.");
        }

        return ResponseEntity.ok(postagemMapperAssembler.postagemModelParaPostagemResponse(postagemModel.get()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PostagemResponse cadastrarPostagem(@RequestBody @Valid PostagemRequest postagemRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();

        UsuarioModel usuarioModel = (UsuarioModel) usuarioRepository.findByUsername(nomeUsuario);

        Optional<CidadesModel> cidadesModel = cidadeService.buscarPorId(postagemRequest.getLocalidade().getIdCidade());

        LocalidadeModel localidadeModel = new LocalidadeModel();
        localidadeModel.setRuaLocalidade(postagemRequest.getLocalidade().getRuaLocalidade());
        localidadeModel.setNumeroLocalidade(postagemRequest.getLocalidade().getNumeroLocalidade());
        localidadeModel.setBairroLocalidade(postagemRequest.getLocalidade().getBairroLocalidade());
        localidadeModel.setPontoReferenciaLocalidade(postagemRequest.getLocalidade().getPontoReferenciaLocalidade());
        localidadeModel.setCidadesModel(cidadesModel.get());

        cidadeService.salvar(localidadeModel);

        PostagemModel postagemModel = new PostagemModel();
        postagemModel.setUsuario(usuarioModel);
        postagemModel.setLocalidade(localidadeModel);
        postagemModel.setTipo(postagemRequest.getTipo());
        postagemModel.setDescricao(postagemRequest.getDescricao());
        postagemModel.setDica(postagemRequest.getDica());

        postagemService.cadastrar(postagemModel);

        return postagemMapperAssembler.postagemModelParaPostagemResponse(postagemModel);
    }

    /*@PutMapping(path = "/{idPostagem}")
    public PostagemResponse editarPostagem(@PathVariable Long idPostagem, @RequestBody @Valid PostagemRequest postagemRequest){
        Optional<PostagemModel> postagemAtual = postagemService.listarPorId(idPostagem);

        if (!postagemAtual.isPresent()) {
            throw new PostagemNaoEncontradaException("Postagem de id "+idPostagem+" não localizado.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();

        Optional<CidadesModel> cidadesModel = cidadeService.buscarPorId(postagemRequest.getLocalidade().getIdCidade());

        System.out.println(cidadesModel.get().getEstado().getNomeEstado());
        System.out.println(cidadesModel);
        LocalidadeModel localidadeModel = new LocalidadeModel();
        localidadeModel.setRuaLocalidade(postagemRequest.getLocalidade().getRuaLocalidade());
        localidadeModel.setNumeroLocalidade(postagemRequest.getLocalidade().getNumeroLocalidade());
        localidadeModel.setBairroLocalidade(postagemRequest.getLocalidade().getBairroLocalidade());
        localidadeModel.setPontoReferenciaLocalidade(postagemRequest.getLocalidade().getPontoReferenciaLocalidade());
        localidadeModel.setCidadesModel(cidadesModel.get());

        cidadeService.salvar(localidadeModel);
        postagemAtual.get().setLocalidade(localidadeModel);
        postagemAtual.get().setTipo(postagemRequest.getTipo());
        postagemAtual.get().setDescricao(postagemRequest.getDescricao());
        postagemAtual.get().setDica(postagemRequest.getDica());

        postagemService.salvar(postagemAtual.get());
        return postagemMapperAssembler.postagemModelParaPostagemResponse(postagemAtual.get());

        *//*if(!postagemAtual.get().getUsuario().getUsername().equals(nomeUsuario)){
            throw new PostagemNaoEncontradaException("Não é possível editar postagens feitas por outros usuários.");
        }else{
            BeanUtils.copyProperties(postagemRequest, postagemAtual, "idPostagem","usuario");
            PostagemModel postagemModel1 = postagemService.salvar(postagemAtual.get());
            return postagemMapperAssembler.postagemModelParaPostagemResponse(postagemModel1);
        }*//*
    }*/

    @DeleteMapping(path = "/{idPostagem}")
    public ResponseEntity<?> deletarPostagem(@PathVariable Long idPostagem){
        String role = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();

        for(GrantedAuthority authority:authentication.getAuthorities()){
            if(authority.getAuthority().equals("ADMIN")) {
                role = authority.getAuthority();
            }else{
                role = authority.getAuthority();
            }
        }

        Optional<PostagemModel> postagemModel = postagemService.listarPorId(idPostagem);

        if (!postagemModel.isPresent()) {
            throw new PostagemNaoEncontradaException("Postagem de id "+idPostagem+" não localizado.");
        }
        System.out.println(nomeUsuario);
        System.out.println(role);
        if(role.equals("ADMIN")){
            postagemService.deletarPostagem(idPostagem);
            return ResponseEntity.ok().body("Postagem excluída com sucesso!");
        } else {
            if (!postagemModel.get().getUsuario().getUsername().equals(nomeUsuario)) {
                throw new PostagemNaoEncontradaException("Não é possível excluir postagens feitas por outros usuários.");
            }

            postagemService.deletarPostagem(idPostagem);
            return ResponseEntity.ok().body("Postagem excluída com sucesso!");
        }
    }

    @GetMapping("/por-cidade/{cidade}")
    public ResponseEntity<?> buscarPorCidade(@PathVariable String cidade) {

        List<PostagemModel> postagens = postagemService.buscarPorCidade(cidade);

        if (postagens.isEmpty()) {
            return ResponseEntity.ok("Nenhuma postagem dessa cidade realizada ainda.");
        } else {

            return ResponseEntity.ok(postagemMapperAssembler.toCollectionPostagemResponse(postagens));
        }
    }

    @GetMapping("/por-bairro/{bairro}")
    public ResponseEntity<?> buscarPorBairro(@PathVariable String bairro) {

        List<PostagemModel> postagens = postagemService.buscarPorBairro(bairro);

        if (postagens.isEmpty()) {
            return ResponseEntity.ok("Nenhuma postagem desse bairro realizada ainda.");
        } else {

            return ResponseEntity.ok(postagemMapperAssembler.toCollectionPostagemResponse(postagens));
        }
    }
}
