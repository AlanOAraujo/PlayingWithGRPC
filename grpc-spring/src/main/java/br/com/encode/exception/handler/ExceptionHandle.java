package br.com.encode.exception.handler;

import br.com.encode.exception.BaseBusinessException;
import io.grpc.Status;
import org.lognet.springboot.grpc.recovery.GRpcExceptionHandler;
import org.lognet.springboot.grpc.recovery.GRpcExceptionScope;
import org.lognet.springboot.grpc.recovery.GRpcServiceAdvice;

@GRpcServiceAdvice
public class ExceptionHandle {

    @GRpcExceptionHandler()
    public Status handleBaseBusinessException(BaseBusinessException e, GRpcExceptionScope scope) {
        return Status.fromCode(e.getStatusCode().getCode())
                .withDescription(e.getErrorMessage())
                .asRuntimeException()
                .getStatus();
    }

}
