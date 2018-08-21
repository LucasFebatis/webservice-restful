package br.com.alura.loja.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

	public Response toResponse(Throwable bex) {
		return Response.status(Status.BAD_REQUEST)
				.entity(new HttpThrowableResponse(bex))
				.build();
	}

}
