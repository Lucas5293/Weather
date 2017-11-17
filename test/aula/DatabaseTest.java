package aula;

import java.sql.SQLException;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class DatabaseTest {
	@Mock
	private Database database = null;

	@Before
	public void setUp() throws SQLException, ClassNotFoundException {
		database = mock(Database.class);
		when(database.createTableCidade()).thenReturn(true);
		when(database.createTablePrevisao()).thenReturn(true);
		when(database.query("select * from tbcidade")).thenReturn(new Object());
		when(database.query("")).thenReturn(null);
	}

	@Test
	public void queryTest() {
		assertNotNull(database.query("select * from tbcidade"));
		assertNull(database.query(""));
	}

	@Test
	public void createTableCidadeTest() throws SQLException {
		assertTrue(database.createTableCidade());
	}

	@Test
	public void createTablePrevisaoTest() throws SQLException {
		assertTrue(database.createTablePrevisao());
	}

}
