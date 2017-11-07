package co.edu.icesi.demo.vista;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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

	@EJB
	private IBusinessDelegate businessDelegate;

	private InputText txtCodigo;
	private InputText txtCuenta;
	private InputText txtUsuario;
	private InputText txtValor;
	private InputTextarea txtDescripcion;
	private InputText txtCodigo1;
	private InputText txtCuenta1;
	private InputText txtUsuario1;
	private InputText txtValor1;
	private InputTextarea txtDescripcion1;
	private InputText txtCuenta2;
	private InputText txtCuenta3;
	private InputText txtCodigo2;
	private InputText txtCodigo3;
	private CommandButton btnGuardar;
	private CommandButton btnActualizar;
	private CommandButton btnBorrar;
	private CommandButton btnBuscar;
	private String id;
	private String cuenta;
	private String usuario;
	private String valor;
	private String fecha;
	private String descripcion;
	private List<Consignaciones> consignacionesTabla;

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
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado exitosamente", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}

		return "";
	}

	public String actionActualizar() {

		try {
			Consignaciones consignaciones = new Consignaciones();
			ConsignacionesId consignacionesId = new ConsignacionesId();
			consignacionesId.setConCodigo(Long.parseLong(txtCodigo1.getValue().toString()));
			consignacionesId.setCueNumero(txtCuenta1.getValue().toString());
			consignaciones.setId(consignacionesId);
			Cuentas cuentas = businessDelegate.findByIdCuentas(txtCuenta1.getValue().toString());
			consignaciones.setCuentas(cuentas);
			Usuarios usuarios = businessDelegate.findById(Long.parseLong(txtUsuario1.getValue().toString()));
			consignaciones.setUsuarios(usuarios);
			consignaciones.setConValor(new BigDecimal(txtValor1.getValue().toString()));
			consignaciones.setConFecha(new Date());
			consignaciones.setConDescripcion(txtDescripcion1.getValue().toString());
			businessDelegate.updateConsignaciones(consignaciones);
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
			ConsignacionesId idCon = new ConsignacionesId();
			idCon.setConCodigo(Long.parseLong(txtCodigo2.getValue().toString()));
			idCon.setCueNumero(txtCuenta2.getValue().toString());
			Consignaciones consignaciones = businessDelegate.findByIdConsignaciones(idCon);
			businessDelegate.deleteConsignaciones(consignaciones);
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
			ConsignacionesId idCon = new ConsignacionesId();
			idCon.setConCodigo(Long.parseLong(txtCodigo3.getValue().toString()));
			idCon.setCueNumero(txtCuenta3.getValue().toString());
			Consignaciones consignaciones = businessDelegate.findByIdConsignaciones(idCon);
			id = consignaciones.getId().getConCodigo() + "";
			cuenta = consignaciones.getCuentas().getCueNumero();
			usuario = consignaciones.getUsuarios().getUsuNombre();
			valor = consignaciones.getConValor().toString();
			fecha = consignaciones.getConFecha().toString();
			descripcion = consignaciones.getConDescripcion();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}

		return "";
	}

	public String actualizarConsignacionesTablas() {
		try {
			consignacionesTabla = businessDelegate.findAllConsignaciones();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
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

	public InputText getTxtCodigo1() {
		return txtCodigo1;
	}

	public void setTxtCodigo1(InputText txtCodigo1) {
		this.txtCodigo1 = txtCodigo1;
	}

	public InputText getTxtCuenta1() {
		return txtCuenta1;
	}

	public void setTxtCuenta1(InputText txtCuenta1) {
		this.txtCuenta1 = txtCuenta1;
	}

	public InputText getTxtUsuario1() {
		return txtUsuario1;
	}

	public void setTxtUsuario1(InputText txtUsuario1) {
		this.txtUsuario1 = txtUsuario1;
	}

	public InputText getTxtValor1() {
		return txtValor1;
	}

	public void setTxtValor1(InputText txtValor1) {
		this.txtValor1 = txtValor1;
	}

	public InputTextarea getTxtDescripcion1() {
		return txtDescripcion1;
	}

	public void setTxtDescripcion1(InputTextarea txtDescripcion1) {
		this.txtDescripcion1 = txtDescripcion1;
	}

	public InputText getTxtCodigo2() {
		return txtCodigo2;
	}

	public void setTxtCodigo2(InputText txtCodigo2) {
		this.txtCodigo2 = txtCodigo2;
	}

	public InputText getTxtCodigo3() {
		return txtCodigo3;
	}

	public void setTxtCodigo3(InputText txtCodigo3) {
		this.txtCodigo3 = txtCodigo3;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Consignaciones> getConsignacionesTabla() {
		try {
			consignacionesTabla = businessDelegate.findAllConsignaciones();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return consignacionesTabla;
	}

	public void setConsignacionesTabla(List<Consignaciones> consignacionesTabla) {
		this.consignacionesTabla = consignacionesTabla;
	}

	public InputText getTxtCuenta2() {
		return txtCuenta2;
	}

	public void setTxtCuenta2(InputText txtCuenta2) {
		this.txtCuenta2 = txtCuenta2;
	}

	public InputText getTxtCuenta3() {
		return txtCuenta3;
	}

	public void setTxtCuenta3(InputText txtCuenta3) {
		this.txtCuenta3 = txtCuenta3;
	}

}
