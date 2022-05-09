package com.bureau.controller.advisor;

import com.bureau.exception.DataExistException;
import com.bureau.exception.ItemNotFoundException;
import com.bureau.exception.ProjectAssignationException;
import com.bureau.model.dto.response.ErrorModel;
import com.bureau.model.dto.response.FieldsValidationErrorResponse;
import com.bureau.model.dto.response.MessageResponse;
import com.bureau.util.ErrorStatusCodes;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public MessageResponse handleEmailExistException(ItemNotFoundException ex) {
        return new MessageResponse(ex.getMessage(), ex.getCode());
    }

    @ExceptionHandler(DataExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public MessageResponse handleDataExistException(DataExistException ex) {
        return new MessageResponse(ex.getMessage(), ex.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FieldsValidationErrorResponse handleValidationException(
            MethodArgumentNotValidException ex) {
        List<ErrorModel> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> new ErrorModel(e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());
        return new FieldsValidationErrorResponse(errors);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public MessageResponse handleBadCredentialsException(BadCredentialsException ex) {
        return new MessageResponse(ex.getMessage(), ErrorStatusCodes.BAD_CREDENTIALS);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ProjectAssignationException.class)
    public MessageResponse handleTaskAssignationException(ProjectAssignationException ex) {
        return new MessageResponse(ex.getMessage(), ex.getCode());
    }
}
