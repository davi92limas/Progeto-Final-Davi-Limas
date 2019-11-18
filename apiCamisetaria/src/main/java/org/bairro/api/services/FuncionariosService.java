package org.bairro.api.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bairro.api.entidades.Funcionarios;
import org.bairro.api.resources.VendasResource;


@Stateless
public class FuncionariosService {

	@Inject
	private VendasResource vendas;

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	public Funcionarios atualizar(Funcionarios funcionario) throws Exception {
		validar(funcionario);
		manager.merge(funcionario);
		return funcionario;
	}
	
	public Funcionarios buscarPorId(Integer idFuncionario) throws Exception {
		Funcionarios funcionario = manager.find(Funcionarios.class, idFuncionario);
		if (funcionario == null) {
			throw new Exception("Funcionario não encontrado!");
		}
		return funcionario;
	}

	public List<Funcionarios> buscarPorPropriedade(String whereClause, Object parametro) {
		return manager.createQuery("select f from Funcionarios f " + whereClause + parametro, Funcionarios.class).getResultList();
	}
	
	public List<Funcionarios> buscarTodos() {
		return manager.createQuery("select f from Funcionarios f", Funcionarios.class).getResultList();
	}

	public void deletar(Integer idFuncionario) throws Exception {
		Funcionarios funcionario = buscarPorId(idFuncionario);
		if (vendas.existeComPropriedade("where f.funcionario = ", idFuncionario)) {
			throw new Exception("Existe vendas com este funcionário!");
		}
		
		if (funcionario != null) {
			manager.remove(funcionario);
		} else {
			throw new Exception("Funcionario não encontrado!");
		}
	}

	public Long quantidadeComPropriedade(String whereClause, Object parametro) {
		return (Long) manager.createQuery("select count(f.idFuncionario) from Funcionarios f " + whereClause + parametro).getSingleResult();
	}

	public Funcionarios salvar(Funcionarios funcionario) throws Exception {
		validar(funcionario);
		manager.persist(funcionario);
		return funcionario;
	}

	private void validar(Funcionarios entity) throws Exception {
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
