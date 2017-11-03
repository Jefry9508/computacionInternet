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

			log.info("saveRetiros inici�");

			if (entity == null) {
				throw new Exception("Debe especificarse un paramentro");
			}

			if (entity.getId() == null) {
				throw new Exception("No hay una identificaci�n asociada a el retiro");
			}

			if (entity.getId().getRetCodigo() == 0) {
				throw new Exception("El c�digo del retiro es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getId().getRetCodigo() + "", 10)) {
				throw new Exception("El c�digo de el retiro no debe ser mayor a 10 digitos");
			}

			Retiros retiros = retirosDAO.findById(entity.getId());

			if (retiros != null) {
				throw new Exception("Ya existe un retiro con el c�digo " + entity.getId().getRetCodigo());
			}

			if (entity.getId().getCueNumero() == null || entity.getId().getCueNumero().equals("")) {
				throw new Exception("No hay un n�mero de cuenta asociado a el retiro");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getId().getCueNumero(), 30)) {
				throw new Exception("El n�mero de cuenta no debe ser mayor a 30 caracteres.");
			}

			if (entity.getCuentas() == null) {
				throw new Exception("No hay una cuenta asociada al retiro");
			}

			Cuentas cuentas = cuentasDAO.findById(entity.getCuentas().getCueNumero());

			if (cuentas == null) {
				throw new Exception("No existe la cuenta n�mero " + entity.getCuentas().getCueNumero());
			}

			if (entity.getUsuarios() == null) {
				throw new Exception("No hay un usuario asociado al retiro.");
			}

			Usuarios usuarios = usuariosDAO.findById(entity.getUsuarios().getUsuCedula());

			if (usuarios == null) {
				throw new Exception("No existe un usuario con la c�dula " + entity.getUsuarios().getUsuCedula());
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
				throw new Exception("Debe especificarse una descrici�n del retiro");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getRetDescripcion(), 50)) {
				throw new Exception("La descripci�n no debe ser mayor a 50 caracteres");
			}

			retirosDAO.save(entity);

			log.info("Guard� satisfactoriamente");

		} catch (Exception e) {
			log.error("saveRetiros fall�", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Retiros entity) {
		try {

			log.info("updateRetiros inici�");

			if (entity == null) {
				throw new Exception("Debe especificarse un paramentro");
			}

			if (entity.getId() == null) {
				throw new Exception("No hay una identificaci�n asociada a el retiro");
			}

			if (entity.getId().getRetCodigo() == 0) {
				throw new Exception("El c�digo del retiro es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getId().getRetCodigo() + "", 10, 0)) {
				throw new Exception("El c�digo de el retiro no debe ser mayor a 10 digitos");
			}

			Retiros retiros = retirosDAO.findById(entity.getId());

			if (retiros == null) {
				throw new Exception("No existe un retiro con el c�digo " + entity.getId().getRetCodigo());
			}

			if (entity.getId().getCueNumero() == null || entity.getId().getCueNumero().equals("")) {
				throw new Exception("No hay un n�mero de cuenta asociado a el retiro");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getId().getCueNumero(), 30)) {
				throw new Exception("El n�mero de cuenta no debe ser mayor a 30 caracteres.");
			}

			if (entity.getCuentas() == null) {
				throw new Exception("No hay una cuenta asociada al retiro");
			}

			Cuentas cuentas = cuentasDAO.findById(entity.getCuentas().getCueNumero());

			if (cuentas == null) {
				throw new Exception("No existe la cuenta n�mero " + entity.getCuentas().getCueNumero());
			}

			if (entity.getUsuarios() == null) {
				throw new Exception("No hay un usuario asociado al retiro.");
			}

			Usuarios usuarios = usuariosDAO.findById(entity.getUsuarios().getUsuCedula());

			if (usuarios == null) {
				throw new Exception("No existe un usuario con la c�dula " + entity.getUsuarios().getUsuCedula());
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
				throw new Exception("Debe especificarse una descrici�n del retiro");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getRetDescripcion(), 50)) {
				throw new Exception("La descripci�n no debe ser mayor a 50 caracteres");
			}

			retirosDAO.update(entity);

			log.info("Actualiz� satisfactoriamente");

		} catch (Exception e) {
			log.error("updateRetiros fall�", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(RetirosId entity) {
		try {
			log.info("deleteRetiros inici�");

			if (entity == null) {
				throw new Exception("No se especific� un par�metro");
			}

			if (entity.getRetCodigo() == 0) {
				throw new Exception("El codigo del retiro es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getRetCodigo() + "", 10, 0)) {
				throw new Exception("El codigo de el retiro no puede ser mayor a 10 digitos");
			}

			Retiros retiros = retirosDAO.findById(entity);

			if (retiros == null) {
				throw new Exception("No existe un retiro con el c�digo " + entity.getRetCodigo());
			}

			retirosDAO.delete(retiros);

			log.info("elimin� satisfactoriamente");

		} catch (Exception e) {
			log.error("deleteRetiro fall�", e);
		}

	}

	@TransactionAttribute
	public Retiros findById(RetirosId entity) {
		Retiros retiros = null;

		try {
			log.info("findByIdRetiros inici�");

			if (entity == null) {
				throw new Exception("No se especific� un par�metro");
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
			log.error("findByIdRetiros fall�");
		}

		return retiros;
	}

	@TransactionAttribute
	public List<Retiros> findAll() {

		return retirosDAO.findAll();
	}

}
