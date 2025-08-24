package com.wilker.agendadortarefas.infrastructure.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsuarioDTOResponse {

    private String email;
    private String senha;

}
