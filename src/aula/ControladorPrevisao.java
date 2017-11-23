package aula;

import java.text.Normalizer;
import java.util.List;

public class ControladorPrevisao {

	private Api api = new Api();
	private Database database;

	public int getCidade(String strCidade) throws Exception {
		database = new Database();
		List<Cidade> cidades = database.selectCidade("Select * from tbcidade where LOWER(nome) LIKE LOWER(\'%"+strCidade+"%\');");
		for(Cidade c: cidades) {
			if(comparaCidade(strCidade,c.getNome())) {
				return c.getId();
			}
		}
		System.out.println("Pegando XML da cidade do servidor");
		Cidade cidade = null;
		String xml = api.getXMLCidade(strCidade);
		Cidade[] cidadesAdd = api.xmlToObjectCidade(xml);
		if(cidadesAdd == null) {
			System.out.println("Cidade não encontrada");
			return -1;
		}
		for(Cidade c : cidadesAdd) {
			database.insertCidade(c);
		}
		return cidadesAdd[0].getId();
	}

	public Object getPrevisao(String string) {
		
		return null;
	}

	public boolean comparaCidade(String string, String string2) {
		//System.out.println(string+":"+string2);
		if (string.toLowerCase() == string2.toLowerCase()) 
			return true;
		if (string2.toLowerCase().contains(string.toLowerCase()))
			return true;		
		return false;
	}

}
