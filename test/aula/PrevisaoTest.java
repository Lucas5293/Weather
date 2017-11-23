package aula;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class PrevisaoTest {

	private Previsao previsao;
	
	@Before
	public void setUp() throws IOException, JAXBException, ParserConfigurationException, SAXException, TransformerException {
		Api api = new Api();
		String xml = (String) api.getXMLPrevisao("4963");
		previsao = api.xmlToObjectPrevisao(xml)[0];
		//System.out.println(previsao.toString());
	}
	
	@Test
	public void testGetId() {
		assertNotNull(previsao.getId());
	}
	
	@Test
	public void testGetTempo() {
		assertNotNull(previsao.getTempo());
	}
	
	@Test
	public void testGetMax() {
		assertNotNull(previsao.getMax());
	}
	
	@Test
	public void testGetMin() {
		assertNotNull(previsao.getMin());
	}
	
	@Test
	public void testGetIuv() {
		assertNotNull(previsao.getIuv());
	}
	
	@Test
	public void testGetDia() {
		assertNotNull(previsao.getDia());
	}
	
	@Test
	public void testSetId() {
		previsao.setId(1);
		assertEquals(1, previsao.getId());
	}
	
	@Test
	public void testSetTempo() {
		previsao.setTempo("c");
		assertEquals("c", previsao.getTempo());
	}
	
	@Test 
	public void testSetMax() {
		previsao.setMax(30);
		assertEquals( 30, previsao.getMax(),0);
	}
	
	@Test
	public void testSetMin() {
		previsao.setMin(22);
		assertEquals(22,previsao.getMin(),0);
	}
	
	@Test
	public void setIuv() {
		previsao.setIuv(11);
		assertEquals(11,previsao.getIuv(),0);
	}
	
	@Test
	public void setDia() throws ParseException {
		previsao.setDia("2016-11-13");
		assertEquals("13/11/2016",previsao.getDia());
	}
	
	

}
