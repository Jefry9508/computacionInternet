package co.edu.icesi.demo.modelo.control;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.dao.IClientesDAO;
import co.edu.icesi.demo.dao.ICuentasDAO;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.utilities.Utilities;

@Stateless
public class ClientesLogic implements IClientesLogic {

	private static final Logger log = LoggerFactory.getLogger(ClientesLogic.class);

	@EJB
	private IClientesDAO clientesDAO;

	@EJB
	private ICuentasDAO cuentasDAO;

	@EJB
	private ICuentasLogic cuentasLogic;

	@EJB
	private IConsignacionesLogic consignacionesLogic;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveClientes(Clientes entity) throws Exception {
		try {

			log.info("inicia saveClientes");

			if (entity.getCliId() == 0) {
				throw new Exception("El id del cliente es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale("" + entity.getCliId(), 10, 0)) {
				throw new Exception("El tamaño del id del cliente no debe ser mayor a 10 digitos");
			}

			if (getClientesById(entity.getCliId()) != null) {
				throw new Exception("Ya existe un tipo de documento con el código " + entity.getCliId());
			}

			if (entity.getCliNombre() == null || entity.getCliNombre().equals("")) {
				throw new Exception("El nombre del cliente es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength("" + entity.getCliNombre(), 50)) {
				throw new Exception("El tamaño del nombre del cliente no debe ser mayor a 50 digitos");
			}

			if (entity.getTiposDocumentos() == null) {
				throw new Exception("El tipo de documento es obligatorio");
			}

			if (entity.getCliDireccion() == null || entity.getCliDireccion().equals("")) {
				throw new Exception("La dirección del cliente es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength("" + entity.getCliDireccion(), 50)) {
				throw new Exception("El tamaño de la dirección del cliente no debe ser mayor a 50 digitos");
			}

			if (entity.getCliTelefono() == null || entity.getCliTelefono().equals("")) {
				throw new Exception("El teléfono del cliente es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength("" + entity.getCliTelefono(), 50)) {
				throw new Exception("El tamaño del teléfono del cliente no debe ser mayor a 10 digitos");
			}

			clientesDAO.save(entity);
			log.info("Guardó satisfactoriamente");

		} catch (Exception e) {
			log.error("saveClientes falló", e);

		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateClientes(Clientes entity) throws Exception {
		try {

			log.info("inicia updateClientes");

			if (entity.getCliId() == 0) {
				throw new Exception("El id del cliente es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale("" + entity.getCliId(), 10, 0)) {
				throw new Exception("El tamaño del id del cliente no debe ser mayor a 10 digitos");
			}

			if (getClientesById(entity.getCliId()) == null) {
				throw new Exception("No existe un cliente con el código " + entity.getCliId());
			}

			if (entity.getCliNombre() == null || entity.getCliNombre().equals("")) {
				throw new Exception("El nombre del cliente es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale("" + entity.getCliNombre(), 50, 0)) {
				throw new Exception("El tamaño del nombre del cliente no debe ser mayor a 50 digitos");
			}

			if (entity.getTiposDocumentos() != null) {
				throw new Exception("El tipo de documento es obligatorio");
			}

			if (entity.getCliDireccion() == null || entity.getCliDireccion().equals("")) {
				throw new Exception("La dirección del cliente es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale("" + entity.getCliDireccion(), 50, 0)) {
				throw new Exception("El tamaño de la dirección del cliente no debe ser mayor a 50 digitos");
			}

			if (entity.getCliTelefono() == null || entity.getCliTelefono().equals("")) {
				throw new Exception("El teléfono del cliente es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale("" + entity.getCliTelefono(), 50, 0)) {
				throw new Exception("El tamaño del teléfono del cliente no debe ser mayor a 10 digitos");
			}

			clientesDAO.update(entity);
			log.info("Modificó satisfactoriamente");
		} catch (Exception e) {
			log.error("updateClientes falló", e);

		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteClientes(Long codigo) throws Exception {
		try {
			log.info("deleteClientes inició");
			if (codigo == null || codigo == 0) {
				throw new Exception("El código del cliente es obligatorio");
			}

			Clientes clientes = clientesDAO.findById(codigo);

			if (clientes == null) {
				throw new Exception("No existe un cliente con el código " + codigo);
			}

			Set<Cuentas> cuentas = clientes.getCuentases();

			if (cuentas != null && !cuentas.isEmpty()) {
				throw new Exception(
						"El cliente con el id " + codigo + " no se puede eliminar porque" + " tiene cuentas asociadas");
			}

			clientesDAO.delete(clientes);

			log.info("eliminó satisfactoriamente");

		} catch (Exception e) {
			log.error("deleteClientes", e);
		}

	}

	@TransactionAttribute
	public List<Clientes> getClientes() throws Exception {
		return clientesDAO.findAll();
	}

	@TransactionAttribute
	public Clientes getClientesById(Long codigo) throws Exception {
		Clientes clientes = null;

		try {
			log.info("getClientesById inició");

			if (codigo == null || codigo == 0) {
				throw new Exception("El código del tipo de documento es obligatorio");
			}

			clientes = clientesDAO.findById(codigo);

		} catch (Exception e) {
			log.error("getClientesById falló", e);
		}

		return clientes;
	}

	@TransactionAttribute
	public List<Clientes> findByProperty(String propertyName, Object value) {

		try {
			log.error("findByProperty inició");

			if (propertyName == null || propertyName.equals("")) {
				throw new Exception("No se escribió la carácteristica de comparación");
			}

			if (value == null) {
				throw new Exception("No hay un valor de comparación");
			}

			List<Clientes> clientes = clientesDAO.findByProperty(propertyName, value);
		} catch (Exception e) {
			log.error("findByProperty falló", e);
		}

		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registro(Clientes clientes, List<Cuentas> cuentas) {
		try {
			if (clientes != null) {
				if (getClientesById(clientes.getCliId()) == null) {
					saveClientes(clientes);
				} else {
					updateClientes(clientes);
				}
			}

			if (cuentas != null && !cuentas.isEmpty()) {
				for (Cuentas cuen : cuentas) {
					Consignaciones cons = null;
					if (cuentasLogic.findById(cuen.getCueNumero()) == null) {
						cons = new Consignaciones();
						cons.setCuentas(cuen);
						cons.setId(new ConsignacionesId(90L, cuen.getCueNumero()));
						cons.setConDescripcion("APERTURA DE CUENTA");
						cons.setConFecha(new Date());
						BigDecimal bg = new BigDecimal("100000");
						cons.setConValor(bg);
						consignacionesLogic.save(cons);
						cuen.setCueSaldo(cuen.getCueSaldo().add(bg));
						cuentasLogic.save(cuen);
					} else {
						cons.setCuentas(cuen);
						cons.setId(new ConsignacionesId(100L, cuen.getCueNumero()));
						cons.setConDescripcion("APERTURA DE CUENTA");
						cons.setConFecha(new Date());
						BigDecimal bg = new BigDecimal("100000");
						cons.setConValor(bg);
						consignacionesLogic.save(cons);
						cuen.setCueSaldo(cuen.getCueSaldo().add(bg));
						cuentasLogic.update(cuen);
					}
				}
			}

		} catch (Exception e) {
			log.error("Error", e);
		}
	}

}
