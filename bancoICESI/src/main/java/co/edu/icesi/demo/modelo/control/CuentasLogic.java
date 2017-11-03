package co.edu.icesi.demo.modelo.control;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.dao.ICuentasDAO;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.utilities.Utilities;

@Stateless
public class CuentasLogic implements ICuentasLogic {

	private static final Logger log = LoggerFactory.getLogger(ClientesLogic.class);

	@EJB
	private ICuentasDAO cuentasDAO;

	@EJB
	private IClientesLogic clientesDAO;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(Cuentas entity) {
		try {

			log.info("inicia saveCuentas");

			if (entity.getCueNumero() == null || entity.getCueNumero().equals("")) {
				throw new Exception("El número de cuenta es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength("" + entity.getCueNumero(), 30)) {
				throw new Exception("El tamaño del nombre del cliente no debe ser mayor a 50 digitos");
			}

			Cuentas cuentas = findById(entity.getCueNumero());

			if (cuentas != null) {
				throw new Exception("Ya existe una cuenta con el número " + entity.getCueNumero());
			}

			if (entity.getClientes() == null) {
				throw new Exception("No hay un cliente asociado a la cuenta");
			}

			Clientes clientes = clientesDAO.getClientesById(entity.getClientes().getCliId());

			if (clientes == null) {
				throw new Exception("No existe un cliente con el id " + entity.getClientes().getCliId());
			}

			if (entity.getCueSaldo() == null) {
				throw new Exception("La cuenta debe tener un saldo asociado");
			}

			if (entity.getCueSaldo().doubleValue() < 0) {
				throw new Exception("El saldo de la cuenta no puede ser negativo");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getCueSaldo().toString(), 10, 2)) {
				throw new Exception("El valor de la cuenta no debe ser mayor a 10 digitos");
			}

			if (entity.getCueActiva() == null || entity.getCueActiva().equals("")) {
				throw new Exception("Se debe especificar el estado de la cuenta");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getCueActiva(), 2)) {
				throw new Exception("El estado de la cuenta permite máximo dos caracteres");
			}

			if (entity.getCueClave() == null || entity.getCueClave().equals("")) {
				throw new Exception("Debe asiganarle una clave a su cuenta");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getCueClave(), 50)) {
				throw new Exception("La clave permite máximo 50 caracteres");
			}

			cuentasDAO.save(entity);
			log.info("guardó satisfactoriamente");

		} catch (Exception e) {
			log.error("saveCuentas falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Cuentas entity) {
		try {

			log.info("inicia saveCuentas");

			if (entity.getCueNumero() == null || entity.getCueNumero().equals("")) {
				throw new Exception("El número de cuenta es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength("" + entity.getCueNumero(), 30)) {
				throw new Exception("El tamaño del nombre del cliente no debe ser mayor a 50 digitos");
			}

			Cuentas cuentas = cuentasDAO.findById(entity.getCueNumero());

			if (cuentas == null) {
				throw new Exception("No existe una cuenta con el número " + entity.getCueNumero());
			}

			if (entity.getClientes() == null) {
				throw new Exception("No hay un cliente asociado a la cuenta");
			}

			Clientes clientes = clientesDAO.getClientesById(entity.getClientes().getCliId());

			if (clientes == null) {
				throw new Exception("No existe un cliente con el id " + entity.getClientes().getCliId());
			}

			if (entity.getCueSaldo() == null) {
				throw new Exception("La cuenta debe tener un saldo asociado");
			}

			if (entity.getCueSaldo().doubleValue() < 0) {
				throw new Exception("El saldo de la cuenta no puede ser negativo");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getCueSaldo().toString(), 10, 2)) {
				throw new Exception("El valor de la cuenta no debe ser mayor a 10 digitos");
			}

			if (entity.getCueActiva() == null || entity.getCueActiva().equals("")) {
				throw new Exception("Se debe especificar el estado de la cuenta");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getCueActiva(), 2)) {
				throw new Exception("El estado de la cuenta permite máximo dos caracteres");
			}

			if (entity.getCueClave() == null || entity.getCueClave().equals("")) {
				throw new Exception("Debe asiganarle una clave a su cuenta");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getCueClave(), 50)) {
				throw new Exception("La clave permite máximo 50 caracteres");
			}

			cuentasDAO.update(entity);
			log.info("actualizó satisfactoriamente");

		} catch (Exception e) {
			log.error("updateCuentas falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(String num) {
		try {
			log.info("deleteCuentas inició");
			if (num == null || num.equals("")) {
				throw new Exception("El id de la cuenta es obligatorio");
			}

			Cuentas cuentas = cuentasDAO.findById(num);

			if (cuentas == null) {
				throw new Exception("No existe ninguna cuenta con el número " + num);
			}

			Set<Consignaciones> consignaciones = cuentas.getConsignacioneses();
			Set<Retiros> retiros = cuentas.getRetiroses();

			if (consignaciones != null && !consignaciones.isEmpty()) {
				throw new Exception(
						"La cuenta número " + num + " no se puede eliminar porque" + " tiene cosignaciones asociadas");
			}

			if (retiros != null && !retiros.isEmpty()) {
				throw new Exception(
						"La cuenta número " + num + " no se puede eliminar porque" + " tiene retiros asociados");
			}

			cuentasDAO.delete(cuentas);
			log.info("Eliminó satisfactoriamente");

		} catch (Exception e) {
			log.error("deleteCuentas falló", e);
		}

	}

	@TransactionAttribute
	public Cuentas findById(String id) {
		Cuentas cuentas = null;

		try {
			log.info("findByIdCuentas inició");

			if (id == null || id.equals("")) {
				throw new Exception("El id de la cuenta es obligatorio");
			}

			cuentas = cuentasDAO.findById(id);

		} catch (Exception e) {
			log.error("findByIdCuentas falló", e);
		}
		return cuentas;
	}

	@TransactionAttribute
	public List<Cuentas> findAll() {

		return cuentasDAO.findAll();
	}

}
