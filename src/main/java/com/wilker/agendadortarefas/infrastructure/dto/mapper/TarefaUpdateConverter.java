package com.wilker.agendadortarefas.infrastructure.dto.mapper;

import com.wilker.agendadortarefas.infrastructure.dto.TarefasDTO;
import com.wilker.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefasDTO tarefasDTO, @MappingTarget TarefasEntity tarefasEntity);
}
