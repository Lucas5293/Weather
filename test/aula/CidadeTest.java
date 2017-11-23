package aula;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CidadeTest {
	
	private Cidade cidade;

	@Before
	public void setUp() throws Exception {
		Api api = new Api();
		String xml = (String) api.getXMLCidade("Jacareí");
		cidade = api.xmlToObjectCidade(xml)[0];
		cidade.setAtualizacao("12/12/2017");
	}
	
	@Test
	public void testGetId() {
		assertEquals(2680, cidade.getId());
	}
	
	@Test
	public void testGetNome() {
		assertNotNull(cidade.getNome());
	}
	
	@Test
	public void testGetUf() {
		assertEquals("SP",cidade.getUf());
	}
	
	@Test
	public void testGetAtualizacao() {
		assertEquals("12/12/2017",cidade.getAtualizacao());
	}
	
	@Test
	public void testSetId() {
		cidade.setId(123);
		assertEquals(123, cidade.getId());
	}
	
	@Test
	public void testSetNome() {
		cidade.setNome("Pelotas");
		assertNotNull(cidade.getNome());
	}
	
	@Test
	public void testSetUf() {
		cidade.setUf("RS");
		assertEquals("RS", cidade.getUf());
	}
}
