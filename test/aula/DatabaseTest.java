package aula;

import java.sql.SQLException;
import java.text.ParseException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	
	public Database database=null;	
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		database = new Database();
	}
	
	@After
	public void setDown() throws SQLException {
		database.close();
	}
		
	@Test
	public void testCreateTableCidade() throws SQLException {
		assertTrue(database.createTableCidade());
	}
	
	@Test
	public void testCreateTablePrevisao() throws SQLException {
		assertTrue(database.createTablePrevisao());
	}
	
	@Test
	public void testInsertCidade() throws SQLException {
		Cidade cidade = new Cidade();
		cidade.setId(1);
		assertTrue(database.insertCidade(cidade));
	}
	@Test
	public void testInsertPrevisao() throws SQLException {
		Previsao previsao = new Previsao();
		assertTrue(database.insertPrevisao(previsao));
	}
	
	@Test
	public void testDropPrevisao() throws SQLException {
		assertTrue(database.dropPrevisao(0));
	}
	
	@Test
	public void testUpdateCidade() throws SQLException {
		assertTrue(database.updateCidade(4963));
	}
	
	@Test 
	public void testSelectCidade() throws SQLException{
		assertNotNull(database.selectCidade("select * from tbcidade;"));
	}
	@Test 
	public void testSelectPrevisao() throws SQLException, ParseException{
		assertNotNull(database.selectPrevisao("select * from tbprevisao;"));
	}
	
	
	
	
	
}
