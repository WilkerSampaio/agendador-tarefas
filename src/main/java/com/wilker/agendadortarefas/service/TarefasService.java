package com.wilker.agendadortarefas.service;

import com.wilker.agendadortarefas.infrastructure.dto.TarefasDTOResponse;
import com.wilker.agendadortarefas.infrastructure.dto.in.TarefasDTORequest;
import com.wilker.agendadortarefas.infrastructure.dto.mapper.TarefaConverter;
import com.wilker.agendadortarefas.infrastructure.dto.mapper.TarefaUpdateConverter;
import com.wilker.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.wilker.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.wilker.agendadortarefas.infrastructure.exception.ResourceNotFoundException;
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
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTOResponse salvarTarefa(TarefasDTORequest tarefasDTORequest, String token){

        String email = jwtUtil.extractUsername(token.substring(7));

        tarefasDTORequest.setEmailUsuario(email);
        tarefasDTORequest.setDataCriacao(LocalDateTime.now());
        tarefasDTORequest.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        TarefasEntity tarefa = tarefaConverter.paraTarefaEntity(tarefasDTORequest);

        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefa));
    }

    public List<TarefasDTOResponse> buscaListaDeTarefaPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefaConverter.paraListaTarefaDTO(
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));

    }

    public List<TarefasDTOResponse> buscarTarefaPorEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefaConverter.paraListaTarefaDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletaTarefaPorId(String id){
        try{
            tarefasRepository.deleteById(id);

        } catch(ResourceNotFoundException e){
            throw new ResourceNotFoundException("ID não encontrado" + id);
        }

    }

    public TarefasDTOResponse alteraStatusTarefa(StatusNotificacaoEnum statusNotificacaoEnum, String id){
        try{
            TarefasEntity tarefa = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException ("ID não encontrado"));
            tarefa.setStatusNotificacaoEnum(statusNotificacaoEnum);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefa));

        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa");
        }

    }

    public TarefasDTOResponse alterarDadosTarefa(TarefasDTORequest tarefasDTORequest, String id){
        try{
            TarefasEntity tarefasEntity = tarefasRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("ID não encontrado"));

            tarefaUpdateConverter.updateTarefas(tarefasDTORequest, tarefasEntity);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefasEntity));

        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa");
        }


    }

}
