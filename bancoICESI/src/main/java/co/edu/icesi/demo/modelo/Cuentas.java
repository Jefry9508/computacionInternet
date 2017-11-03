package co.edu.icesi.demo.modelo;
// Generated 20/08/2017 07:16:01 PM by Hibernate Tools 5.1.4.Final

import java.math.BigDecimal;
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
 * Cuentas generated by hbm2java
 */
@Entity
@Table(name = "cuentas", schema = "public")
public class Cuentas implements java.io.Serializable {

	private String cueNumero;
	private Clientes clientes;
	private BigDecimal cueSaldo;
	private String cueActiva;
	private String cueClave;
	private Set<Consignaciones> consignacioneses = new HashSet<Consignaciones>(0);
	private Set<Retiros> retiroses = new HashSet<Retiros>(0);

	public Cuentas() {
	}

	public Cuentas(String cueNumero, Clientes clientes, BigDecimal cueSaldo, String cueActiva, String cueClave) {
		this.cueNumero = cueNumero;
		this.clientes = clientes;
		this.cueSaldo = cueSaldo;
		this.cueActiva = cueActiva;
		this.cueClave = cueClave;
	}

	public Cuentas(String cueNumero, Clientes clientes, BigDecimal cueSaldo, String cueActiva, String cueClave,
			Set<Consignaciones> consignacioneses, Set<Retiros> retiroses) {
		this.cueNumero = cueNumero;
		this.clientes = clientes;
		this.cueSaldo = cueSaldo;
		this.cueActiva = cueActiva;
		this.cueClave = cueClave;
		this.consignacioneses = consignacioneses;
		this.retiroses = retiroses;
	}

	@Id

	@Column(name = "cue_numero", unique = true, nullable = false, length = 30)
	public String getCueNumero() {
		return this.cueNumero;
	}

	public void setCueNumero(String cueNumero) {
		this.cueNumero = cueNumero;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cli_id", nullable = false)
	public Clientes getClientes() {
		return this.clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	@Column(name = "cue_saldo", nullable = false, precision = 10)
	public BigDecimal getCueSaldo() {
		return this.cueSaldo;
	}

	public void setCueSaldo(BigDecimal cueSaldo) {
		this.cueSaldo = cueSaldo;
	}

	@Column(name = "cue_activa", nullable = false, length = 2)
	public String getCueActiva() {
		return this.cueActiva;
	}

	public void setCueActiva(String cueActiva) {
		this.cueActiva = cueActiva;
	}

	@Column(name = "cue_clave", nullable = false, length = 50)
	public String getCueClave() {
		return this.cueClave;
	}

	public void setCueClave(String cueClave) {
		this.cueClave = cueClave;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuentas")
	public Set<Consignaciones> getConsignacioneses() {
		return this.consignacioneses;
	}

	public void setConsignacioneses(Set<Consignaciones> consignacioneses) {
		this.consignacioneses = consignacioneses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuentas")
	public Set<Retiros> getRetiroses() {
		return this.retiroses;
	}

	public void setRetiroses(Set<Retiros> retiroses) {
		this.retiroses = retiroses;
	}

}
