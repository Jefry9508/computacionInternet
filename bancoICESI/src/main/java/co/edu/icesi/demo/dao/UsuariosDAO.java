package co.edu.icesi.demo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.demo.modelo.Usuarios;

@Stateless
public class UsuariosDAO implements IUsuariosDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Usuarios entity) {
		entityManager.persist(entity);

	}

	@Override
	public void update(Usuarios entity) {
		entityManager.merge(entity);

	}

	@Override
	public void delete(Usuarios entity) {
		entityManager.remove(entity);

	}

	@Override
	public Usuarios findById(Long id) {

		return entityManager.find(Usuarios.class, id);
	}

	@Override
	public List<Usuarios> findAll() {
		String jpql = "SELECT usu FROM Usuarios usu";
		return entityManager.createQuery(jpql).getResultList();
	}

}
