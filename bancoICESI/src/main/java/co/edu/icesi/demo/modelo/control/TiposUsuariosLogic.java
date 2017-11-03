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
import co.edu.icesi.demo.utilities.Utilities;

@Stateless
public class TiposUsuariosLogic implements ITiposUsuariosLogic {

	private static final Logger log = LoggerFactory.getLogger(TiposDocumentosLogic.class);

	@EJB
	private ITiposUsuariosDAO tiposUsuariosDAO;

	@EJB
	private IUsuariosDAO usuariosDAO;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(TiposUsuarios entity) {
		try {
			log.info("inicia saveTipoUsuarios");

			if (entity.getTusuCodigo() == 0) {
				throw new Exception("El código del tipo de usuario es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getTusuCodigo() + "", 10, 0)) {
				throw new Exception("El tamaño del tipo de usuario no debe ser mayor a 10 digitos");
			}

			if (entity.getTusuNombre() == null || entity.getTusuNombre().equals("")) {
				throw new Exception("El nombre del tipo de usuario es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getTusuNombre(), 50)) {
				throw new Exception("El tamaño del nombre tipo de usuario no debe ser mayor a 50 digitos");
			}

			if (findById(entity.getTusuCodigo()) != null) {
				throw new Exception("Ya existe un tipo usuario con el código " + entity.getTusuCodigo());
			}

			tiposUsuariosDAO.save(entity);
			log.info("Guardó satisfactoriamente");
		} catch (Exception e) {
			log.error("saveTiposUsuarios falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(TiposUsuarios entity) {
		try {
			log.info("inicia updateTipoUsuarios");

			if (entity.getTusuCodigo() == 0) {
				throw new Exception("El código del tipo de usuario es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale(entity.getTusuCodigo() + "", 10, 0)) {
				throw new Exception("El tamaño del tipo de usuario no debe ser mayor a 10 digitos");
			}

			if (entity.getTusuNombre() == null || entity.getTusuNombre().equals("")) {
				throw new Exception("El nombre del tipo de usuario es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getTusuNombre(), 50)) {
				throw new Exception("El tamaño del nombre tipo de usuario no debe ser mayor a 50 digitos");
			}

			if (findById(entity.getTusuCodigo()) == null) {
				throw new Exception("No existe un tipo usuario con el código " + entity.getTusuCodigo());
			}

			tiposUsuariosDAO.update(entity);
			log.info("Actualizó satisfactoriamente");
		} catch (Exception e) {
			log.error("updateTiposUsuarios falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Long codigo) {
		try {
			if (codigo == 0 || codigo == null) {
				throw new Exception("El código del tipo de usuario es obligatorio");
			}

			TiposUsuarios tiposUsuarios = tiposUsuariosDAO.findById(codigo);

			if (tiposUsuarios == null) {
				throw new Exception("No exste un tipo usuario con el código " + codigo);
			}

			if (tiposUsuarios.getUsuarioses() != null && !tiposUsuarios.getUsuarioses().isEmpty()) {
				throw new Exception("El tipo de usuario con el código " + codigo + " no se puede eliminar ya"
						+ "que tiene usuarios asociados");
			}

			tiposUsuariosDAO.delete(tiposUsuarios);
			log.info("Eliminó correctamente");

		} catch (Exception e) {
			log.error("deleteTiposUsuarios falló");
		}

	}

	@TransactionAttribute
	public TiposUsuarios findById(Long id) {
		TiposUsuarios tiposUsuarios = null;

		try {
			if (id == null || id == 0) {
				throw new Exception("El codigo del tipo de usuario es obligatorio");
			}

			tiposUsuarios = tiposUsuariosDAO.findById(id);
		} catch (Exception e) {
			log.error("findById falló");
		}
		return tiposUsuarios;
	}

	@TransactionAttribute
	public List<TiposUsuarios> findAll() {
		return tiposUsuariosDAO.findAll();
	}

}
