package co.edu.icesi.demo.vista;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.business.IBusinessDelegate;
import co.edu.icesi.demo.modelo.TiposUsuarios;
import co.edu.icesi.demo.modelo.Usuarios;

@ManagedBean
@ViewScoped
public class UsuarioView {

	@EJB
	private IBusinessDelegate businessDelegate;

	private InputText txtNombre;
	private InputText txtNombre1;
	private InputText txtLogin;
	private InputText txtLogin1;
	private InputText txtCedula;
	private InputText txtCedula1;
	private InputText txtCedula2;
	private InputText txtCedula3;
	private Password txtClave;
	private Password txtClave1;
	private CommandButton btnGuardar;
	private String tipoUsuario;
	private String tipoUsuario1;
	private List<String> listTiposUsuarios;
	private String nombre;
	private String cedula;
	private String login;
	private String clave;
	private String tipUsuc;
	private List<Usuarios> usuariosTabla;

	public IBusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}

	public void setBusinessDelegate(IBusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	private CommandButton btnActualizar;
	private CommandButton btnBorrar;
	private CommandButton btnBuscar;

	private static final Logger log = LoggerFactory.getLogger(UsuarioView.class);

	public String actionGuardar() {
		try {
			log.info("------------------------------------------->entro");
			log.info("------------------------------------------->" + txtNombre);
			log.info("------------------------------------------->" + txtLogin);
			log.info("------------------------------------------->" + txtClave);
			log.info("------------------------------------------->" + txtCedula);
			log.info("------------------------------------------->" + tipoUsuario);
			Usuarios usuarios = new Usuarios();
			TiposUsuarios tiposUsuarios = new TiposUsuarios();
			String[] tipoUsuarioArray = tipoUsuario.split("-");
			tiposUsuarios.setTusuCodigo(Long.parseLong(tipoUsuarioArray[0]));
			tiposUsuarios.setTusuNombre(tipoUsuarioArray[1]);
			usuarios.setTiposUsuarios(tiposUsuarios);
			usuarios.setUsuNombre(txtNombre.getValue().toString());
			usuarios.setUsuLogin(txtLogin.getValue().toString());
			usuarios.setUsuClave(txtClave.getValue().toString());
			usuarios.setUsuCedula(Long.parseLong(txtCedula.getValue().toString()));
			businessDelegate.save(usuarios);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado exitosamente", ""));
		} catch (Exception e) {
			e.getStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}

		return "";
	}

	public String actionActualizar() {
		try {
			Usuarios usuarios = new Usuarios();
			TiposUsuarios tiposUsuarios = new TiposUsuarios();
			String[] tipoUsuarioArray = tipoUsuario1.split("-");
			tiposUsuarios.setTusuCodigo(Long.parseLong(tipoUsuarioArray[0]));
			tiposUsuarios.setTusuNombre(tipoUsuarioArray[1]);
			usuarios.setTiposUsuarios(tiposUsuarios);
			usuarios.setUsuNombre(txtNombre1.getValue().toString());
			usuarios.setUsuLogin(txtLogin1.getValue().toString());
			usuarios.setUsuClave(txtClave1.getValue().toString());
			usuarios.setUsuCedula(Long.parseLong(txtCedula1.getValue().toString()));
			businessDelegate.update(usuarios);
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
			Usuarios usuarios = businessDelegate.findById(Long.parseLong(txtCedula2.getValue().toString()));
			businessDelegate.delete(usuarios.getUsuCedula());
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
			Usuarios usuarios = businessDelegate.findById(Long.parseLong(txtCedula3.getValue().toString()));
			cedula = usuarios.getUsuCedula() + "";
			nombre = usuarios.getUsuNombre();
			login = usuarios.getUsuLogin();
			clave = usuarios.getUsuClave();
			tipUsuc = usuarios.getTiposUsuarios().getTusuNombre();

		} catch (FacesException e) {
			e.getStackTrace();

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

	public InputText getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(InputText txtLogin) {
		this.txtLogin = txtLogin;
	}

	public Password getTxtClave() {
		return txtClave;
	}

	public void setTxtClave(Password txtClave) {
		this.txtClave = txtClave;
	}

	public CommandButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(CommandButton btnGuardar) {
		this.btnGuardar = btnGuardar;
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

	public InputText getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(InputText txtCedula) {
		this.txtCedula = txtCedula;
	}

	public List<String> getListTiposUsuarios() {
		List<TiposUsuarios> lista = businessDelegate.findAllTiposUsuarios();
		listTiposUsuarios = new ArrayList<String>();
		for (TiposUsuarios tiposUsuarios : lista) {
			listTiposUsuarios.add(tiposUsuarios.getTusuCodigo() + "-" + tiposUsuarios.getTusuNombre());
		}
		return listTiposUsuarios;

	}

	public void setListTiposUsuarios(List<String> listTiposUsuarios) {
		this.listTiposUsuarios = listTiposUsuarios;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public InputText getTxtNombre1() {
		return txtNombre1;
	}

	public void setTxtNombre1(InputText txtNombre1) {
		this.txtNombre1 = txtNombre1;
	}

	public InputText getTxtLogin1() {
		return txtLogin1;
	}

	public void setTxtLogin1(InputText txtLogin1) {
		this.txtLogin1 = txtLogin1;
	}

	public InputText getTxtCedula1() {
		return txtCedula1;
	}

	public void setTxtCedula1(InputText txtCedula1) {
		this.txtCedula1 = txtCedula1;
	}

	public Password getTxtClave1() {
		return txtClave1;
	}

	public void setTxtClave1(Password txtClave1) {
		this.txtClave1 = txtClave1;
	}

	public InputText getTxtCedula2() {
		return txtCedula2;
	}

	public void setTxtCedula2(InputText txtCedula2) {
		this.txtCedula2 = txtCedula2;
	}

	public InputText getTxtCedula3() {
		return txtCedula3;
	}

	public void setTxtCedula3(InputText txtCedula3) {
		this.txtCedula3 = txtCedula3;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getTipUsuc() {
		return tipUsuc;
	}

	public void setTipUsuc(String tipUsuc) {
		this.tipUsuc = tipUsuc;
	}

	public String getTipoUsuario1() {
		return tipoUsuario1;
	}

	public void setTipoUsuario1(String tipoUsuario1) {
		this.tipoUsuario1 = tipoUsuario1;
	}

	public List<Usuarios> getUsuariosTabla() {
		usuariosTabla = businessDelegate.findAllUsuarios();
		log.info("----------------------------------------------------------------------->" + usuariosTabla.size());
		return usuariosTabla;
	}

	public void setUsuariosTabla(List<Usuarios> usuariosTabla) {
		this.usuariosTabla = usuariosTabla;
	}

	public String actualizarUsuariosTablas() {
		usuariosTabla = businessDelegate.findAllUsuarios();
		return "";
	}

}
