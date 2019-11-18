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

import org.bairro.api.entidades.Camisas;
import org.bairro.api.services.CamisasService;
import org.bairro.api.utils.MensagemResposta;

@Path("camisas")
public class CamisasResource {

	@Inject
	private CamisasService service;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(Camisas camisa) {
		try {
			return Response.ok(service.atualizar(camisa)).status(Status.OK).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}


	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("id") Integer idCamisa) {
		try {
			Camisas camisa = service.buscarPorId(idCamisa);
			return Response.ok(camisa).build();
		} catch (Exception e) {
			MensagemResposta resultado = new MensagemResposta(e.getMessage());
			return Response.ok(resultado).status(200).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Camisas> buscarTodos() {
		return service.buscarTodos();
	}

	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Integer id) {
		try {
			service.deletar(id);
			return Response.ok("camisa removido com sucesso!").build();
		} catch (Exception e) {
			MensagemResposta resultado = new MensagemResposta(e.getMessage());
			return Response.ok(resultado).status(404).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Camisas camisa) {
		try {
			return Response.ok(service.salvar(camisa)).status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

}
