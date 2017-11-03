package co.edu.icesi.demo.modelo;
// Generated 20/08/2017 07:16:01 PM by Hibernate Tools 5.1.4.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clientes generated by hbm2java
 */
@Entity
@Table(name = "clientes", schema = "public")
public class Clientes implements java.io.Serializable {

	private long cliId;
	private TiposDocumentos tiposDocumentos;
	private String cliNombre;
	private String cliDireccion;
	private String cliTelefono;
	private String cliMail;
	private Set<Cuentas> cuentases = new HashSet<Cuentas>(0);

	public Clientes() {
	}

	public Clientes(long cliId, TiposDocumentos tiposDocumentos, String cliNombre, String cliDireccion,
			String cliTelefono) {
		this.cliId = cliId;
		this.tiposDocumentos = tiposDocumentos;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliTelefono = cliTelefono;
	}

	public Clientes(long cliId, TiposDocumentos tiposDocumentos, String cliNombre, String cliDireccion,
			String cliTelefono, String cliMail, Set<Cuentas> cuentases) {
		this.cliId = cliId;
		this.tiposDocumentos = tiposDocumentos;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliTelefono = cliTelefono;
		this.cliMail = cliMail;
		this.cuentases = cuentases;
	}

	@Id

	@Column(name = "cli_id", unique = true, nullable = false, precision = 10, scale = 0)
	public long getCliId() {
		return this.cliId;
	}

	public void setCliId(long cliId) {
		this.cliId = cliId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tdoc_codigo", nullable = false)
	public TiposDocumentos getTiposDocumentos() {
		return this.tiposDocumentos;
	}

	public void setTiposDocumentos(TiposDocumentos tiposDocumentos) {
		this.tiposDocumentos = tiposDocumentos;
	}

	@Column(name = "cli_nombre", nullable = false, length = 50)
	public String getCliNombre() {
		return this.cliNombre;
	}

	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}

	@Column(name = "cli_direccion", nullable = false, length = 50)
	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	@Column(name = "cli_telefono", nullable = false, length = 50)
	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	@Column(name = "cli_mail", length = 50)
	public String getCliMail() {
		return this.cliMail;
	}

	public void setCliMail(String cliMail) {
		this.cliMail = cliMail;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientes")
	public Set<Cuentas> getCuentases() {
		return this.cuentases;
	}

	public void setCuentases(Set<Cuentas> cuentases) {
		this.cuentases = cuentases;
	}

}
