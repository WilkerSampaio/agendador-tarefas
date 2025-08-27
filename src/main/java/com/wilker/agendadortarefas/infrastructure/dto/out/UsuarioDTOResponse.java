package com.wilker.agendadortarefas.infrastructure.dto.out;

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
