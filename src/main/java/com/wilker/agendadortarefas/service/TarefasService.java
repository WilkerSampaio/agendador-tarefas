package com.wilker.agendadortarefas.service;

import com.wilker.agendadortarefas.infrastructure.dto.TarefasDTO;
import com.wilker.agendadortarefas.infrastructure.dto.mapper.TarefaConverter;
import com.wilker.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.wilker.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.wilker.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.wilker.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO salvarTarefa(String token, TarefasDTO tarefasDTO){

            String email = jwtUtil.extractUsername(token.substring(7));

            tarefasDTO.setEmailUsuario(email);
            tarefasDTO.setDataCriacao(LocalDateTime.now());
            tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
            TarefasEntity tarefa = tarefaConverter.paraTarefaEntity(tarefasDTO);

            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefa));
    }
    public List<TarefasDTO> buscarTarefaDeUmaData(LocalDateTime dataInicial, LocalDateTime dataFinal){
       return tarefaConverter.paraListaTarefaDTO(
               tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));

    }

    public List<TarefasDTO> buscarTarefaPorEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefaConverter.paraListaTarefaDTO(tarefasRepository.findByEmailUsuario(email));
    }



}
