package co.edu.icesi.demo.dao;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;

@Local
public interface IConsignacionesDAO {

	public void save(Consignaciones entity);

	public void update(Consignaciones entity);

	public void delete(Consignaciones entity);

	public Consignaciones findById(ConsignacionesId entity);

	public List<Consignaciones> findAll();

}
