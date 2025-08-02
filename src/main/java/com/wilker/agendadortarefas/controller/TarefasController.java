package com.wilker.agendadortarefas.controller;

import com.wilker.agendadortarefas.infrastructure.dto.TarefasDTO;
import com.wilker.agendadortarefas.infrastructure.dto.mapper.TarefaConverter;
import com.wilker.agendadortarefas.service.TarefasService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> registraTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                     @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(tarefasService.salvarTarefa(token, tarefasDTO));
    }

}
