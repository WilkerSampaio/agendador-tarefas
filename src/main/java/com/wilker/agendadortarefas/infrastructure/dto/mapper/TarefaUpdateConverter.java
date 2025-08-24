package com.wilker.agendadortarefas.infrastructure.dto.mapper;

import com.wilker.agendadortarefas.infrastructure.dto.TarefasDTOResponse;
import com.wilker.agendadortarefas.infrastructure.dto.in.TarefasDTORequest;
import com.wilker.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefasDTORequest tarefasDTORequest, @MappingTarget TarefasEntity tarefasEntity);
}
