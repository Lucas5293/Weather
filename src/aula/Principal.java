package aula;

import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) {
		try {
			Database d = new Database();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
