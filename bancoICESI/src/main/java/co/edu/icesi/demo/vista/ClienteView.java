package co.edu.icesi.demo.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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

	@ManagedProperty("#{BusinessDelegate}")
	private IBusinessDelegate businessDelegate;

	private InputText txtNombre;
	private InputText txtId;
	private InputText txtDireccion;
	private InputText txtEmail;
	private CommandButton btnGuardar;
	private CommandButton btnActualizar;
	private CommandButton btnBorrar;
	private CommandButton btnBuscar;
	private InputText txtTelefono;
	private String tipoDoc;
	private List<String> listTiposDocumentos;

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

}
