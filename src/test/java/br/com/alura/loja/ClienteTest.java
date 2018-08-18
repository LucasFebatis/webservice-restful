package br.com.alura.loja;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class ClienteTest {

	HttpServer server;
	Client client;
	WebTarget target;
	
	@BeforeAll
	public void subirServidor() {
		// Configurando Servidor
		URI uri = URI.create("http://localhost:8080/");
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		
		// Subindo Servidor
		server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		
		//Configurando Log
		ClientConfig clientConfig = new ClientConfig();
        Logger logger = Logger.getLogger(Carrinho.class.getName());
        Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
        clientConfig.register(feature);
		
		//Obtendo Client e Target
		this.client = ClientBuilder.newClient();
		this.target = client.target("http://localhost:8080");
		
		System.out.println("Servidor rodando");
	}
	
	@AfterAll
	public void descerServidor() {
		server.shutdown();
		System.out.println("Servidor parado");
	}

	@Test
	public void testaBusca() {

		// Realizando requisicao
		String conteudo = target.path("/carrinhos/1").request().get(String.class);

		System.out.println(conteudo);
	}
	
	@Test
	public void testaAdiciona() {

		//Criando carrinho para envio
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314, "Microfone", 37, 1));
		carrinho.setRua("Rua Vergueiro 3185");
		carrinho.setCidade("São Paulo");
		
		// Realizando requisicao
		Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_JSON);		
		Response response = target.path("/carrinhos").request().post(entity);
		
		//Tratando resposta
		Assertions.assertEquals(201, response.getStatus());
		String location = response.getHeaderString("Location");
		
		//Obtendo carrinho criado
		Carrinho carrinhoCarregado = target.path(location).request().get(Carrinho.class);
		Assertions.assertEquals("Microfone", carrinhoCarregado.getProdutos().get(0).getNome());
		
	}
}
