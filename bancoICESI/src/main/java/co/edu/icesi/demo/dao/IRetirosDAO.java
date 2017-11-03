package co.edu.icesi.demo.dao;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;

@Local
public interface IRetirosDAO {

	public void save(Retiros entity);

	public void update(Retiros entity);

	public void delete(Retiros entity);

	public Retiros findById(RetirosId entity);

	public List<Retiros> findAll();

}
