package org.bairro.api.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bairro.api.entidades.Clientes;
import org.bairro.api.resources.VendasResource;

@Stateless
public class ClientesService {

	@Inject
	private VendasResource vendas;

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public Clientes atualizar(Clientes entity) throws Exception {
		validar(entity);
		manager.merge(entity);
		return entity;
	}

	public Clientes buscarPorId(Integer idCliente) throws Exception {
		Clientes cliente = manager.find(Clientes.class, idCliente);
		if (cliente == null) {
			throw new Exception("Cliente não encontrado!");
		}
		return cliente;
	}

	public List<Clientes> buscarPorPropriedade(String whereClause, Object parametro) {
		return manager.createQuery("select c from Clientes c " + whereClause + parametro, Clientes.class)
				.getResultList();
	}

	public List<Clientes> buscarTodos() {
		return manager.createQuery("select c from Clientes c", Clientes.class).getResultList();
	}

	public void deletar(Integer idCliente) throws Exception {
		Clientes cliente = buscarPorId(idCliente);
		if (vendas.existeComPropriedade("where v.cliente = ", idCliente)) {
			throw new Exception("Existe vendas com este cliente!");
		}

		if (cliente != null) {
			manager.remove(cliente);
		} else {
			throw new Exception("Cliente não encontrado!");
		}
	}

	public Clientes salvar(Clientes entity) throws Exception {
		validar(entity);
		manager.persist(entity);
		return entity;
	}

	private void validar(Clientes entity) throws Exception {
		if (entity.getCpf() == null || entity.getCpf().isEmpty() || entity.getCpf().length() != 11) {
			throw new Exception("Um CPF válido deve ser informado!");
		}
		if (entity.getNome() == null || entity.getNome().isEmpty() || entity.getNome().length() > 50) {
			throw new Exception("O nome deve ser informado!");
		}
		if (entity.getTelefone() == null || entity.getTelefone().isEmpty() || entity.getTelefone().length() > 12) {
			throw new Exception("Deve ser informado um telefone válido!");
		}
		if (entity.getAtivo() == null || entity.getAtivo().compareTo(1) > 0 || entity.getAtivo().compareTo(0) < 0) {
			throw new Exception("Informe se o cliente está ativo! 0 - Inativo ou 1 - Ativo");
		}
	}

}
