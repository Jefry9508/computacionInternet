package co.edu.icesi.demo.modelo.control.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://webservices.control.modelo.demo.icesi.edu.co/wsdl")
public interface IConsultarTipoDoc {

	@WebMethod(operationName = "consultarTipoDoc")
	public String consultarTipoDoc(@WebParam(name = "idTipoDoc") Long id) throws Exception;
}
