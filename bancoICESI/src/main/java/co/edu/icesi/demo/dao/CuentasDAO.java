package co.edu.icesi.demo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.demo.modelo.Cuentas;

@Stateless
public class CuentasDAO implements ICuentasDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Cuentas entity) {
		entityManager.persist(entity);

	}

	@Override
	public void update(Cuentas entity) {
		entityManager.merge(entity);

	}

	@Override
	public void delete(Cuentas entity) {
		entityManager.remove(entity);

	}

	@Override
	public Cuentas findById(String id) {

		return entityManager.find(Cuentas.class, id);
	}

	@Override
	public List<Cuentas> findAll() {
		String jpql = "SELECT cue FROM Cuentas cue";
		return entityManager.createQuery(jpql).getResultList();
	}

}
