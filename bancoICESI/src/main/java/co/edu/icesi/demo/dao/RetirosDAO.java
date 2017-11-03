package co.edu.icesi.demo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;

@Stateless
public class RetirosDAO implements IRetirosDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Retiros entity) {
		entityManager.persist(entity);

	}

	@Override
	public void update(Retiros entity) {
		entityManager.merge(entity);

	}

	@Override
	public void delete(Retiros entity) {
		entityManager.remove(entity);

	}

	@Override
	public Retiros findById(RetirosId id) {

		return entityManager.find(Retiros.class, id);
	}

	@Override
	public List<Retiros> findAll() {
		String jpql = "SELECT ret FROM Retiros ret";
		return entityManager.createQuery(jpql).getResultList();
	}

}
