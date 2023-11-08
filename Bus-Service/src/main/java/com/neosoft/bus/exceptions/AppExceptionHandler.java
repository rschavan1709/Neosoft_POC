package com.neosoft.bus.exceptions;

import com.neosoft.bus.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @ExceptionHandler(BusAlreadyPresentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<BaseResponse> alreadyPresentException(final BusAlreadyPresentException ex)
    {
        return new ResponseEntity<>(BaseResponse.builder()
                .code(HttpStatus.CONFLICT.value())
                .error(ex.getMessage())
                .build(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BusNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> notFoundException(final BusNotFoundException ex)
    {
        return new ResponseEntity<>(BaseResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .error(ex.getMessage())
                .build(),HttpStatus.NOT_FOUND);
    }


}
