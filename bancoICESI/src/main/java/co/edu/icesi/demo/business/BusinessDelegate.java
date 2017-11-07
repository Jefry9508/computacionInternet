package co.edu.icesi.demo.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;
import co.edu.icesi.demo.modelo.TiposDocumentos;
import co.edu.icesi.demo.modelo.TiposUsuarios;
import co.edu.icesi.demo.modelo.Usuarios;
import co.edu.icesi.demo.modelo.control.IClientesLogic;
import co.edu.icesi.demo.modelo.control.IConsignacionesLogic;
import co.edu.icesi.demo.modelo.control.ICuentasLogic;
import co.edu.icesi.demo.modelo.control.IRetirosLogic;
import co.edu.icesi.demo.modelo.control.ITiposDocumentosLogic;
import co.edu.icesi.demo.modelo.control.ITiposUsuariosLogic;
import co.edu.icesi.demo.modelo.control.IUsuariosLogic;

@Stateless
public class BusinessDelegate implements IBusinessDelegate {

	@EJB
	private IUsuariosLogic usuarioLogic;

	@EJB
	private ITiposUsuariosLogic tiposUsuariosLogic;

	@EJB
	private IClientesLogic clienteLogic;

	@EJB
	private ITiposDocumentosLogic tiposDocumentosLogic;

	@EJB
	private ICuentasLogic cuentasLogic;

	@EJB
	private IConsignacionesLogic consignacionesLogic;

	@EJB
	private IRetirosLogic retirosLogic;

	@Override
	public void save(Usuarios entity) {
		// TODO Auto-generated method stub

		usuarioLogic.save(entity);

	}

	@Override
	public void update(Usuarios entity) {
		// TODO Auto-generated method stub
		usuarioLogic.update(entity);

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		usuarioLogic.delete(id);

	}

	@Override
	public Usuarios findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioLogic.findById(id);
	}

	@Override
	public List<Usuarios> findAllUsuarios() {
		// TODO Auto-generated method stub
		return usuarioLogic.findAll();
	}

	@Override
	public List<TiposUsuarios> findAllTiposUsuarios() {
		// TODO Auto-generated method stub
		return tiposUsuariosLogic.findAll();
	}

	@Override
	public void saveClientes(Clientes entity) throws Exception {
		clienteLogic.saveClientes(entity);

	}

	@Override
	public void updateClientes(Clientes entity) throws Exception {
		clienteLogic.updateClientes(entity);

	}

	@Override
	public void deleteClientes(Long codigo) throws Exception {
		clienteLogic.deleteClientes(codigo);

	}

	@Override
	public List<Clientes> getClientes() throws Exception {
		// TODO Auto-generated method stub
		return clienteLogic.getClientes();
	}

	@Override
	public Clientes getClientesById(Long codigo) throws Exception {
		// TODO Auto-generated method stub
		return clienteLogic.getClientesById(codigo);
	}

	@Override
	public List<Clientes> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return clienteLogic.findByProperty(propertyName, Clientes.class);
	}

	@Override
	public void saveTiposDocumentos(TiposDocumentos entity) throws Exception {
		tiposDocumentosLogic.saveTiposDocumentos(entity);

	}

	@Override
	public void updateTiposDocumentos(TiposDocumentos entity) throws Exception {
		tiposDocumentosLogic.updateTiposDocumentos(entity);

	}

	@Override
	public void deleteTiposDocumentos(Long codigo) throws Exception {
		tiposDocumentosLogic.deleteTiposDocumentos(codigo);

	}

	@Override
	public List<TiposDocumentos> getTiposDocumentos() throws Exception {
		// TODO Auto-generated method stub
		return tiposDocumentosLogic.getTiposDocumentos();
	}

	@Override
	public TiposDocumentos getTiposDocumentosById(Long codigo) throws Exception {
		// TODO Auto-generated method stub
		return tiposDocumentosLogic.getTiposDocumentosById(codigo);
	}

	@Override
	public void saveTiposUsuarios(TiposUsuarios entity) {
		tiposUsuariosLogic.save(entity);

	}

	@Override
	public void updateTiposUsuarios(TiposUsuarios entity) {
		tiposUsuariosLogic.update(entity);

	}

	@Override
	public void deleteTiposUsuarios(Long codigo) {
		tiposUsuariosLogic.delete(codigo);

	}

	@Override
	public TiposUsuarios findByIdTiposUsuarios(Long id) {
		// TODO Auto-generated method stub
		return tiposUsuariosLogic.findById(id);
	}

	@Override
	public void saveCuentas(Cuentas entity) throws Exception {
		cuentasLogic.save(entity);

	}

	@Override
	public void updateCuentas(Cuentas entity) throws Exception {
		cuentasLogic.update(entity);

	}

	@Override
	public void deleteCuentas(String id) throws Exception {
		cuentasLogic.delete(id);

	}

	@Override
	public Cuentas findByIdCuentas(String id) throws Exception {
		// TODO Auto-generated method stub
		return cuentasLogic.findById(id);
	}

	@Override
	public List<Cuentas> findAllCuentas() throws Exception {
		// TODO Auto-generated method stub
		return cuentasLogic.findAll();
	}

	@Override
	public void saveConsignaciones(Consignaciones entity) {
		consignacionesLogic.save(entity);

	}

	@Override
	public void updateConsignaciones(Consignaciones entity) {
		consignacionesLogic.update(entity);

	}

	@Override
	public void deleteConsignaciones(Consignaciones entity) {
		consignacionesLogic.delete(entity);

	}

	@Override
	public Consignaciones findByIdConsignaciones(ConsignacionesId entity) {

		return consignacionesLogic.findById(entity);
	}

	@Override
	public List<Consignaciones> findAllConsignaciones() {
		// TODO Auto-generated method stub
		return consignacionesLogic.findAll();
	}

	@Override
	public void saveRetiros(Retiros entity) throws Exception {
		retirosLogic.save(entity);

	}

	@Override
	public void updateRetiros(Retiros entity) throws Exception {
		retirosLogic.update(entity);

	}

	@Override
	public void deleteRetiros(RetirosId entity) throws Exception {
		retirosLogic.delete(entity);

	}

	@Override
	public Retiros findByIdRetiros(RetirosId entity) throws Exception {
		// TODO Auto-generated method stub
		return retirosLogic.findById(entity);
	}

	@Override
	public List<Retiros> findAllRetiros() throws Exception {
		// TODO Auto-generated method stub
		return retirosLogic.findAll();
	}

}
