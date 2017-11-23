package aula;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ControladorPrevisao {

	private Api api = new Api();
	private Database database;

	public int getCidade(String strCidade) throws Exception {
		database = new Database();
		List<Cidade> cidades = database.selectCidade("Select * from tbcidade;");
		for(Cidade c: cidades) {
			if(c.getNome().toLowerCase().equals(strCidade.toLowerCase())) {
				System.out.println(strCidade+": ex");
				return c.getId();
			}
		}
		Cidade cidade = null;
		String xml = api.getXMLCidade(strCidade);
		Cidade[] cidadesAdd = api.xmlToObjectCidade(xml);
		if(cidadesAdd == null)
			return -1;
		for(Cidade c : cidadesAdd) {
			database.insertCidade(c);
		}
		return cidadesAdd[0].getId();
	}
	//userTratada = Normalizer.normalize(userTratada, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

	public Object getPrevisao(String string) {
		
		return null;
	}

}
