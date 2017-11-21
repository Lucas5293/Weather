package aula;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ApiTest {

	public Api api = null;
	
	@Before
	public void setUp() {
		api = new Api();
	}
	
	@Test
	public void testGetXMLCidade() throws Exception {
		assertNotNull(api.getXMLCidade("São Jose"));
	}
	@Test
	public void testGetXMLPrevisao() throws Exception {
		assertNotNull(api.getXMLPrevisao("4963"));
	}
	@Test
	public void testXmlToObjectCidade() throws Exception {
		String xml = (String) api.getXMLCidade("São Jose");
		assertNotNull(api.xmlToObjectCidade(xml));
	}
	
	@Test
	public void testXmlToObjectPrevisao() throws Exception {
		String xml = (String) api.getXMLPrevisao("4963");
		assertNotNull(api.xmlToObjectPrevisao(xml));
	}




}
