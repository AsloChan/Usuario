package com.javanauta.Usuario.contoller;

import com.javanauta.Usuario.business.UsuarioSevice;
import com.javanauta.Usuario.business.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Usuario")
@RequiredArgsConstructor
public class UsuarioContoller {

    private final UsuarioSevice usuarioSevice;
    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioSevice.salvaUsuario(usuarioDTO));

    }
}
