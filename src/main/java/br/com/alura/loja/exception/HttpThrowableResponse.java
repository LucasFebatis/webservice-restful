package br.com.alura.loja.exception;

public class HttpThrowableResponse {

	private String exceptionClass;
	private String mensagem;

	public HttpThrowableResponse(Throwable exception) {
		this.exceptionClass = exception.getClass().getSimpleName();
		this.mensagem = exception.getMessage();
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getExceptionClass() {
		return exceptionClass;
	}

	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}
	
}
