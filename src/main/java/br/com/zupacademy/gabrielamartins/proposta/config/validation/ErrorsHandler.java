package br.com.zupacademy.gabrielamartins.proposta.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorsHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorsOutputDto> handle (MethodArgumentNotValidException exception){
        List<ErrorsOutputDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorsOutputDto erro = new ErrorsOutputDto(e.getField(), mensagem);
            dto.add(erro);});
        return dto;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> assertionException(final IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }



}
