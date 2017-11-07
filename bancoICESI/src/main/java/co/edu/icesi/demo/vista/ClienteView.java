package co.edu.icesi.demo.vista;

import java.util.ArrayList;
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
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.TiposDocumentos;

@ManagedBean
@ViewScoped
public class ClienteView {

	@EJB
	private IBusinessDelegate businessDelegate;

	private InputText txtNombre;
	private InputText txtId;
	private InputText txtDireccion;
	private InputText txtTelefono;
	private InputText txtEmail;
	private String tipoDoc;
	private InputText txtNombre1;
	private InputText txtId1;
	private InputText txtDireccion1;
	private InputText txtTelefono1;
	private InputText txtEmail1;
	private String tipoDoc1;
	private InputText txtId2;
	private InputText txtId3;
	private String nombre;
	private String id;
	private String tipoDocumento;
	private String direccion;
	private String telefono;
	private String email;
	private CommandButton btnGuardar;
	private CommandButton btnActualizar;
	private CommandButton btnBorrar;
	private CommandButton btnBuscar;
	private List<String> listTiposDocumentos;
	private List<Clientes> clientesTabla;

	public IBusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}

	public void setBusinessDelegate(IBusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	private static final Logger log = LoggerFactory.getLogger(ClienteView.class);

	public String actionGuardar() {
		try {
			Clientes clientes = new Clientes();
			TiposDocumentos tiposDocumentos = new TiposDocumentos();
			String[] tipoUsuarioArray = tipoDoc.split("-");
			log.info(tipoUsuarioArray[0]);
			log.info(tipoUsuarioArray[1]);
			tiposDocumentos.setTdocCodigo((Long.parseLong(tipoUsuarioArray[0])));
			tiposDocumentos.setTdocNombre(tipoUsuarioArray[1]);
			clientes.setCliNombre(txtNombre.getValue().toString());
			clientes.setTiposDocumentos(tiposDocumentos);
			clientes.setCliDireccion(txtDireccion.getValue().toString());
			clientes.setCliMail(txtEmail.getValue().toString());
			clientes.setCliId(Long.parseLong(txtId.getValue().toString()));
			clientes.setCliTelefono(txtTelefono.getValue().toString());
			clientes.setTiposDocumentos(tiposDocumentos);
			businessDelegate.saveClientes(clientes);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado exitosamente", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}

		return "";
	}

	public String actionActualizar() {

		try {
			Clientes clientes = new Clientes();
			TiposDocumentos tiposDocumentos = new TiposDocumentos();
			String[] tipoUsuarioArray = tipoDoc1.split("-");
			log.info(tipoUsuarioArray[0]);
			log.info(tipoUsuarioArray[1]);
			tiposDocumentos.setTdocCodigo((Long.parseLong(tipoUsuarioArray[0])));
			tiposDocumentos.setTdocNombre(tipoUsuarioArray[1]);
			clientes.setCliNombre(txtNombre1.getValue().toString());
			clientes.setTiposDocumentos(tiposDocumentos);
			clientes.setCliDireccion(txtDireccion1.getValue().toString());
			clientes.setCliMail(txtEmail1.getValue().toString());
			clientes.setCliId(Long.parseLong(txtId1.getValue().toString()));
			clientes.setCliTelefono(txtTelefono1.getValue().toString());
			clientes.setTiposDocumentos(tiposDocumentos);
			businessDelegate.updateClientes(clientes);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado exitosamente", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}

		return "";
	}

	public String actionBorrar() {
		try {
			businessDelegate.deleteClientes(Long.parseLong(txtId2.getValue().toString()));
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado exitosamente", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de formato", e.getMessage()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}

		return "";
	}

	public String actionBuscar() {
		try {
			Clientes cliente = businessDelegate.getClientesById(Long.parseLong(txtId3.getValue().toString()));
			id = cliente.getCliId() + "";
			nombre = cliente.getCliNombre();
			tipoDocumento = cliente.getTiposDocumentos().getTdocNombre();
			direccion = cliente.getCliDireccion();
			telefono = cliente.getCliTelefono();
			email = cliente.getCliMail();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de formato", e.getMessage()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}

		return "";
	}

	public String actualizarClientesTablas() {
		try {
			clientesTabla = businessDelegate.getClientes();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));

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

	public InputText getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(InputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public InputText getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(InputText txtEmail) {
		this.txtEmail = txtEmail;
	}

	public CommandButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(CommandButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public List<String> getListTiposDocumentos() {
		try {
			List<TiposDocumentos> lista = businessDelegate.getTiposDocumentos();
			listTiposDocumentos = new ArrayList<String>();
			for (TiposDocumentos tiposDocumentos : lista) {
				listTiposDocumentos.add(tiposDocumentos.getTdocCodigo() + "-" + tiposDocumentos.getTdocNombre());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listTiposDocumentos;
	}

	public void setListTiposDocumentos(List<String> listTiposDocumentos) {
		this.listTiposDocumentos = listTiposDocumentos;
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

	public InputText getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(InputText txtTelefono) {
		this.txtTelefono = txtTelefono;
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

	public InputText getTxtDireccion1() {
		return txtDireccion1;
	}

	public void setTxtDireccion1(InputText txtDireccion1) {
		this.txtDireccion1 = txtDireccion1;
	}

	public InputText getTxtTelefono1() {
		return txtTelefono1;
	}

	public void setTxtTelefono1(InputText txtTelefono1) {
		this.txtTelefono1 = txtTelefono1;
	}

	public InputText getTxtEmail1() {
		return txtEmail1;
	}

	public void setTxtEmail1(InputText txtEmail1) {
		this.txtEmail1 = txtEmail1;
	}

	public String getTipoDoc1() {
		return tipoDoc1;
	}

	public void setTipoDoc1(String tipoDoc1) {
		this.tipoDoc1 = tipoDoc1;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Clientes> getClientesTabla() {
		try {
			clientesTabla = businessDelegate.getClientes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return clientesTabla;
	}

	public void setClientesTabla(List<Clientes> clientesTabla) {
		this.clientesTabla = clientesTabla;
	}

}
