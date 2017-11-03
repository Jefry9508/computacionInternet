package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.dao.IClientesDAO;
import co.edu.icesi.demo.dao.ITiposDocumentosDAO;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.TiposDocumentos;
import co.edu.icesi.demo.utilities.Utilities;

@Stateless
public class TiposDocumentosLogic implements ITiposDocumentosLogic {

	private static final Logger log = LoggerFactory.getLogger(TiposDocumentosLogic.class);

	@EJB
	private ITiposDocumentosDAO tiposDocumentosDAO;

	@EJB
	private IClientesDAO clientesDAO;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveTiposDocumentos(TiposDocumentos entity) throws Exception {

		try {

			log.info("inicia saveTiposDocumentos");

			if (entity.getTdocCodigo() == 0) {
				throw new Exception("El código del tipo de documento es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale("" + entity.getTdocCodigo(), 10, 0)) {
				throw new Exception("El tamaño del tipo de documento no debe ser mayor a 10 digitos");
			}

			if (entity.getTdocNombre() == null || entity.getTdocNombre().equals("")) {
				throw new Exception("El nombre del tipo de documento es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getTdocNombre(), 50)) {
				throw new Exception("El tamaño del nombre tipo de documento no debe ser mayor a 50 digitos");
			}

			if (getTiposDocumentosById(entity.getTdocCodigo()) != null) {
				throw new Exception("Ya existe un tipo de documento con el código " + entity.getTdocCodigo());
			}

			tiposDocumentosDAO.save(entity);
			log.info("Guardo satisfactoriamente");

		} catch (Exception e) {
			log.error("saveTiposDocumentos falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateTiposDocumentos(TiposDocumentos entity) throws Exception {
		try {

			log.info("inicia saveTiposDocumentos");

			if (entity.getTdocCodigo() == 0) {
				throw new Exception("El código del tipo de documento es obligatorio");
			}

			if (!Utilities.checkNumberAndCheckWithPrecisionAndScale("" + entity.getTdocCodigo(), 10, 0)) {
				throw new Exception("El tamaño del tipo de documento no debe ser mayor a 10 digitos");
			}

			if (entity.getTdocNombre() == null || entity.getTdocNombre().equals("")) {
				throw new Exception("El nombre del tipo de documento es obligatorio");
			}

			if (!Utilities.checkWordAndCheckWithlength(entity.getTdocNombre(), 50)) {
				throw new Exception("El tamaño del tipo de documento no debe ser mayor a 10 digitos");
			}

			if (getTiposDocumentosById(entity.getTdocCodigo()) == null) {
				throw new Exception("No existe un tipo de documento con el código " + entity.getTdocCodigo());
			}

			tiposDocumentosDAO.update(entity);
			log.info("Modificó satisfactoriamente");

		} catch (Exception e) {
			log.error("updateTiposDocumentos falló", e);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteTiposDocumentos(Long codigo) throws Exception {
		try {

			if (codigo == null || codigo == 0) {
				throw new Exception("El código del tipo de documento debe ser obligatorio");
			}

			List<Clientes> clientes = clientesDAO.findByProperty("tiposDocumentos.tdocCodigo", codigo);

			if (clientes != null && !clientes.isEmpty()) {
				throw new Exception("El tipo de documento con el código " + codigo + " no se puede eliminar ya"
						+ "que tiene clientes asociados");
			}
			TiposDocumentos entity = getTiposDocumentosById(codigo);

			if (entity == null) {
				throw new Exception("No existe un tipo de documento con el código " + codigo);
			}

			tiposDocumentosDAO.delete(entity);
			log.info("Eliminó satisfactoriamente");

		} catch (Exception e) {
			log.error("deleteTiposDocumentos falló", e);
		}

	}

	@TransactionAttribute
	public List<TiposDocumentos> getTiposDocumentos() throws Exception {
		return tiposDocumentosDAO.findAll();
	}

	@TransactionAttribute
	public TiposDocumentos getTiposDocumentosById(Long codigo) throws Exception {

		TiposDocumentos tiposDocumentos = null;

		try {

			if (codigo == null || codigo == 0) {
				throw new Exception("El código del tipo de documento es obligatorio");
			}

			tiposDocumentos = tiposDocumentosDAO.findById(codigo);

		} catch (Exception e) {
			log.error("getTiposDocumentos falló", e);
		}

		return tiposDocumentos;
	}

}
