package br.com.encode.exception;

import io.grpc.Status;

public class ProductNotFoundException extends BaseBusinessException {


    private static final String ERROR_MESSAGE = "Product with ID %s not found";
    private final String id;


    public ProductNotFoundException(String message) {
        super(String.format(ERROR_MESSAGE, message));
        this.id = message;
    }

    @Override
    public Status getStatusCode() {
        return Status.NOT_FOUND;
    }

    @Override
    public String getErrorMessage() {
        return (String.format(ERROR_MESSAGE, this.id));
    }
}
