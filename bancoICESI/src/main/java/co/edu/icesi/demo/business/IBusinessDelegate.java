package co.edu.icesi.demo.business;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;
import co.edu.icesi.demo.modelo.TiposDocumentos;
import co.edu.icesi.demo.modelo.TiposUsuarios;
import co.edu.icesi.demo.modelo.Usuarios;

@Local
public interface IBusinessDelegate {

	// ---------Usuarios--------------
	public void save(Usuarios entity);

	public void update(Usuarios entity);

	public void delete(Long id);

	public Usuarios findById(Long id);

	public List<Usuarios> findAllUsuarios();

	// ---------Tipos Usuarios----------------
	public void saveTiposUsuarios(TiposUsuarios entity);

	public List<TiposUsuarios> findAllTiposUsuarios();

	public void updateTiposUsuarios(TiposUsuarios entity);

	public void deleteTiposUsuarios(Long codigo);

	public TiposUsuarios findByIdTiposUsuarios(Long id);

	// -------------Clientes----------------------------
	public void saveClientes(Clientes entity) throws Exception;

	public void updateClientes(Clientes entity) throws Exception;

	public void deleteClientes(Long codigo) throws Exception;

	public List<Clientes> getClientes() throws Exception;

	public Clientes getClientesById(Long codigo) throws Exception;

	public List<Clientes> findByProperty(String propertyName, Object value);

	// -------------Tipos Documentos-------------------
	public void saveTiposDocumentos(TiposDocumentos entity) throws Exception;

	public void updateTiposDocumentos(TiposDocumentos entity) throws Exception;

	public void deleteTiposDocumentos(Long codigo) throws Exception;

	public List<TiposDocumentos> getTiposDocumentos() throws Exception;

	public TiposDocumentos getTiposDocumentosById(Long codigo) throws Exception;

	// -------------------Cuentas-------------------------------
	public void saveCuentas(Cuentas entity) throws Exception;

	public void updateCuentas(Cuentas entity) throws Exception;

	public void deleteCuentas(String id) throws Exception;

	public Cuentas findByIdCuentas(String id) throws Exception;

	public List<Cuentas> findAllCuentas() throws Exception;

	// -------------------------Consignaciones----------------------------------
	public void saveConsignaciones(Consignaciones entity) throws Exception;

	public void updateConsignaciones(Consignaciones entity) throws Exception;

	public void deleteConsignaciones(Consignaciones entity) throws Exception;

	public Consignaciones findByIdConsignaciones(ConsignacionesId entity) throws Exception;

	public List<Consignaciones> findAllConsignaciones() throws Exception;

	// ----------------------------Retiros--------------------------------------
	public void saveRetiros(Retiros entity) throws Exception;

	public void updateRetiros(Retiros entity) throws Exception;

	public void deleteRetiros(RetirosId entity) throws Exception;

	public Retiros findByIdRetiros(RetirosId entity) throws Exception;

	public List<Retiros> findAllRetiros() throws Exception;

}
