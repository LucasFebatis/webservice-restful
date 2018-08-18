package br.com.alura.loja;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest")
public class ApplicationConfig extends Application {
	
	String protocolo = "http://";
	String porta = ":8080";
	String carrinhosPath = "/webservice-restful/rest/carrinhos";
	
	@Override
	public Set<Class<?>> getClasses() {
		try {
			System.out.println("Servidor rodando em: " + 
		protocolo + InetAddress.getLocalHost().getHostAddress() + porta);

			System.out.println("Recurso webservice-restful disponível em: " + 
		protocolo + InetAddress.getLocalHost().getHostAddress() + porta + carrinhosPath);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return super.getClasses();
	}
}