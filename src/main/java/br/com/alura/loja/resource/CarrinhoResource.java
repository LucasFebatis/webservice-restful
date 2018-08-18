package br.com.alura.loja.resource;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Carrinho busca(@PathParam("id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adiciona(Carrinho carrinho) throws URISyntaxException {
		new CarrinhoDAO().adiciona(carrinho);

		URI uri = new URI("/webservice-restful/rest/carrinhos/" + carrinho.getId());
		return Response.created(uri).build();
	}

	@PUT
	@Path("{id}/produtos/{produtoId}/quantidade")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alteraProduto(Produto produto, @PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.troca(produto);

		return Response.ok().build();
	}

	@DELETE
	@Path("{id}/produtos/{produtoId}")
	public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId) {

		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);

		return Response.ok().build();
	}
}
