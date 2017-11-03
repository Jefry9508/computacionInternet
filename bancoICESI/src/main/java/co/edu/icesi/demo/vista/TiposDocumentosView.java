package co.edu.icesi.demo.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.business.IBusinessDelegate;
import co.edu.icesi.demo.modelo.TiposDocumentos;

@ManagedBean
@ViewScoped
public class TiposDocumentosView {

	@ManagedProperty("#{BusinessDelegate}")
	private IBusinessDelegate businessDelegate;

	private InputText txtNombre;
	private InputText txtId;
	private CommandButton btnActualizar;
	private CommandButton btnBorrar;
	private CommandButton btnBuscar;
	private CommandButton btnGuardar;

	public IBusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}

	public void setBusinessDelegate(IBusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	private static final Logger log = LoggerFactory.getLogger(TiposDocumentosView.class);

	public String actionGuardar() {
		try {
			TiposDocumentos tiposDocumentos = new TiposDocumentos();
			tiposDocumentos.setTdocCodigo(Long.parseLong(txtId.getValue().toString()));
			tiposDocumentos.setTdocNombre(txtNombre.getValue().toString());
			businessDelegate.saveTiposDocumentos(tiposDocumentos);
			log.info("Agrego correctamente");
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return "";
	}

	public String actionActualizar() {

		return "";
	}

	public String actionBorrar() {
		log.info("Borro");

		return "";
	}

	public String actionBuscar() {
		log.info("Busco");

		return "";
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtId() {
		return txtId;
	}

	public void setTxtId(InputText txtId) {
		this.txtId = txtId;
	}

	public CommandButton getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(CommandButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public CommandButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(CommandButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public CommandButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(CommandButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

}
