package co.edu.icesi.demo.vista;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.business.IBusinessDelegate;
import co.edu.icesi.demo.modelo.TiposDocumentos;

@ManagedBean
@ViewScoped
public class TiposDocumentosView {

	@EJB
	private IBusinessDelegate businessDelegate;

	private InputText txtNombre;
	private InputText txtId;
	private InputText txtNombre1;
	private InputText txtId1;
	private InputText txtId2;
	private InputText txtId3;
	private CommandButton btnActualizar;
	private CommandButton btnBorrar;
	private CommandButton btnBuscar;
	private CommandButton btnGuardar;
	private String codigo;
	private String nombre;
	private List<TiposDocumentos> tiposDocumentosTabla;

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
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado exitosamente", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}

		return "";
	}

	public String actionActualizar() {

		try {
			TiposDocumentos tiposDocumentos = new TiposDocumentos();
			tiposDocumentos.setTdocCodigo(Long.parseLong(txtId1.getValue().toString()));
			tiposDocumentos.setTdocNombre(txtNombre1.getValue().toString());
			businessDelegate.updateTiposDocumentos(tiposDocumentos);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado exitosamente", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}

		return "";
	}

	public String actionBorrar() {
		try {
			businessDelegate.deleteTiposDocumentos(Long.parseLong(txtId2.getValue().toString()));
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado exitosamente", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}

		return "";
	}

	public String actionBuscar() {
		try {
			TiposDocumentos td = businessDelegate.getTiposDocumentosById(Long.parseLong(txtId3.getValue().toString()));
			codigo = td.getTdocCodigo() + "";
			nombre = td.getTdocNombre();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}

		return "";
	}

	public String actualizarTiposDocumentosTabla() {
		try {
			tiposDocumentosTabla = businessDelegate.getTiposDocumentos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
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

	public InputText getTxtNombre1() {
		return txtNombre1;
	}

	public void setTxtNombre1(InputText txtNombre1) {
		this.txtNombre1 = txtNombre1;
	}

	public InputText getTxtId1() {
		return txtId1;
	}

	public void setTxtId1(InputText txtId1) {
		this.txtId1 = txtId1;
	}

	public InputText getTxtId2() {
		return txtId2;
	}

	public void setTxtId2(InputText txtId2) {
		this.txtId2 = txtId2;
	}

	public InputText getTxtId3() {
		return txtId3;
	}

	public void setTxtId3(InputText txtId3) {
		this.txtId3 = txtId3;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<TiposDocumentos> getTiposDocumentosTabla() {
		try {
			tiposDocumentosTabla = businessDelegate.getTiposDocumentos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
		return tiposDocumentosTabla;
	}

	public void setTiposDocumentosTabla(List<TiposDocumentos> tiposDocumentosTabla) {
		this.tiposDocumentosTabla = tiposDocumentosTabla;
	}

}
