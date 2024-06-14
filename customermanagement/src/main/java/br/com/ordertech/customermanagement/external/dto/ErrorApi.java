package br.com.ordertech.customermanagement.external.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorApi {
    private String message;
    private List<FieldError> fieldErrors = new ArrayList<>();

    public ErrorApi(String message) {
        this.message = message;
    }

    public void addError(FieldError fieldError) {
        fieldErrors.add(fieldError);
    }
}
