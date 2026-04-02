package com.myorganisation.vibehub.exception;

import com.myorganisation.vibehub.dto.response.GenericResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponseDto> handleException(Exception ex) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        genericResponseDto.setIsSuccess(false);
        genericResponseDto.setMessage("An exception occurred");
        genericResponseDto.setDetails(Map.of("detail", ex.getMessage()));

        return new ResponseEntity<>(genericResponseDto, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GenericResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        genericResponseDto.setIsSuccess(false);
        genericResponseDto.setMessage("User doesn't exist");
        genericResponseDto.setDetails(Map.of("detail", ex.getMessage()));

        return new ResponseEntity<>(genericResponseDto, HttpStatusCode.valueOf(404));
    }
}
