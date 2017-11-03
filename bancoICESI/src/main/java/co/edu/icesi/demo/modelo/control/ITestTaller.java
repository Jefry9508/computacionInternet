package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Cuentas;

@Local
public interface ITestTaller {

	public void registro(Clientes clientes, List<Cuentas> cuentas);

}
