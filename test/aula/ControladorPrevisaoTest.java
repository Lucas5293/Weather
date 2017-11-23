package aula;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

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
	public void testComparaCidade3() {
		assertFalse(controladorPrevisao.comparaCidade("","são josé"));
	}
	
	@Test
	public void testGetCidade() throws Exception {
		assertNotNull(controladorPrevisao.getCidade("Jacareí"));
	}
	
	@Test
	public void testGetCidade2() throws Exception {
		assertNotNull(controladorPrevisao.getCidade("São José dos Campos"));
	}
	
	@Test
	public void testGetCidadeInexistente() throws Exception {
		assertNull(controladorPrevisao.getCidade("abc"));
	}
	
	@Test
	public void testGetPrevisao() throws Exception{
		Cidade cidade = new Cidade();
		cidade.setId(4963);
		cidade.defaultAtualizacao();
		assertNotNull(controladorPrevisao.getPrevisao(cidade));
	}
		
	@Test
	public void testGetPrevisao2() throws Exception{
		Cidade cidade = new Cidade();
		cidade.setId(4758);
		cidade.defaultAtualizacao();
		assertNotNull(controladorPrevisao.getPrevisao(cidade));
	}
	
	@Test
	public void testGetCidadePrevisao() throws Exception{
		Cidade cidade = controladorPrevisao.getCidade("São Bento do Sapucaí");
		assertNotNull(controladorPrevisao.getPrevisao(cidade));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDataPassada() {
		assertFalse(controladorPrevisao.dataPassada(new Date("11/12/2015"), new Date("10/11/2015")));
	}
	
	
	
	

}
