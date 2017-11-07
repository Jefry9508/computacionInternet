package co.edu.icesi.test.ws;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.modelo.control.webservices.wsdl.IConsultarTipoDoc;
import co.edu.icesi.demo.modelo.control.webservices.wsdl.IConsultarTipoDocProxy;

public class TestWs {

	private static final Logger log = LoggerFactory.getLogger(TestWs.class);

	@Test
	public void test() {
		try {
			IConsultarTipoDoc consultarTipoDocWs = new IConsultarTipoDocProxy();
			String tipoDoc = consultarTipoDocWs.consultarTipoDoc(10L);
			log.info(tipoDoc);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
