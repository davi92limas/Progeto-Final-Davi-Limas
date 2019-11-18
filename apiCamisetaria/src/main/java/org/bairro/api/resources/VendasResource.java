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

import org.bairro.api.entidades.Vendas;
import org.bairro.api.services.VendasService;
import org.bairro.api.utils.MensagemResposta;

@Path("vendas")
public class VendasResource {

	@Inject
	private VendasService service;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(Vendas venda) {
		try {
			return Response.ok(service.atualizar(venda)).status(Status.OK).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("id") Integer idVenda) {
		try {
			service.quantidadeComPropriedade("where v.idVenda = ", idVenda);
			Vendas venda = service.buscarPorId(idVenda);
			return Response.ok(venda).build();
		} catch (Exception e) {
			MensagemResposta resultado = new MensagemResposta(e.getMessage());
			return Response.ok(resultado).status(200).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vendas> buscarTodos() {
		return service.buscarTodos();
	}

	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Integer idVenda) {
		try {
			service.deletar(idVenda);
			return Response.ok("Venda removida com sucesso!").build();
		} catch (Exception e) {
			MensagemResposta resultado = new MensagemResposta(e.getMessage());
			return Response.ok(resultado).status(404).build();
		}
	}

	@Path("/data_venda/{dataLong}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vendas> buscarPorDataVenda(@PathParam("dataLong") Long dataVenda) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		Date dataVen = new Date(dataVenda);
		String dataFormatada = "'" + formatador.format(dataVen) + "'";
		return service.buscarPorPropriedade("where v.dataVenda = ", dataFormatada);
	}

	@GET
	@Path("/funcionario/{idFuncionario}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vendas> buscarPorIdFuncionario(@PathParam("idFuncionario") Integer idFuncionario) {
		return service.buscarPorPropriedade("where v.idFuncionario = ", idFuncionario);
	}

	@GET
	@Path("/jogos/{idCamisa}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vendas> buscarPorIdJogo(@PathParam("idCamisa") Integer idCamisa) {
		return service.buscarPorCamisa(idCamisa);
	}

	@GET
	@Path("/cliente/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vendas> buscarPorIdCliente(@PathParam("idCliente") Integer idCliente) {
		return service.buscarPorPropriedade("where v.idCliente = ", idCliente);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(Vendas venda) {
		try {
			Vendas vendaSalva = service.salvar(venda);
			return Response.ok(vendaSalva).status(201).build();
		} catch (Exception e) {
			MensagemResposta mensagem = new MensagemResposta("Erro ao salvar a venda! " + e.getMessage());
			return Response.ok(mensagem).status(400).build();
		}
	}

	public boolean existeComPropriedade(String string, Integer idFuncionario) {
		// TODO Auto-generated method stub
		return false;
	}

}
