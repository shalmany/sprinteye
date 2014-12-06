package com.slb.core.controller.exception;

import java.util.ArrayList;

import java.util.List;

public class ValidationErrorDTO {

    private List<FieldErrorDTO> fieldErrors = new ArrayList<>();
    private List<FieldErrorDTO> globalErrors = new ArrayList<>();

    public ValidationErrorDTO() {

    }

    public void addFieldError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }
    
    public void addGlobalError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        globalErrors.add(error);
    }

	public List<FieldErrorDTO> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public List<FieldErrorDTO> getGlobalErrors() {
		return globalErrors;
	}

	public void setGlobalErrors(List<FieldErrorDTO> globalErrors) {
		this.globalErrors = globalErrors;
	}

    //Getter is omitted.
    
    
}