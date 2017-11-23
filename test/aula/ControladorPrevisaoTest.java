package aula;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ControladorPrevisaoTest {
	
	ControladorPrevisao controladorPrevisao;

	@Before
	public void setUp() {
		controladorPrevisao = new ControladorPrevisao();
	}
	
	@Test
	public void testComparaCidade() {
		assertTrue(controladorPrevisao.comparaCidade("são","são josé"));
	}
	@Test
	public void testComparaCidade2() {
		assertFalse(controladorPrevisao.comparaCidade("sãe","são josé"));
	}
	
	@Test
	public void testGetCidade() throws Exception {
		assertNotNull(controladorPrevisao.getCidade("Jacareí"));
	}
	
	@Test
	public void testGetCidadeVazio() throws Exception {
		assertNotNull(controladorPrevisao.getCidade("São"));
	}
	
	@Test
	public void testGetCidadeInexistente() throws Exception {
		assertEquals(-1,controladorPrevisao.getCidade("abc"));
	}

}
