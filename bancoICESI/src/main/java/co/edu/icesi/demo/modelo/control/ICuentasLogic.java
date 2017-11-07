package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Cuentas;

@Local
public interface ICuentasLogic {

	public void save(Cuentas entity) throws Exception;

	public void update(Cuentas entity) throws Exception;

	public void delete(String id) throws Exception;

	public Cuentas findById(String id) throws Exception;

	public List<Cuentas> findAll() throws Exception;

}
