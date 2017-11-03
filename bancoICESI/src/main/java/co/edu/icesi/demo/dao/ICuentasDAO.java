package co.edu.icesi.demo.dao;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Cuentas;

@Local
public interface ICuentasDAO {

	public void save(Cuentas entity);

	public void update(Cuentas entity);

	public void delete(Cuentas entity);

	public Cuentas findById(String id);

	public List<Cuentas> findAll();

}
