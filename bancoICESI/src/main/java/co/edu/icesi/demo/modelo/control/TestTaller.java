package co.edu.icesi.demo.modelo.control;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;
import co.edu.icesi.demo.modelo.Cuentas;

@Stateless
public class TestTaller implements ITestTaller {

	private static final Logger log = LoggerFactory.getLogger(TestTaller.class);

	@EJB
	private IClientesLogic clientesLogic;

	@EJB
	private ICuentasLogic cuentasLogic;

	@EJB
	private IConsignacionesLogic consignacionesLogic;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registro(Clientes clientes, List<Cuentas> cuentas) {
		try {
			if (clientes != null) {
				if (clientesLogic.getClientesById(clientes.getCliId()) == null) {
					clientesLogic.saveClientes(clientes);
				} else {
					clientesLogic.updateClientes(clientes);
				}
			}

			if (cuentas != null && !cuentas.isEmpty()) {
				for (Cuentas cuen : cuentas) {
					Consignaciones cons = null;
					if (cuentasLogic.findById(cuen.getCueNumero()) == null) {
						cons = new Consignaciones();
						cons.setCuentas(cuen);
						cons.setId(new ConsignacionesId(90L, cuen.getCueNumero()));
						cons.setConDescripcion("APERTURA DE CUENTA");
						cons.setConFecha(new Date());
						BigDecimal bg = new BigDecimal("100000");
						cons.setConValor(bg);
						consignacionesLogic.save(cons);
						cuen.setCueSaldo(cuen.getCueSaldo().add(bg));
						cuen.setClientes(clientes);
						cuentasLogic.save(cuen);
					} else {
						cons.setCuentas(cuen);
						cons.setId(new ConsignacionesId(100L, cuen.getCueNumero()));
						cons.setConDescripcion("APERTURA DE CUENTA");
						cons.setConFecha(new Date());
						BigDecimal bg = new BigDecimal("100000");
						cons.setConValor(bg);
						consignacionesLogic.save(cons);
						cuen.setCueSaldo(cuen.getCueSaldo().add(bg));
						cuen.setClientes(clientes);
						cuentasLogic.update(cuen);
					}
				}
			}

		} catch (Exception e) {
			log.error("Error", e);
		}
	}

}
