package org.bairro.api.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bairro.api.entidades.Camisas;



@Stateless
public class CamisasService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public Camisas atualizar(Camisas camisa) {
		manager.merge(camisa);
		return camisa;
	}

	public Camisas buscarPorId(Integer idCamisa) throws Exception {
		Camisas camisa = manager.find(Camisas.class, idCamisa);
		if (camisa == null) {
			throw new Exception("camisa " + idCamisa + " não encontrada!");
		}
		return camisa;
	}

	public List<Camisas> buscarPorPropriedade(String whereClause, Object parametro) {
		return manager.createQuery("select c from Camisas c " + whereClause + parametro, Camisas.class).getResultList();
	}

	public List<Camisas> buscarTodos() {
		return manager.createQuery("select c from Camisas c", Camisas.class).getResultList();
	}

	public void deletar(Integer idCamisa) throws Exception {
		Camisas camisa = buscarPorId(idCamisa);
		if (camisa != null) {
			manager.remove(camisa);
		} else {
			throw new Exception("Camisa não encontrado!");
		}
	}

	
	
	public Long quantidadeComPropriedade(String whereClause, Object parametro) {
		return (Long) manager.createQuery("select COUNT(c.idCamisa) from Camisas c " + whereClause + parametro).getSingleResult();
	}

	public Camisas salvar(Camisas camisa) {
		manager.persist(camisa);
		return camisa;
	}

}
