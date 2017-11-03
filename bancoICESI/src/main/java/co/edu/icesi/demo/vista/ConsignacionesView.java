package co.edu.icesi.demo.vista;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.business.IBusinessDelegate;
import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.modelo.Usuarios;

@ManagedBean
@ViewScoped
public class ConsignacionesView {

	@ManagedProperty("#{BusinessDelegate}")
	private IBusinessDelegate businessDelegate;

	private InputText txtCodigo;
	private InputText txtCuenta;
	private InputText txtUsuario;
	private InputText txtValor;
	private InputTextarea txtDescripcion;
	private CommandButton btnGuardar;
	private CommandButton btnActualizar;
	private CommandButton btnBorrar;
	private CommandButton btnBuscar;

	private static final Logger log = LoggerFactory.getLogger(ConsignacionesView.class);

	public String actionGuardar() {
		try {
			Consignaciones consignaciones = new Consignaciones();
			ConsignacionesId consignacionesId = new ConsignacionesId();
			consignacionesId.setConCodigo(Long.parseLong(txtCodigo.getValue().toString()));
			consignacionesId.setCueNumero(txtCuenta.getValue().toString());
			consignaciones.setId(consignacionesId);
			Cuentas cuentas = businessDelegate.findByIdCuentas(txtCuenta.getValue().toString());
			consignaciones.setCuentas(cuentas);
			Usuarios usuarios = businessDelegate.findById(Long.parseLong(txtUsuario.getValue().toString()));
			consignaciones.setUsuarios(usuarios);
			consignaciones.setConValor(new BigDecimal(txtValor.getValue().toString()));
			consignaciones.setConFecha(new Date());
			consignaciones.setConDescripcion(txtDescripcion.getValue().toString());
			businessDelegate.saveConsignaciones(consignaciones);
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

	public InputText getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(InputText txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public InputText getTxtCuenta() {
		return txtCuenta;
	}

	public void setTxtCuenta(InputText txtCuenta) {
		this.txtCuenta = txtCuenta;
	}

	public InputText getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(InputText txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public InputText getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(InputText txtValor) {
		this.txtValor = txtValor;
	}

	public InputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
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
