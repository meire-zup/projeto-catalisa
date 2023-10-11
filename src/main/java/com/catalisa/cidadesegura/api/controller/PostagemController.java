package com.catalisa.cidadesegura.api.controller;

import com.catalisa.cidadesegura.api.mapper.postagem.PostagemMapperAssembler;
import com.catalisa.cidadesegura.api.mapper.usuario.UsuarioMapperDisassembler;
import com.catalisa.cidadesegura.domain.dto.request.PostagemDeleteRequest;
import com.catalisa.cidadesegura.domain.dto.request.PostagemRequest;
import com.catalisa.cidadesegura.domain.dto.response.PostagemResponse;
import com.catalisa.cidadesegura.domain.exception.PostagemNaoEncontradaException;
import com.catalisa.cidadesegura.domain.exception.UsuarioNaoCadastradoException;
import com.catalisa.cidadesegura.domain.model.CidadesModel;
import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import com.catalisa.cidadesegura.domain.model.PostagemModel;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import com.catalisa.cidadesegura.domain.repository.UsuarioRepository;
import com.catalisa.cidadesegura.domain.service.CidadeService;
import com.catalisa.cidadesegura.domain.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        Optional<UsuarioModel> usuarioModel = usuarioRepository.findByUsername(postagemRequest.getUsername());

        if(!usuarioModel.isPresent()){
            throw new UsuarioNaoCadastradoException("Usuário não cadastrado.") ;
        }

        Optional<CidadesModel> cidadesModel = cidadeService.buscarPorId(postagemRequest.getLocalidade().getIdCidade());

        LocalidadeModel localidadeModel = new LocalidadeModel();
        localidadeModel.setRuaLocalidade(postagemRequest.getLocalidade().getRuaLocalidade());
        localidadeModel.setNumeroLocalidade(postagemRequest.getLocalidade().getNumeroLocalidade());
        localidadeModel.setBairroLocalidade(postagemRequest.getLocalidade().getBairroLocalidade());
        localidadeModel.setPontoReferenciaLocalidade(postagemRequest.getLocalidade().getPontoReferenciaLocalidade());
        localidadeModel.setCidadesModel(cidadesModel.get());

        cidadeService.salvar(localidadeModel);

        PostagemModel postagemModel = new PostagemModel();
        postagemModel.setUsuario(usuarioModel.get());
        postagemModel.setLocalidade(localidadeModel);
        postagemModel.setTipo(postagemRequest.getTipo());
        postagemModel.setDescricao(postagemRequest.getDescricao());
        postagemModel.setDica(postagemRequest.getDica());

        postagemService.cadastrar(postagemModel);

        return postagemMapperAssembler.postagemModelParaPostagemResponse(postagemModel);
    }

    @DeleteMapping(path = "/{idPostagem}")
    public ResponseEntity<?> deletarPostagem(@PathVariable Long idPostagem, @RequestBody PostagemDeleteRequest postagemDeleteRequest){
        Optional<PostagemModel> postagemModel = postagemService.listarPorId(idPostagem);

        if (!postagemModel.isPresent()) {
            throw new PostagemNaoEncontradaException("Id de postagem inválido.");
        }

        Optional<PostagemModel> postagemExistente = postagemService.buscarPostagemPorIdEUsername(idPostagem,postagemDeleteRequest.getUsername());
        if (!postagemExistente.isPresent()) {
            throw new PostagemNaoEncontradaException("Não foi localizado postagem para esse username.");
        }

        postagemService.deletarPostagem(idPostagem);
        return ResponseEntity.ok().body("Postagem excluída com sucesso.");
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
