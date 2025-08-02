package com.wilker.agendadortarefas.infrastructure.security;

import com.wilker.agendadortarefas.infrastructure.client.UsuarioClient;
import com.wilker.agendadortarefas.infrastructure.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient usuarioClient;

    public UserDetails carregaDadosUsuario(String email, String token){
        UsuarioDTO usuarioDTO = usuarioClient.buscaPeloEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail()) // define nome do usuário como email
                .password(usuarioDTO.getSenha()) // define a senha do usuário
                .build();

    }
}
