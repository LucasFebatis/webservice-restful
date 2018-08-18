package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


public class Servidor {

	public static void main(String[] args) {
		
		//Configurando Servidor
		URI uri = URI.create("http://localhost:8080/");
		ResourceConfig config = new ResourceConfig()
				.packages("br.com.alura.loja")
				.register(JacksonJsonProvider.class);
		
		//Subindo Servidor
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		System.out.println("Servidor rodando");
        
		//Parando servidor
		try {
			System.in.read();
			server.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Servidor parou");

	}

}
