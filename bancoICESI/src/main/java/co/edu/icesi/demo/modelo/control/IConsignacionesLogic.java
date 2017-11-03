package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;

@Local
public interface IConsignacionesLogic {

	public void save(Consignaciones entity);

	public void update(Consignaciones entity);

	public void delete(Consignaciones entity);

	public Consignaciones findById(ConsignacionesId entity);

	public List<Consignaciones> findAll();

}
