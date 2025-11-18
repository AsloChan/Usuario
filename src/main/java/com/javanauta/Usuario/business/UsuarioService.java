package com.javanauta.Usuario.business;

import com.javanauta.Usuario.business.conveter.UsuarioConverter;
import com.javanauta.Usuario.business.dto.UsuarioDTO;
import com.javanauta.Usuario.infrastructure.entity.Usuario;
import com.javanauta.Usuario.infrastructure.exceptions.ConflictException;
import com.javanauta.Usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.Usuario.infrastructure.repository.repository.UsuarioRepository;
import com.javanauta.Usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }
    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw new ConflictException("Email já cadastrado " + email);
            }
        } catch (ConflictException e){
            throw new ConflictException("Email ja cadastrado " + e.getCause());
        }}

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado " + email));
    }
    public void deletaUsuarioPorEmail(String email){

        usuarioRepository.deleteByEmail(email);
    }
        public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto) {

            // AQUI BUSCA O EMAIL DO ÚSUARIO ATRAVÉS DO TOKEN (tira a obrigatoriedade do email)
            String email = JwtUtil.extrairEmailToken(token.substring(7));

            //CRIPTOGRAFIA DE SENHA
            dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null );

            //BUSCA OS DADOS DO ÚSUARIO NO BANCO DE DADOS
            Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                    new ResourceNotFoundException("email não localizado"));

            //MESCLOU OS DADOS QUE RECEBEMOS NA REQUISIÇÃO DTO COM OS DADOS DO BANCO DE DADOS
            Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

            //SALVOU OS DADOS DO ÚSUARIO CONVERTIDO E DEPOIS PEGOU E RETORNOU E CONVERTEU PARA USUARIOdto
            return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
        }
}
