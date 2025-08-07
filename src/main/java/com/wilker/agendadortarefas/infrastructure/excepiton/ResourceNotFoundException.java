package com.wilker.agendadortarefas.infrastructure.excepiton;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
