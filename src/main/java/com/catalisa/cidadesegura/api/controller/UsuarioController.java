package com.catalisa.cidadesegura.api.controller;

import com.catalisa.cidadesegura.api.mapper.usuario.UsuarioMapperAssembler;
import com.catalisa.cidadesegura.api.mapper.usuario.UsuarioMapperDisassembler;
import com.catalisa.cidadesegura.domain.dto.request.UsuarioRequest;
import com.catalisa.cidadesegura.domain.dto.response.UsuarioResponse;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import com.catalisa.cidadesegura.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapperAssembler usuarioMapperAssembler;

    @Autowired
    private UsuarioMapperDisassembler usuarioMapperDisassembler;

    @GetMapping
    public List<UsuarioResponse> listar() {

        List<UsuarioModel> usuarios = usuarioService.listar();

        return usuarioMapperAssembler.toCollectionUsuarioResponse(usuarios);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequest usuarioRequest){



    }
}