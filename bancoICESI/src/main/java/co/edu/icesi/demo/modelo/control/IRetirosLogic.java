package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;

@Local
public interface IRetirosLogic {

	public void save(Retiros entity) throws Exception;

	public void update(Retiros entity) throws Exception;

	public void delete(RetirosId entity) throws Exception;

	public Retiros findById(RetirosId entity) throws Exception;

	public List<Retiros> findAll() throws Exception;

}
