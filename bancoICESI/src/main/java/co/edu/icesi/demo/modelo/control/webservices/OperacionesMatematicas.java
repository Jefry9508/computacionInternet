package co.edu.icesi.demo.modelo.control.webservices;

import javax.jws.WebService;

@WebService(portName = "OperacionesMatematicasPort", serviceName = "OperacionesMatematicasService", targetNamespace = "http://webservices.control.modelo.demo.icesi.edu.co/wsdl", endpointInterface = "co.edu.icesi.demo.modelo.control.webservices.IOperacionesMatematicas")
public class OperacionesMatematicas implements IOperacionesMatematicas {

	@Override
	public int sumar(int n1, int n2) {

		return n1 + n2;
	}

}
