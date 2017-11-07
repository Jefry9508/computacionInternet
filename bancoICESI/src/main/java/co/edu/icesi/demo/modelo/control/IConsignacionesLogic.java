package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;

@Local
public interface IConsignacionesLogic {

	public void save(Consignaciones entity) throws Exception;

	public void update(Consignaciones entity) throws Exception;

	public void delete(Consignaciones entity) throws Exception;

	public Consignaciones findById(ConsignacionesId entity) throws Exception;

	public List<Consignaciones> findAll() throws Exception;

}
