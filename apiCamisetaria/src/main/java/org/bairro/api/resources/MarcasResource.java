package org.bairro.api.resources;

import java.util.List;

import javax.inject.Inject;
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
import javax.ws.rs.core.Response.Status;

import org.bairro.api.entidades.Marcas;
import org.bairro.api.services.MarcasService;
import org.bairro.api.utils.MensagemResposta;



@Path("marcas")
public class MarcasResource {

	@Inject
	private MarcasService service;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(Marcas marca) {
		try {
			return Response.ok(service.atualizar(marca)).status(Status.OK).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}	
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("id") Integer idMarca) {
		try {
			service.quantidadeComPropriedade("where t.idMarca = ", idMarca);
			Marcas marca = service.buscarPorId(idMarca);
			return Response.ok(marca).build();
		} catch (Exception e) {
			MensagemResposta resultado = new MensagemResposta(e.getMessage());
			return Response.ok(resultado).status(200).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Marcas> buscarTodos() {
		return service.buscarTodos();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Integer idMarca) {
		try {
			service.deletar(idMarca);
			return Response.ok("marca removido com sucesso!").build();
		} catch (Exception e) {
			MensagemResposta resultado = new MensagemResposta(e.getMessage());
			return Response.ok(resultado).status(404).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Marcas marca) {
		try {
			return Response.ok(service.salvar(marca)).status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
}
