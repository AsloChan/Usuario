package com.javanauta.Usuario.business;

import com.javanauta.Usuario.business.conveter.UsuarioConverter;
import com.javanauta.Usuario.business.dto.UsuarioDTO;
import com.javanauta.Usuario.infrastructure.entity.Usuario;
import com.javanauta.Usuario.infrastructure.repository.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioSevice {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }

}
