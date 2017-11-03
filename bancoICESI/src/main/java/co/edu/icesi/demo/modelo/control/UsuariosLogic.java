package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.dao.ITiposUsuariosDAO;
import co.edu.icesi.demo.dao.IUsuariosDAO;
import co.edu.icesi.demo.modelo.TiposUsuarios;
import co.edu.icesi.demo.modelo.Usuarios;
import co.edu.icesi.demo.utilities.Utilities;

@Stateless
public class UsuariosLogic implements IUsuariosLogic {

	private static final Logger log = LoggerFactory.getLogger(ClientesLogic.class);

	@EJB
	private IUsuariosDAO usuariosDAO;

	@EJB
	private ITiposUsuariosDAO tiposUsuariosDAO;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(Usuarios entity) {
		try {
			log.info("inicia saveUsuarios");

			if (entity == null) {
				throw new Exception("No se especificó un parametro");
			}

			if (entity.getUsuCedula() == 0) {
				throw new Exception("La cedula del usuario es obligatoria");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale("" + entity.getUsuCedula(), 10, 0)) {
				throw new Exception("El tamaño de la cedula del usuario no debe ser mayor a 10 digitos");
			}

			Usuarios usuarios = usuariosDAO.findById(entity.getUsuCedula());

			if (usuarios != null) {
				throw new Exception("Ya existe un usuario con la cedula " + entity.getUsuCedula());
			}

			if (entity.getTiposUsuarios() == null) {
				throw new Exception("No hay un tipo de usuario asociado");
			}

			TiposUsuarios tiposUsuarios = tiposUsuariosDAO.findById(entity.getTiposUsuarios().getTusuCodigo());

			if (tiposUsuarios == null) {
				throw new Exception("No existe un tipo usuario con el código " + tiposUsuarios.getTusuCodigo());
			}

			if (entity.getUsuNombre() == null || entity.getUsuNombre().equals("")) {
				throw new Exception("El nombre del usuario es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getUsuNombre(), 50)) {
				throw new Exception("El tamaño del nombre del usuario no debe ser mayor a 50 caracteres");
			}

			if (entity.getUsuLogin() == null || entity.getUsuLogin().equals("")) {
				throw new Exception("El login del usuario es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getUsuLogin(), 50)) {
				throw new Exception("El tamaño del login del usuario no debe ser mayor a 50 caracteres");
			}

			if (entity.getUsuClave() == null || entity.getUsuClave().equals("")) {
				throw new Exception("La clave del usuario es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getUsuClave(), 50)) {
				throw new Exception("El tamaño de la clave del usuario no debe ser mayor a 50 caracteres");
			}

			usuariosDAO.save(entity);
			log.info("Guardó correctamente");
		} catch (Exception e) {
			log.error("saveUsuarios falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Usuarios entity) {
		try {
			log.info("inicia updateUsuarios");

			if (entity == null) {
				throw new Exception("No se especificó un parametro");
			}

			if (entity.getUsuCedula() == 0) {
				throw new Exception("La cedula del usuario es obligatoria");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale("" + entity.getUsuCedula(), 10, 0)) {
				throw new Exception("El tamaño de la cedula del usuario no debe ser mayor a 10 digitos");
			}

			Usuarios usuarios = usuariosDAO.findById(entity.getUsuCedula());

			if (usuarios == null) {
				throw new Exception("No existe un usuario con la cedula " + entity.getUsuCedula());
			}

			if (entity.getTiposUsuarios() == null) {
				throw new Exception("No hay un tipo de usuario asociado");
			}

			TiposUsuarios tiposUsuarios = tiposUsuariosDAO.findById(entity.getTiposUsuarios().getTusuCodigo());

			if (tiposUsuarios == null) {
				throw new Exception("No existe un tipo usuario con el código " + tiposUsuarios.getTusuCodigo());
			}

			if (entity.getUsuNombre() == null || entity.getUsuNombre().equals("")) {
				throw new Exception("El nombre del usuario es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getUsuNombre(), 50)) {
				throw new Exception("El tamaño del nombre del usuario no debe ser mayor a 50 caracteres");
			}

			if (entity.getUsuLogin() == null || entity.getUsuLogin().equals("")) {
				throw new Exception("El login del usuario es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getUsuLogin(), 50)) {
				throw new Exception("El tamaño del login del usuario no debe ser mayor a 50 caracteres");
			}

			if (entity.getUsuClave() == null || entity.getUsuClave().equals("")) {
				throw new Exception("La clave del usuario es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getUsuClave(), 50)) {
				throw new Exception("El tamaño de la clave del usuario no debe ser mayor a 50 caracteres");
			}

			usuariosDAO.update(entity);
			log.info("Actualizó correctamente");
		} catch (Exception e) {
			log.error("updateUsuarios falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Long id) {
		try {
			log.info("inicia deleteClientes");
			if (id == null | id == 0) {
				throw new Exception("La cedula del usuario es obligatoria");
			}

			Usuarios usuarios = usuariosDAO.findById(id);

			if (usuarios == null) {
				throw new Exception("No existe ningún usuario con la cedula " + id);
			}

			if (usuarios.getConsignacioneses() != null && !usuarios.getConsignacioneses().isEmpty()) {
				throw new Exception("El usuario con la cedula " + id + " no se puede eliminar porque"
						+ " tiene consignaciones asociadas");
			}

			if (usuarios.getRetiroses() != null && !usuarios.getRetiroses().isEmpty()) {
				throw new Exception(
						"El usuario con la cedula " + id + " no se puede eliminar porque" + " tiene retiros asociadas");
			}

			usuariosDAO.delete(usuarios);
			log.info("Se eliminó correctamente");
		} catch (Exception e) {
			log.error("deleteUsuarios falló", e);
		}

	}

	@TransactionAttribute
	public Usuarios findById(Long id) {
		Usuarios usuarios = null;
		try {
			log.info("inicia findByIdUsuarios");

			if (id == null || id == 0) {
				throw new Exception("La cedula usuario es obligatorio");
			}

			usuarios = usuariosDAO.findById(id);

		} catch (Exception e) {
			log.error("findByIdUsuarios falló", e);
		}
		return usuarios;
	}

	@TransactionAttribute
	public List<Usuarios> findAll() {
		return usuariosDAO.findAll();
	}

}
