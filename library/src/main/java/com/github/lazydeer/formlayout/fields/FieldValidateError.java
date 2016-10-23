package com.github.lazydeer.formlayout.fields;

/**
 * Created by dzq on 2016/10/24.
 */

public class FieldValidateError {
    private InputField inputField;
    private String errorMessage;
    private int errorType;

    public FieldValidateError(InputField inputField, String errorMessage, int errorType) {
        this.inputField = inputField;
        this.errorMessage = errorMessage;
        this.errorType = errorType;
    }

    public static class ErrorType {
        public final static int NULL_ERROR = 1;
        public final static int REGEX_VALIDATE_ERROR = 2;
    }


    public InputField getInputField() {
        return inputField;
    }

    public void setInputField(InputField inputField) {
        this.inputField = inputField;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }
}
