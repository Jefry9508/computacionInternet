package co.edu.icesi.demo.vista;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
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
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Cuentas;

@ManagedBean
@ViewScoped
public class CuentaView {

	@EJB
	private IBusinessDelegate businessDelegate;

	private InputText txtCueNumero;
	private InputText txtCliente;
	private InputText txtSaldo;
	private InputText txtActiva;
	private Password txtClave;
	private InputText txtCueNumero1;
	private InputText txtCliente1;
	private InputText txtSaldo1;
	private InputText txtActiva1;
	private Password txtClave1;
	private InputText txtCueNumero2;
	private InputText txtCueNumero3;
	private CommandButton btnGuardar;
	private CommandButton btnActualizar;
	private CommandButton btnBorrar;
	private CommandButton btnBuscar;
	private String numCuenta;
	private String cliente;
	private String saldo;
	private String activa;
	private String clave;
	private List<Cuentas> cuentasTabla;

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

		try {
			Cuentas cuentas = new Cuentas();
			cuentas.setCueNumero(txtCueNumero1.getValue().toString());
			cuentas.setCueSaldo(new BigDecimal(txtSaldo1.getValue().toString()));
			cuentas.setCueActiva(txtActiva1.getValue().toString());
			cuentas.setCueClave(txtClave1.getValue().toString());
			Clientes clientes = (Clientes) businessDelegate
					.getClientesById(Long.parseLong(txtCliente1.getValue().toString()));
			cuentas.setClientes(clientes);
			businessDelegate.updateCuentas(cuentas);
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
			businessDelegate.deleteCuentas(txtCueNumero2.getValue().toString());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado exitosamente", ""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}

		return "";
	}

	public String actionBuscar() {
		try {
			Cuentas cuenta = businessDelegate.findByIdCuentas(txtCueNumero3.getValue().toString());
			numCuenta = cuenta.getCueNumero();
			cliente = cuenta.getClientes().getCliNombre();
			saldo = cuenta.getCueSaldo().toString();
			activa = cuenta.getCueActiva();
			clave = cuenta.getCueClave();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}

		return "";
	}

	public String actualizarCuentasTablas() {
		try {
			cuentasTabla = businessDelegate.findAllCuentas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public InputText getTxtCueNumero1() {
		return txtCueNumero1;
	}

	public void setTxtCueNumero1(InputText txtCueNumero1) {
		this.txtCueNumero1 = txtCueNumero1;
	}

	public InputText getTxtCliente1() {
		return txtCliente1;
	}

	public void setTxtCliente1(InputText txtCliente1) {
		this.txtCliente1 = txtCliente1;
	}

	public InputText getTxtSaldo1() {
		return txtSaldo1;
	}

	public void setTxtSaldo1(InputText txtSaldo1) {
		this.txtSaldo1 = txtSaldo1;
	}

	public InputText getTxtActiva1() {
		return txtActiva1;
	}

	public void setTxtActiva1(InputText txtActiva1) {
		this.txtActiva1 = txtActiva1;
	}

	public Password getTxtClave1() {
		return txtClave1;
	}

	public void setTxtClave1(Password txtClave1) {
		this.txtClave1 = txtClave1;
	}

	public InputText getTxtCueNumero2() {
		return txtCueNumero2;
	}

	public void setTxtCueNumero2(InputText txtCueNumero2) {
		this.txtCueNumero2 = txtCueNumero2;
	}

	public InputText getTxtCueNumero3() {
		return txtCueNumero3;
	}

	public void setTxtCueNumero3(InputText txtCueNumero3) {
		this.txtCueNumero3 = txtCueNumero3;
	}

	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getActiva() {
		return activa;
	}

	public void setActiva(String activa) {
		this.activa = activa;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public List<Cuentas> getCuentasTabla() {
		try {
			cuentasTabla = businessDelegate.findAllCuentas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return cuentasTabla;
	}

	public void setCuentasTabla(List<Cuentas> cuentasTabla) {
		this.cuentasTabla = cuentasTabla;
	}

}
