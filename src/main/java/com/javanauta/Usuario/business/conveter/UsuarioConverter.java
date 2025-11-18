package com.javanauta.Usuario.business.conveter;

import com.javanauta.Usuario.business.dto.EnderecoDTO;
import com.javanauta.Usuario.business.dto.TelefoneDTO;
import com.javanauta.Usuario.business.dto.UsuarioDTO;
import com.javanauta.Usuario.infrastructure.entity.Endereco;
import com.javanauta.Usuario.infrastructure.entity.Telefone;
import com.javanauta.Usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    // Lista Usuario
    public Usuario paraUsuario (UsuarioDTO usuarioDTO) {
     return Usuario.builder()
             .nome(usuarioDTO.getNome())
             .email(usuarioDTO.getEmail())
             .senha(usuarioDTO.getSenha())
             .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
             .telefones(paraListaTelefones(usuarioDTO.getTelefones()))
             .build();
    }

    // Lsita Endereços
    public List<Endereco> paraListaEndereco(List<EnderecoDTO> ederecoDTOS){
        return ederecoDTOS.stream().map(this::paraEndeerco).toList();
    }

    public Endereco paraEndeerco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    // Lista telefones
    public List<Telefone> paraListaTelefones(List<TelefoneDTO> telefoneDTOS){
    return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

// conversor

    public UsuarioDTO paraUsuarioDTO (Usuario usuarioDTO) {
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefonesDTO(usuarioDTO.getTelefones()))
                .build();
    }

    // Lsita Endereços
    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> ederecoDTOS){
        return ederecoDTOS.stream().map(this::paraEndeercoDTO).toList();
    }

    public EnderecoDTO paraEndeercoDTO(Endereco enderecoDTO){
        return EnderecoDTO.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    // Lista telefones
    public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefoneDTO) {
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }
    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }


}
