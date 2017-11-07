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
import co.edu.icesi.demo.modelo.TiposUsuarios;

@ManagedBean
@ViewScoped
public class TiposUsuariosView {

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
	private List<TiposUsuarios> tiposUsuariosTabla;

	public IBusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}

	public void setBusinessDelegate(IBusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	private static final Logger log = LoggerFactory.getLogger(TiposUsuariosView.class);

	public String actionGuardar() {
		try {
			TiposUsuarios tiposUsuarios = new TiposUsuarios();
			tiposUsuarios.setTusuCodigo(Long.parseLong(txtId.getValue().toString()));
			tiposUsuarios.setTusuNombre(txtNombre.getValue().toString());
			businessDelegate.saveTiposUsuarios(tiposUsuarios);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado exitosamente", ""));
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return "";
	}

	public String actionActualizar() {

		try {
			TiposUsuarios tiposUsuarios = new TiposUsuarios();
			tiposUsuarios.setTusuCodigo(Long.parseLong(txtId1.getValue().toString()));
			tiposUsuarios.setTusuNombre(txtNombre1.getValue().toString());
			businessDelegate.updateTiposUsuarios(tiposUsuarios);
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
			businessDelegate.deleteTiposUsuarios(Long.parseLong(txtId2.getValue().toString()));
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado exitosamente", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}

		return "";
	}

	public String actionBuscar() {
		try {
			TiposUsuarios tu = businessDelegate.findByIdTiposUsuarios(Long.parseLong(txtId3.getValue().toString()));
			nombre = tu.getTusuNombre();
			codigo = tu.getTusuCodigo() + "";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

		}

		return "";
	}

	public String actualizarTiposUsuariosTabla() {

		tiposUsuariosTabla = businessDelegate.findAllTiposUsuarios();

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

	public List<TiposUsuarios> getTiposUsuariosTabla() {
		tiposUsuariosTabla = businessDelegate.findAllTiposUsuarios();
		log.info("--------------------------------------------------------------------> es null?"
				+ tiposUsuariosTabla == null ? "es nulo :´v" : "cagada no es nulo");
		return tiposUsuariosTabla;
	}

	public void setTiposUsuariosTabla(List<TiposUsuarios> tiposUsuarios) {
		this.tiposUsuariosTabla = tiposUsuarios;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
