package com.catalisa.cidadesegura.api.controller;


import com.catalisa.cidadesegura.api.mapper.usuario.UsuarioMapperAssembler;
import com.catalisa.cidadesegura.api.mapper.usuario.UsuarioMapperDisassembler;
import com.catalisa.cidadesegura.domain.dto.request.AuthenticationRequest;
import com.catalisa.cidadesegura.domain.dto.request.UsuarioRequest;
import com.catalisa.cidadesegura.domain.dto.response.LoginResponse;
import com.catalisa.cidadesegura.domain.dto.response.UsuarioResponse;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import com.catalisa.cidadesegura.domain.service.UsuarioService;
import com.catalisa.cidadesegura.security.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidade-segura")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapperAssembler usuarioMapperAssembler;

    @Autowired
    private UsuarioMapperDisassembler usuarioMapperDisassembler;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/usuarios")
    public List<UsuarioResponse> listar() {

        List<UsuarioModel> usuarios = usuarioService.listar();

        return usuarioMapperAssembler.toCollectionUsuarioResponse(usuarios);
    }
    @GetMapping("/usuarios/{idUsuario}")
    public UsuarioResponse buscarPorId(@PathVariable Long idUsuario){

        UsuarioResponse usuarioResponse = usuarioMapperAssembler
                .usuarioModelParaUsuarioResponse(usuarioService.buscarPorId(idUsuario));
        return usuarioResponse;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest authenticationRequest){

        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UsuarioModel) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequest request) {

        var usuario = request.toModel();
        return usuarioService.salvar(usuario, HttpStatus.CREATED);
    }


    @PutMapping("/atualizar/{idUsuario}")
    public UsuarioResponse atualizar(@PathVariable Long idUsuario, @RequestBody @Valid UsuarioRequest usuarioRequest){

        UsuarioModel usuarioModel = usuarioMapperDisassembler.usuarioRequestParaUsuarioModel(usuarioRequest);

        UsuarioModel usuarioAtual = usuarioService.buscarPorId(idUsuario);

        BeanUtils.copyProperties(usuarioModel, usuarioAtual, "idUsuario");

        UsuarioResponse usuarioResponse = usuarioMapperAssembler.usuarioModelParaUsuarioResponse(usuarioService.salvar(usuarioAtual));

        return usuarioResponse;
    }

    @DeleteMapping("/excluir/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  excluir(@PathVariable Long idUsuario){

        usuarioService.excluir(idUsuario);

    }

}
