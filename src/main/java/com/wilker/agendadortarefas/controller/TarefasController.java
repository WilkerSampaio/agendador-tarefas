package com.wilker.agendadortarefas.controller;

import com.wilker.agendadortarefas.infrastructure.dto.TarefasDTO;
import com.wilker.agendadortarefas.service.TarefasService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal){
        return ResponseEntity.ok(tarefasService.buscarTarefaDeUmaData(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaTarefaPorEmail(@RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(tarefasService.buscarTarefaPorEmail(token));
    }





}
