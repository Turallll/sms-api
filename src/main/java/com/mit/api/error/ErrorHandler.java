package com.mit.api.error;

import com.mit.api.model.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDto handleInternalServerErrors(Exception ex) {
        log.error("InternalServerError: {}", ex);
        return new ErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidIdentifierException.class)
    public ErrorDto handleInvalidIdentifierException(InvalidIdentifierException ex) {
        log.error("InvalidIdentifierException: {}", ex.getMessage());
        return new ErrorDto(
                HttpStatus.BAD_REQUEST.name(),
                ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTokenException.class)
    public ErrorDto handleInvalidTokenException(InvalidTokenException ex) {
        log.error("InvalidTokenException: {}", ex.getMessage());
        return new ErrorDto(
                HttpStatus.BAD_REQUEST.name(),
                ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorDto handleAccessDeniedException(AccessDeniedException ex) {
        log.error("AccessDeniedException: {}", ex.getMessage());
        return new ErrorDto(
                HttpStatus.BAD_REQUEST.name(),
                ex.getMessage());
    }
}
