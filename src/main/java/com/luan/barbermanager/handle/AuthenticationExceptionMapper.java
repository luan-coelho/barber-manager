package com.luan.barbermanager.handle;

import com.luan.barbermanager.exception.AuthenticationException;
import com.luan.barbermanager.handle.rest.response.ErrorResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.http.HttpServerRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.SneakyThrows;

@SuppressWarnings("unused")
@Provider
@ApplicationScoped
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException>, HandleExceptionMapper {

    @Context
    HttpServerRequest request;

    @SneakyThrows
    @Override
    public Response toResponse(AuthenticationException exception) {
        ErrorResponse errorResponse = buildResponse(exception, request);
        return Response.status(errorResponse.getStatus()).entity(errorResponse).build();
    }

    @Override
    public String getTitle() {
        return HttpResponseStatus.UNAUTHORIZED.reasonPhrase();
    }

    @Override
    public int getStatus() {
        return HttpResponseStatus.UNAUTHORIZED.code();
    }

}
