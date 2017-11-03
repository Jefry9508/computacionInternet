package co.edu.icesi.demo.vista;

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.business.IBusinessDelegate;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Cuentas;

@ManagedBean
@ViewScoped
public class CuentaView {

	@ManagedProperty("#{BusinessDelegate}")
	private IBusinessDelegate businessDelegate;

	private InputText txtCueNumero;
	private InputText txtCliente;
	private InputText txtSaldo;
	private InputText txtActiva;
	private Password txtClave;
	private CommandButton btnGuardar;
	private CommandButton btnActualizar;
	private CommandButton btnBorrar;
	private CommandButton btnBuscar;

	private static final Logger log = LoggerFactory.getLogger(CuentaView.class);

	public String actionGuardar() {
		try {
			Cuentas cuentas = new Cuentas();
			cuentas.setCueNumero(txtCueNumero.getValue().toString());
			cuentas.setCueSaldo(new BigDecimal(txtSaldo.getValue().toString()));
			log.info("-------------------------------------------------->" + cuentas.getCueSaldo().toString());
			cuentas.setCueActiva(txtActiva.getValue().toString());
			cuentas.setCueClave(txtClave.getValue().toString());
			Clientes clientes = (Clientes) businessDelegate
					.getClientesById(Long.parseLong(txtCliente.getValue().toString()));
			cuentas.setClientes(clientes);
			businessDelegate.saveCuentas(cuentas);
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

	public IBusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}

	public void setBusinessDelegate(IBusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	public InputText getTxtCueNumero() {
		return txtCueNumero;
	}

	public void setTxtCueNumero(InputText txtCueNumero) {
		this.txtCueNumero = txtCueNumero;
	}

	public InputText getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(InputText txtCliente) {
		this.txtCliente = txtCliente;
	}

	public InputText getTxtSaldo() {
		return txtSaldo;
	}

	public void setTxtSaldo(InputText txtSaldo) {
		this.txtSaldo = txtSaldo;
	}

	public InputText getTxtActiva() {
		return txtActiva;
	}

	public void setTxtActiva(InputText txtActiva) {
		this.txtActiva = txtActiva;
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

}
