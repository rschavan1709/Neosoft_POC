package com.neosoft.user.exceptions;

import com.neosoft.user.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<BaseResponse> exception(final Exception ex)
    {
        return new ResponseEntity<>(BaseResponse.builder()
                .code(HttpStatus.CONFLICT.value())
                .error(ex.getMessage())
                .build(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyPresentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<BaseResponse> alreadyPresentException(final UserAlreadyPresentException ex)
    {
        return new ResponseEntity<>(BaseResponse.builder()
                .code(HttpStatus.CONFLICT.value())
                .error(ex.getMessage())
                .build(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> notFoundException(final UserNotFoundException ex)
    {
        return new ResponseEntity<>(BaseResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .error(ex.getMessage())
                .build(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(BaseResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .error(errors)
                .build(),HttpStatus.BAD_REQUEST);
    }
}
