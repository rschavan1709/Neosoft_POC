package com.neosoft.user.exceptions;

import com.neosoft.user.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<BaseResponse> runtimeException(final RuntimeException ex)
    {
        return new ResponseEntity<>(BaseResponse.builder()
                .code(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .error(ex.getMessage())
                .data(null).build(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> userNotFoundException(final UserNotFoundException ex)
    {
        return new ResponseEntity<>(BaseResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .error(ex.getMessage())
                .data(null).build(),HttpStatus.NOT_FOUND);
    }
}
