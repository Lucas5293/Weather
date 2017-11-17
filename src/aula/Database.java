package aula;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {
	private Connection conexao = null;

	public Database() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		File bd = new File("bdprevisao.db");
		/* verifica se o arquivo do BD existe na raiz do projeto */
		if (!bd.exists()) {
			/* cria o arquivo do BD na raiz do projeto e cria uma conexão para o BD */
			conexao = DriverManager.getConnection("jdbc:sqlite:bdprevisao.db");
			/* como o BD não existe então é necessário criar as tabelas */
			createTableCidade();
			createTablePrevisao();
		} else {
			/* cria uma conexão com o BD */
			conexao = DriverManager.getConnection("jdbc:sqlite:bdprevisao.db");
		}
		conexao.setAutoCommit(false);
	}

	public boolean createTablePrevisao() throws SQLException {
		Statement stmt = conexao.createStatement();
		String sql = "create table if not exists tbprevisao( " + "id int not null," + "dia date not null,"
				+ "tempo char(3) not null," + "minima float not null," + "maxima float not null,"
				+ "iuv float not null," + "primary key (id, dia)," + "foreign key (id) references tbcidade(id) " + ")";
		stmt.executeUpdate(sql);
		stmt.close();
		return true;
	}

	public boolean createTableCidade() throws SQLException {
		Statement stmt = conexao.createStatement();
		String sql = "create table if not exists tbcidade( " + "id int not null," + "nome char(60) not null,"
				+ "uf char(2) not null," + "atualizacao date not null," + "primary key (id)" + ")";
		stmt.executeUpdate(sql);
		stmt.close();
		return true;
	}

	public abstract Object query(String S);
}
