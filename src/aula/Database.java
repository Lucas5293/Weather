package aula;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Database {
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
	
	public void close() throws SQLException {
		conexao.close();
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

	public boolean insertCidade(Cidade cidade) throws SQLException {
		/* o campo atualizacao irá receber o valor padrão, ou seja, null */
		String sql = "insert or ignore into tbcidade(id,nome,uf) values(?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, cidade.getId() );
		stmt.setString(2, cidade.getNome() );
		stmt.setString(3, cidade.getUf() );
		stmt.execute();
		stmt.close();
		conexao.commit();
		return true;
	}

	public boolean insertPrevisao(Previsao previsao) throws SQLException {
		 /* o campo atualizacao irá receber o valor padrão, ou seja, null */
		 String sql = "insert or ignore into tbprevisao(id,dia,tempo,minima,maxima,iuv) values(?,?,?,?,?,?)";
		 PreparedStatement stmt = conexao.prepareStatement(sql);
		 stmt.setInt(1, previsao.getId());
		 stmt.setString(2, previsao.getDia());
		 stmt.setString(3, previsao.getTempo());
		 stmt.setDouble(4, previsao.getMin());
		 stmt.setDouble(5, previsao.getMax());
		 stmt.setDouble(6, previsao.getIuv());
		 stmt.execute();
		 stmt.close();
		 conexao.commit();
		 return true;
	}

	public Object selectCidade(String sql) throws SQLException {
		Statement stmt = conexao.createStatement();
		 ResultSet rs = stmt.executeQuery(sql);
		 List<Cidade> lista = new ArrayList<>();
		 Cidade cidade;
			 while ( rs.next() ) {
				 cidade = new Cidade();
				 cidade.setId(rs.getInt("id"));
				 cidade.setNome(rs.getString("nome"));
				 cidade.setUf(rs.getString("uf"));
				 cidade.setAtualizacao(rs.getString("atualizacao"));
				 lista.add(cidade);
			 }

		 rs.close();
		 stmt.close();
		 conexao.commit();
		 return lista;
	}

	public Object selectPrevisao(String sql) throws SQLException, ParseException{
		 Statement stmt = conexao.createStatement();
		 ResultSet rs = stmt.executeQuery(sql);
		 List<Previsao> lista = new ArrayList<>();
		 Previsao previsao;
		 while ( rs.next() ) {
			 previsao = new Previsao();
			 previsao.setId(rs.getInt("id"));
			 previsao.setDia(rs.getString("dia"));
			 previsao.setTempo(rs.getString("tempo"));
			 previsao.setMin(rs.getDouble("minima"));
			 previsao.setMax(rs.getDouble("maxima"));
			 previsao.setIuv(rs.getDouble("iuv"));
			 lista.add(previsao);
		 }
		 rs.close();
		 stmt.close();
		 conexao.commit();
		 return lista;
	}

	
	

}
