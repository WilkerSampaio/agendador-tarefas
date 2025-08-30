package com.wilker.agendadortarefas.infrastructure.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTOResponse {
    private String mensagem;
}
