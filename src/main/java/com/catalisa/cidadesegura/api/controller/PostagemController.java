package com.catalisa.cidadesegura.api.controller;

import com.catalisa.cidadesegura.api.mapper.postagem.LocalidadeMapperDisassembler;
import com.catalisa.cidadesegura.api.mapper.postagem.PostagemMapperAssembler;
import com.catalisa.cidadesegura.api.mapper.postagem.PostagemMapperDisassembler;
import com.catalisa.cidadesegura.api.mapper.usuario.UsuarioMapperDisassembler;
import com.catalisa.cidadesegura.domain.dto.request.PostagemRequest;
import com.catalisa.cidadesegura.domain.dto.response.PostagemResponse;
import com.catalisa.cidadesegura.domain.model.CidadesModel;
import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import com.catalisa.cidadesegura.domain.model.PostagemModel;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import com.catalisa.cidadesegura.domain.repository.CidadesRepository;
import com.catalisa.cidadesegura.domain.repository.UsuarioRepository;
import com.catalisa.cidadesegura.domain.service.LocalidadeService;
import com.catalisa.cidadesegura.domain.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @Autowired
    private LocalidadeService localidadeService;

    @Autowired
    private CidadesRepository cidadesRepository;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PostagemResponse cadastrar(@RequestBody PostagemRequest postagemRequest) {

        UsuarioModel usuarioModel = usuarioMapperDisassembler.usuarioRequestParaUsuarioModel(postagemRequest.getUsuario());
        usuarioRepository.save(usuarioModel);

        Optional<CidadesModel> cidadesModel = cidadesRepository.findById(postagemRequest.getLocalidade().getIdCidade());

        LocalidadeModel localidadeModel = new LocalidadeModel();
        localidadeModel.setIdEstado(postagemRequest.getLocalidade().getIdEstado());
        localidadeModel.setRuaLocalidade(postagemRequest.getLocalidade().getRuaLocalidade());
        localidadeModel.setNumeroLocalidade(postagemRequest.getLocalidade().getNumeroLocalidade());
        localidadeModel.setBairroLocalidade(postagemRequest.getLocalidade().getBairroLocalidade());
        localidadeModel.setPontoReferenciaLocalidade(postagemRequest.getLocalidade().getPontoReferenciaLocalidade());
        localidadeModel.setCidadesModel(cidadesModel.get());

        localidadeService.salvar(localidadeModel);

        PostagemModel postagemModel = new PostagemModel();
        postagemModel.setUsuario(usuarioModel);
        postagemModel.setLocalidade(localidadeModel);
        postagemModel.setTipo(postagemRequest.getTipo());
        postagemModel.setDescricao(postagemRequest.getDescricao());
        postagemModel.setDica(postagemRequest.getDica());

        postagemService.cadastrar(postagemModel);

        return postagemMapperAssembler.postagemModelParaPostagemResponse(postagemModel);
    }
}
