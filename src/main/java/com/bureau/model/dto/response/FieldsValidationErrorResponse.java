package com.bureau.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FieldsValidationErrorResponse {
    private List<ErrorModel> errors;
}
