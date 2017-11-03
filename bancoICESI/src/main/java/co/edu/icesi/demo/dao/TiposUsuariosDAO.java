package co.edu.icesi.demo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.demo.modelo.TiposUsuarios;

@Stateless
public class TiposUsuariosDAO implements ITiposUsuariosDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TiposUsuarios entity) {
		entityManager.persist(entity);

	}

	@Override
	public void update(TiposUsuarios entity) {
		entityManager.merge(entity);

	}

	@Override
	public void delete(TiposUsuarios entity) {
		entityManager.remove(entity);

	}

	@Override
	public TiposUsuarios findById(Long id) {

		return entityManager.find(TiposUsuarios.class, id);
	}

	@Override
	public List<TiposUsuarios> findAll() {
		String jpql = "SELECT tUsu FROM TiposUsuarios tUsu";
		return entityManager.createQuery(jpql).getResultList();
	}

}
