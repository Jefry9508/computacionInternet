package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.dao.ICuentasDAO;
import co.edu.icesi.demo.dao.IRetirosDAO;
import co.edu.icesi.demo.dao.IUsuariosDAO;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;
import co.edu.icesi.demo.modelo.Usuarios;
import co.edu.icesi.demo.utilities.Utilities;

@Stateless
public class RetirosLogic implements IRetirosLogic {

	private static final Logger log = LoggerFactory.getLogger(ClientesLogic.class);

	@EJB
	private IRetirosDAO retirosDAO;

	@EJB
	private ICuentasDAO cuentasDAO;

	@EJB
	private IUsuariosDAO usuariosDAO;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(Retiros entity) {
		try {

			log.info("saveRetiros inició");

			if (entity == null) {
				throw new Exception("Debe especificarse un paramentro");
			}

			if (entity.getId() == null) {
				throw new Exception("No hay una identificación asociada a el retiro");
			}

			if (entity.getId().getRetCodigo() == 0) {
				throw new Exception("El código del retiro es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getId().getRetCodigo() + "", 10)) {
				throw new Exception("El código de el retiro no debe ser mayor a 10 digitos");
			}

			Retiros retiros = retirosDAO.findById(entity.getId());

			if (retiros != null) {
				throw new Exception("Ya existe un retiro con el código " + entity.getId().getRetCodigo());
			}

			if (entity.getId().getCueNumero() == null || entity.getId().getCueNumero().equals("")) {
				throw new Exception("No hay un número de cuenta asociado a el retiro");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getId().getCueNumero(), 30)) {
				throw new Exception("El número de cuenta no debe ser mayor a 30 caracteres.");
			}

			if (entity.getCuentas() == null) {
				throw new Exception("No hay una cuenta asociada al retiro");
			}

			Cuentas cuentas = cuentasDAO.findById(entity.getCuentas().getCueNumero());

			if (cuentas == null) {
				throw new Exception("No existe la cuenta número " + entity.getCuentas().getCueNumero());
			}

			if (entity.getUsuarios() == null) {
				throw new Exception("No hay un usuario asociado al retiro.");
			}

			Usuarios usuarios = usuariosDAO.findById(entity.getUsuarios().getUsuCedula());

			if (usuarios == null) {
				throw new Exception("No existe un usuario con la cédula " + entity.getUsuarios().getUsuCedula());
			}

			if (entity.getRetFecha() == null) {
				throw new Exception("El monto del retiro es obligatorio");
			}

			if (entity.getRetValor().doubleValue() < 0) {
				throw new Exception("El monto del retiro no puede ser negativo");
			}

			if (entity.getRetValor().doubleValue() == 0) {
				throw new Exception("El monto del retiro no puede ser $0");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getRetValor().toString(), 10, 2)) {
				throw new Exception("El monto del valor no debe ser mayor a 10 digitos");
			}

			if (entity.getRetFecha() == null) {
				throw new Exception("No hay una fecha asociada al retiro");
			}

			if (entity.getRetDescripcion() == null || entity.getRetDescripcion().equals("")) {
				throw new Exception("Debe especificarse una descrición del retiro");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getRetDescripcion(), 50)) {
				throw new Exception("La descripción no debe ser mayor a 50 caracteres");
			}

			retirosDAO.save(entity);

			log.info("Guardó satisfactoriamente");

		} catch (Exception e) {
			log.error("saveRetiros falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Retiros entity) {
		try {

			log.info("updateRetiros inició");

			if (entity == null) {
				throw new Exception("Debe especificarse un paramentro");
			}

			if (entity.getId() == null) {
				throw new Exception("No hay una identificación asociada a el retiro");
			}

			if (entity.getId().getRetCodigo() == 0) {
				throw new Exception("El código del retiro es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getId().getRetCodigo() + "", 10, 0)) {
				throw new Exception("El código de el retiro no debe ser mayor a 10 digitos");
			}

			Retiros retiros = retirosDAO.findById(entity.getId());

			if (retiros == null) {
				throw new Exception("No existe un retiro con el código " + entity.getId().getRetCodigo());
			}

			if (entity.getId().getCueNumero() == null || entity.getId().getCueNumero().equals("")) {
				throw new Exception("No hay un número de cuenta asociado a el retiro");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getId().getCueNumero(), 30)) {
				throw new Exception("El número de cuenta no debe ser mayor a 30 caracteres.");
			}

			if (entity.getCuentas() == null) {
				throw new Exception("No hay una cuenta asociada al retiro");
			}

			Cuentas cuentas = cuentasDAO.findById(entity.getCuentas().getCueNumero());

			if (cuentas == null) {
				throw new Exception("No existe la cuenta número " + entity.getCuentas().getCueNumero());
			}

			if (entity.getUsuarios() == null) {
				throw new Exception("No hay un usuario asociado al retiro.");
			}

			Usuarios usuarios = usuariosDAO.findById(entity.getUsuarios().getUsuCedula());

			if (usuarios == null) {
				throw new Exception("No existe un usuario con la cédula " + entity.getUsuarios().getUsuCedula());
			}

			if (entity.getRetFecha() == null) {
				throw new Exception("El monto del retiro es obligatorio");
			}

			if (entity.getRetValor().doubleValue() < 0) {
				throw new Exception("El monto del retiro no puede ser negativo");
			}

			if (entity.getRetValor().doubleValue() == 0) {
				throw new Exception("El monto del retiro no puede ser $0");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getRetValor().toString(), 10, 2)) {
				throw new Exception("El monto del valor no debe ser mayor a 10 digitos");
			}

			if (entity.getRetFecha() == null) {
				throw new Exception("No hay una fecha asociada al retiro");
			}

			if (entity.getRetDescripcion() == null || entity.getRetDescripcion().equals("")) {
				throw new Exception("Debe especificarse una descrición del retiro");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getRetDescripcion(), 50)) {
				throw new Exception("La descripción no debe ser mayor a 50 caracteres");
			}

			retirosDAO.update(entity);

			log.info("Actualizó satisfactoriamente");

		} catch (Exception e) {
			log.error("updateRetiros falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(RetirosId entity) {
		try {
			log.info("deleteRetiros inició");

			if (entity == null) {
				throw new Exception("No se especificó un parámetro");
			}

			if (entity.getRetCodigo() == 0) {
				throw new Exception("El codigo del retiro es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getRetCodigo() + "", 10, 0)) {
				throw new Exception("El codigo de el retiro no puede ser mayor a 10 digitos");
			}

			Retiros retiros = retirosDAO.findById(entity);

			if (retiros == null) {
				throw new Exception("No existe un retiro con el código " + entity.getRetCodigo());
			}

			retirosDAO.delete(retiros);

			log.info("eliminó satisfactoriamente");

		} catch (Exception e) {
			log.error("deleteRetiro falló", e);
		}

	}

	@TransactionAttribute
	public Retiros findById(RetirosId entity) {
		Retiros retiros = null;

		try {
			log.info("findByIdRetiros inició");

			if (entity == null) {
				throw new Exception("No se especificó un parámetro");
			}

			if (entity.getRetCodigo() == 0) {
				throw new Exception("El codigo del retiro es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getRetCodigo() + "", 10, 0)) {
				throw new Exception("El codigo de el retiro no puede ser mayor a 10 digitos");
			}

			retiros = retirosDAO.findById(entity);
			log.info("findByIdRetiros correctamente");
		} catch (Exception e) {
			log.error("findByIdRetiros falló");
		}

		return retiros;
	}

	@TransactionAttribute
	public List<Retiros> findAll() {

		return retirosDAO.findAll();
	}

}
