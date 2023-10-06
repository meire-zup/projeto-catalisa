package com.catalisa.cidadesegura.api.controller;

import com.catalisa.cidadesegura.api.mapper.localidade.LocalidadeMapperAssembler;
import com.catalisa.cidadesegura.api.mapper.localidade.LocalidadeMapperDisassembler;
import com.catalisa.cidadesegura.domain.dto.request.LocalidadeRequest;
import com.catalisa.cidadesegura.domain.dto.response.LocalidadeResponse;
import com.catalisa.cidadesegura.domain.model.CidadesModel;
import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import com.catalisa.cidadesegura.domain.repository.CidadesRepository;
import com.catalisa.cidadesegura.domain.service.LocalidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/localidades")
public class LocalidadeController {
    @Autowired
    private LocalidadeService localidadeService;

    @Autowired
    private LocalidadeMapperAssembler localidadeMapperAssembler;
    @Autowired
    private LocalidadeMapperDisassembler localidadeMapperDisassembler;
    @Autowired
    private CidadesRepository cidadesRepository;
    @GetMapping
    public List<LocalidadeResponse> listar() {

        List<LocalidadeModel> localidadess = localidadeService.listar();

        return localidadeMapperAssembler.toCollectionLocalidadeResponse(localidadess);
    }
    @GetMapping("/{idLocalidade}")
    public LocalidadeResponse buscarPorId(@PathVariable Long idLocalidade){

        LocalidadeResponse localidadeResponse = localidadeMapperAssembler
                .localidadeModelParaLocalidadeResponse2(localidadeService.buscarPorId(idLocalidade));
        return localidadeResponse;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LocalidadeResponse cadastrar(@RequestBody @Valid LocalidadeRequest localidadeRequest){


        Optional<CidadesModel> cidade = cidadesRepository.findById(localidadeRequest.getCidadesModel().getIdCidade());
        LocalidadeModel localidadeModel = localidadeMapperDisassembler.localidadeRequestParaLocalidadeModel(localidadeRequest);
        localidadeModel.setCidadesModel(cidade.get());
        LocalidadeResponse  localidadeResponse =  localidadeMapperAssembler
                .localidadeModelParaLocalidadeResponse(localidadeService.salvar(localidadeModel));

        return  localidadeResponse;

    }
    @PutMapping("/{idLocalidade}")
    public  LocalidadeResponse atualizar(@PathVariable Long idLocalidade, @RequestBody @Valid  LocalidadeRequest localidadeRequest){

        LocalidadeModel  localidadeModel =  localidadeMapperDisassembler. localidadeRequestParaLocalidadeModel(localidadeRequest);

        LocalidadeModel  localidadeAtual = localidadeService.buscarPorId(idLocalidade);
        BeanUtils.copyProperties(localidadeModel, localidadeAtual, "idLocalidade");

        LocalidadeResponse  localidadeResponse =  localidadeMapperAssembler.localidadeModelParaLocalidadeResponse(localidadeService.salvar(localidadeAtual));

        return  localidadeResponse;
    }

    @DeleteMapping("/{idLocalidade}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  excluir(@PathVariable Long idLocalidade){

        localidadeService.excluir(idLocalidade);

    }

}
