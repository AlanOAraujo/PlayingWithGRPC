package br.com.encode.exception;

import io.grpc.Status;

public class ProductAlreadyExistsException extends BaseBusinessException {

    private static final String ERROR_MESSAGE = "Product %s already exists";
    private final String name;


    public ProductAlreadyExistsException(String message) {
        super(String.format(ERROR_MESSAGE, message));
        this.name = message;
    }

    @Override
    public Status getStatusCode() {
        return Status.ALREADY_EXISTS;
    }

    @Override
    public String getErrorMessage() {
        return (String.format(ERROR_MESSAGE, this.name));
    }

}
