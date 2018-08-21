package br.com.alura.loja.exception;

public class CustomReasonPhraseException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public CustomReasonPhraseException(String mensagem) {
		super(mensagem);
	}

}
