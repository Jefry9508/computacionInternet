package co.edu.icesi.demo.modelo.control.webservices;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import co.edu.icesi.demo.modelo.control.ITiposDocumentosLogic;

@Stateless
@WebService(portName = "ConsultarTipoDocPort", serviceName = "ConsultarTipoDocService", targetNamespace = "http://webservices.control.modelo.demo.icesi.edu.co/wsdl", endpointInterface = "co.edu.icesi.demo.modelo.control.webservices.IConsultarTipoDoc")
public class ConsultarTipoDoc implements IConsultarTipoDoc {

	@EJB
	private ITiposDocumentosLogic tipoDocLogic;

	@Override
	public String consultarTipoDoc(Long id) throws Exception {
		String nombreTipoDoc = tipoDocLogic.getTiposDocumentosById(id).getTdocNombre();
		return nombreTipoDoc;
	}

}
