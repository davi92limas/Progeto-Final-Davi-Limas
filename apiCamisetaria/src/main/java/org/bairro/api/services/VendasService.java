package org.bairro.api.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bairro.api.entidades.Camisas;
import org.bairro.api.entidades.Clientes;
import org.bairro.api.entidades.Funcionarios;
import org.bairro.api.entidades.Vendas;

@Stateless
public class VendasService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private CamisasService camisaService;
	
	public Vendas atualizar(Vendas venda) throws Exception {
		validaCadastro(venda);
		manager.merge(venda);
		return venda;
	}

	public Vendas buscarPorId(Integer idVenda) throws Exception {
		Vendas venda = manager.find(Vendas.class, idVenda);
		if (venda == null) {
			throw new Exception("Venda não encontrada!");
		}
		return venda;
	}

	public List<Vendas> buscarPorCamisa(Integer idCamisa) {
		return manager.createQuery("select v from Vendas v where v.camisa1 = " + idCamisa + " or v.camisa2 = "
				+ idCamisa + " or v.camisa3 = " + idCamisa, Vendas.class).getResultList();
	}

	public List<Vendas> buscarPorPropriedade(String whereClause, Object parametro) {
		return manager.createQuery("select v from Vendas v " + whereClause + parametro, Vendas.class).getResultList();
	}

	public List<Vendas> buscarTodos() {
		return manager.createQuery("select v from Vendas v", Vendas.class).getResultList();
	}

	public void deletar(Integer idVenda) throws Exception {
		Vendas venda = buscarPorId(idVenda);

		if (venda != null) {
			manager.remove(venda);
		} else {
			throw new Exception("Venda não encontrada!");
		}
	}

	public boolean existeComPropriedade(String whereClause, Object parametro) {
		if (quantidadeComPropriedade(whereClause, parametro) > 0) {
			return true;
		}
		return false;
	}

	public Long quantidadeComPropriedade(String whereClause, Object parametro) {
		return (Long) manager.createQuery("select COUNT(v.idVenda) from Vendas v " + whereClause + parametro)
				.getSingleResult();
	}

	public Vendas salvar(Vendas venda) throws Exception {
		validaCadastro(venda);
		manager.persist(venda);
		return venda;
	}

	private void validaFuncionarioInformado(Funcionarios funcionario) throws Exception {
		if (funcionario == null) {
			throw new Exception("O funcionário não foi informado!");
		}

	}

	private void validaCamisasInformados(Camisas camisa1, Camisas camisa2, Camisas camisa3) throws Exception {
		if (camisa1 == null && camisa2 == null && camisa3 == null) {
			throw new Exception("Nenhuma camisa foi infomardo!");
		}
		if (camisa1 != null) {
			camisaService.buscarPorId(camisa1.getIdCamisa());
		}
		if (camisa2 != null) {
			camisaService.buscarPorId(camisa2.getIdCamisa());
		}
		if (camisa3 != null) {
			camisaService.buscarPorId(camisa3.getIdCamisa());
		}
	}

	private void validaClienteInformado(Clientes cliente) throws Exception {
		if (cliente == null) {
			throw new Exception("O cliente não foi informado!");
		}
	}
	
	private void validaCadastro(Vendas venda) throws Exception {
		validaFuncionarioInformado(venda.getFuncionario());

		validaCamisasInformados(venda.getCamisa1(), venda.getCamisa2(), venda.getCamisa3());

		validaClienteInformado(venda.getCliente());
		
		if (venda.getDataVenda() == null) {
			throw new Exception("A data da venda não foi informada!");
		}
	}
}
