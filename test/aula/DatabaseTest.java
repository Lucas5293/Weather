package aula;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class DatabaseTest {
	@Mock
	private Database database = null;

	@Before
	public void setUp() throws SQLException {
		database = mock(Database.class);
		when(database.createTablePrevisao()).thenReturn(true);
		when(database.createTableCidade()).thenReturn(true);
		when(database.query("select * from tbcidade")).thenReturn();
	}
	
	@Test
	public void test1() throws SQLException {
		assertTrue(database.createTableCidade());
	}
	@Test
	public void test2() throws SQLException {
		assertTrue(database.createTablePrevisao());
	}

}
