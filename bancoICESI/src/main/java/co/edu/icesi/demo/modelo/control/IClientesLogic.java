package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Clientes;

@Local
public interface IClientesLogic {

	public void saveClientes(Clientes entity) throws Exception;

	public void updateClientes(Clientes entity) throws Exception;

	public void deleteClientes(Long codigo) throws Exception;

	public List<Clientes> getClientes() throws Exception;

	public Clientes getClientesById(Long codigo) throws Exception;

	public List<Clientes> findByProperty(String propertyName, Object value);

}