package com.wilker.agendadortarefas.infrastructure.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsuarioDTO {

    private String email;
    private String senha;

}
