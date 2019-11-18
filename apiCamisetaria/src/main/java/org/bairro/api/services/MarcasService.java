package org.bairro.api.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bairro.api.entidades.Marcas;

@Stateless
public class MarcasService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public Marcas atualizar(Marcas marca) {
		manager.merge(marca);
		return marca;
	}

	public Marcas buscarPorId(Integer idMarca) throws Exception {
		Marcas marca = manager.find(Marcas.class, idMarca);
		if (marca == null) {
			throw new Exception("marca não encontrado!");
		}
		return marca;
	}

	public List<Marcas> buscarTodos() {
		return manager.createQuery("select t from Marcas t", Marcas.class).getResultList();
	}

	public void deletar(Integer idMarca) throws Exception {
		Marcas marca = buscarPorId(idMarca);
		if (marca != null) {
			manager.remove(marca);
		} else {
			throw new Exception("marca não encontrado!");
		}
	}

	public Long quantidadeComPropriedade(String whereClause, Object parametro) {
		return (Long) manager.createQuery("select COUNT(t.idMarca) from Marcas t " + whereClause + parametro)
				.getSingleResult();
	}

	public Marcas salvar(Marcas marca) {
		manager.persist(marca);
		return marca;
	}

}
