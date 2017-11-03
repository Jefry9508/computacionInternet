package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;

@Local
public interface IRetirosLogic {

	public void save(Retiros entity);

	public void update(Retiros entity);

	public void delete(RetirosId entity);

	public Retiros findById(RetirosId entity);

	public List<Retiros> findAll();

}
