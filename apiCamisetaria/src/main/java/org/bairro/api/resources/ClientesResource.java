package org.bairro.api.resources;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.bairro.api.entidades.Clientes;
import org.bairro.api.services.ClientesService;
import org.bairro.api.utils.MensagemResposta;

@Path("clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientesResource {

	@Inject
	private ClientesService service;

	@PUT
	public Response atualizar(Clientes cliente) {
		try {
			return Response.ok(service.atualizar(cliente)).status(Status.OK).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response buscarPorId(@PathParam("id") Integer idCliente) {
		try {
			Clientes cliente = service.buscarPorId(idCliente);
			return Response.ok(cliente).build();
		} catch (Exception e) {
			MensagemResposta resultado = new MensagemResposta(e.getMessage());
			return Response.ok(resultado).status(200).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarTodos() {
		try {
			return Response.ok(service.buscarTodos()).build();
		} catch (Exception e) {
			MensagemResposta resultado = new MensagemResposta(e.getMessage());
			return Response.ok(resultado).status(200).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Integer idCliente) {
		try {
			service.deletar(idCliente);
			return Response.ok().status(Status.OK).build();
		} catch (Exception e) {
			MensagemResposta resultado = new MensagemResposta(e.getMessage());
			return Response.ok(resultado).status(404).build();
		}
	}

	@Path("/data_cadastro/{dataLong}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Clientes> buscarPorDataCadastro(@PathParam("dataLong") Long dataCadastro) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		Date dataCad = new Date(dataCadastro);
		String dataFormatada = "'" + formatador.format(dataCad) + "'";
		return service.buscarPorPropriedade("where c.dataCadastro = ", dataFormatada);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Clientes cliente) {
		try {
			return Response.ok(service.salvar(cliente)).status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
}
