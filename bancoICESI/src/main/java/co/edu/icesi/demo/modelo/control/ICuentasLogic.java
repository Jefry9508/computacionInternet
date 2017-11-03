package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Cuentas;

@Local
public interface ICuentasLogic {

	public void save(Cuentas entity);

	public void update(Cuentas entity);

	public void delete(String id);

	public Cuentas findById(String id);

	public List<Cuentas> findAll();

}
