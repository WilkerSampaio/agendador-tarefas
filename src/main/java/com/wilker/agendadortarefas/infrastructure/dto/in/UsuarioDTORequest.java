package com.wilker.agendadortarefas.infrastructure.dto.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsuarioDTORequest {

    private String email;
    private String senha;

}
