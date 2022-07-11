package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException exception) {

        ErrorDTO errorDTO = ErrorDTO.builder().error(exception.getMessage()).build();

        return switch (exception.getMessage()) {
            case "This kingdom does not belong to authenticated player!" -> ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED).body(errorDTO);
            case "Field kingdomName was empty!", "No id was entered!" -> ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(errorDTO);
            default -> null;
        };
    }
}
