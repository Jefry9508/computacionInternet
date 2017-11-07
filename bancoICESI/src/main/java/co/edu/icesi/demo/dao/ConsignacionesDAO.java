package co.edu.icesi.demo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;

@Stateless
public class ConsignacionesDAO implements IConsignacionesDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Consignaciones entity) throws Exception {
		entityManager.persist(entity);

	}

	@Override
	public void update(Consignaciones entity) throws Exception {
		entityManager.merge(entity);

	}

	@Override
	public void delete(Consignaciones entity) throws Exception {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));

	}

	@Override
	public Consignaciones findById(ConsignacionesId entity) throws Exception {

		return entityManager.find(Consignaciones.class, entity);
	}

	@Override
	public List<Consignaciones> findAll() throws Exception {
		String jpql = "SELECT con FROM Consignaciones con";
		return entityManager.createQuery(jpql).getResultList();
	}

}
