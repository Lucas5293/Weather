package aula;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ControladorPrevisaoTest {
	
	ControladorPrevisao controladorPrevisao;

	@Before
	public void setUp() {
		controladorPrevisao = new ControladorPrevisao();
	}
	
	@Test
	public void testGetCidade() throws Exception {
		assertNotNull(controladorPrevisao.getCidade("Jacareí"));
	}
	
	@Test
	public void testGetCidadeVazio() throws Exception {
		//assertEquals(-1,controladorPrevisao.getCidade(""));
	}
	
	@Test
	public void testGetCidadeInexistente() throws Exception {
		assertEquals(-1,controladorPrevisao.getCidade("abc"));
	}

}
