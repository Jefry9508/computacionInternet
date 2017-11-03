package co.edu.icesi.demo.modelo.control.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://webservices.control.modelo.demo.icesi.edu.co/wsdl")
public interface IOperacionesMatematicas {

	@WebMethod(operationName = "sumar")
	public int sumar(@WebParam(name = "numero1") int n1, @WebParam(name = "numero2") int n2);

}
