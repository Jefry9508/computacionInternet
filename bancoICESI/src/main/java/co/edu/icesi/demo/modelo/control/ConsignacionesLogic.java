package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.dao.IConsignacionesDAO;
import co.edu.icesi.demo.dao.ICuentasDAO;
import co.edu.icesi.demo.dao.IUsuariosDAO;
import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.modelo.Usuarios;
import co.edu.icesi.demo.utilities.Utilities;

@Stateless
public class ConsignacionesLogic implements IConsignacionesLogic {

	private static final Logger log = LoggerFactory.getLogger(ClientesLogic.class);

	@EJB
	private IConsignacionesDAO consignacionesDAO;

	@EJB
	private ICuentasDAO cuentasDAO;

	@EJB
	private IUsuariosDAO usuariosDAO;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(Consignaciones entity) throws Exception {
		try {
			log.info("saveConsignaciones inició");

			if (entity == null) {
				throw new Exception("Debe especificarse un paramentro");
			}

			if (entity.getId() == null) {
				throw new Exception("La consignación no tiene una identificación asociada");
			}

			if (entity.getId().getConCodigo() == 0) {
				throw new Exception("El id de la consignación es obligatoria");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getId().getConCodigo() + "", 10, 2)) {
				throw new Exception("El código de la consignación no debe exceder los 10 digitos");
			}

			Consignaciones consignacion = consignacionesDAO.findById(entity.getId());

			if (consignacion == null) {
				throw new Exception("No existe una consignación con el id " + consignacion.getId().getConCodigo());
			}

			Cuentas cuenta = cuentasDAO.findById(entity.getId().getCueNumero());

			if (cuenta == null) {
				throw new Exception("No existe una cuenta con código " + entity.getCuentas().getCueNumero());
			}

			if (entity.getId().getCueNumero() == null || entity.getId().getCueNumero().equals("")) {
				throw new Exception("El número de cuenta es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength("" + entity.getId().getConCodigo(), 10)) {
				throw new Exception("El código de la consigación no puede ser mayor a 10 digitos");
			}

			if (!Utilities.checkWordAndCheckWithlength("" + entity.getId().getCueNumero(), 30)) {
				throw new Exception("El número de cuenta no puede ser mayor a 30 digitos");
			}

			if (entity.getUsuarios() == null) {
				throw new Exception("No hay un usuario asociado a la consignación");
			}

			Usuarios usuarios = usuariosDAO.findById(entity.getUsuarios().getUsuCedula());

			if (usuarios == null) {
				throw new Exception("No existe un usuario con la cédula " + entity.getUsuarios().getUsuCedula());
			}

			if (entity.getCuentas() == null) {
				throw new Exception("No hay una cuenta asociada a la consignación");
			}

			if (entity.getConValor() == null) {
				throw new Exception("El valor de la consignación es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getConValor().toString(), 10, 2)) {
				throw new Exception("El valor de la consignación no debe ser mayor a 10 digitos");
			}

			if (entity.getConValor().doubleValue() == 0) {
				throw new Exception("Debe escribir un valor para la consignación");
			}

			if (entity.getConValor().doubleValue() < 0) {
				throw new Exception("El valor de la consignación no puede ser un número negativo");
			}

			if (entity.getConFecha() == null) {
				throw new Exception("La fecha es un campo obligatorio");
			}

			if (entity.getConDescripcion() == null || entity.getConDescripcion().equals("")) {
				throw new Exception("La descripción de la consignación es obligatoria");
			}

			if (!Utilities.checkWordAndCheckWithlength("" + entity.getConDescripcion(), 50)) {
				throw new Exception("La descripción de la consignación no puede ser mayor a 50 caracteres");
			}

			consignacionesDAO.save(entity);
			log.info("Guardó satisfactoriamente");

		} catch (Exception e) {
			log.error("saveConsignaciones falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Consignaciones entity) throws Exception {
		try {

			log.info("inicia updateConsignaciones");

			if (entity == null) {
				throw new Exception("Debe especificarse un paramentro");
			}

			if (entity.getId() == null) {
				throw new Exception("La consignación no tiene una identificación asociada");
			}

			if (entity.getId().getConCodigo() == 0) {
				throw new Exception("El id de la consignación es obligatoria");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getId().getConCodigo() + "", 10, 2)) {
				throw new Exception("El código de la consignación no debe exceder los 10 digitos");
			}

			Consignaciones consignacion = consignacionesDAO.findById(entity.getId());

			if (consignacion == null) {
				throw new Exception("No existe una consignación con el id " + consignacion.getId().getConCodigo());
			}

			Cuentas cuenta = cuentasDAO.findById(entity.getId().getCueNumero());

			if (cuenta == null) {
				throw new Exception("No existe una cuenta con código " + entity.getCuentas().getCueNumero());
			}

			if (entity.getId().getCueNumero() == null || entity.getId().getCueNumero().equals("")) {
				throw new Exception("El número de cuenta es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale("" + entity.getId().getConCodigo(), 10, 0)) {
				throw new Exception("El ID de la cuenta no puede ser mayor a 10 digitos");
			}

			if (!Utilities.checkWordAndCheckWithlength("" + entity.getId().getCueNumero(), 30)) {
				throw new Exception("El número de cuenta no puede ser mayor a 30 digitos");
			}

			if (entity.getUsuarios() == null) {
				throw new Exception("No hay un usuario asociado a la consignación");
			}

			Usuarios usuarios = usuariosDAO.findById(entity.getUsuarios().getUsuCedula());

			if (usuarios == null) {
				throw new Exception("No exoste un usuario con la cédula " + entity.getUsuarios().getUsuCedula());
			}

			if (entity.getCuentas() == null) {
				throw new Exception("No hay una cuenta asociada a la consignación");
			}

			if (entity.getConValor() == null) {
				throw new Exception("El valor de la consignación es obligatorio");
			}

			if (entity.getConValor().doubleValue() == 0) {
				throw new Exception("Debe escribir un valor para la consignación");
			}

			if (entity.getConValor().doubleValue() < 0) {
				throw new Exception("El valor de la consignación no puede ser un número negativo");
			}

			if (entity.getConFecha() == null) {
				throw new Exception("La fecha es un campo obligatorio");
			}

			if (entity.getConDescripcion() == null || entity.getConDescripcion().equals("")) {
				throw new Exception("La descripción de la consignación es obligatoria");
			}

			if (!Utilities.checkWordAndCheckWithlength("" + entity.getConDescripcion(), 50)) {
				throw new Exception("El número de cuenta no puede ser mayor a 30 digitos");
			}

			consignacionesDAO.update(entity);
			log.info("Actualizó satisfactoriamente");

		} catch (Exception e) {
			log.error("updateConsignaciones falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Consignaciones entity) throws Exception {

		log.info("deleteConsignaciones inició");

		if (entity.getId() == null) {
			throw new Exception("La consignación no tiene una identificación asociada");
		}

		if (entity.getId().getConCodigo() == 0) {
			throw new Exception("El código de la consignación es obligatorio");
		}

		Consignaciones consignaciones = consignacionesDAO.findById(entity.getId());

		if (consignaciones == null) {
			throw new Exception("No exise ninguna consignación con el código " + entity.getId().getConCodigo());
		}

		consignacionesDAO.delete(entity);
		log.info("eliminó satisfactoriamente");

	}

	@TransactionAttribute
	public Consignaciones findById(ConsignacionesId entity) throws Exception {
		Consignaciones consignaciones = null;

		try {
			log.info("findById inició");

			if (entity == null) {
				throw new Exception("La consignación no tiene una identificación asociada");
			}

			if (entity.getConCodigo() == 0) {
				throw new Exception("El código de la consignación es obligatorio");
			}

			consignaciones = consignacionesDAO.findById(entity);

		} catch (Exception e) {
			log.error("findById falló");
		}

		return consignaciones;
	}

	@TransactionAttribute
	public List<Consignaciones> findAll() throws Exception {

		return consignacionesDAO.findAll();
	}

}
