package com.wilker.agendadortarefas.infrastructure.dto.mapper;

import com.wilker.agendadortarefas.infrastructure.dto.TarefasDTO;
import com.wilker.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")

public interface TarefaConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefaDTO (TarefasEntity tarefasEntity);

}
