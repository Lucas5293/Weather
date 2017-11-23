package aula;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ControladorPrevisao {

	private Api api = new Api();
	private Database database;

	public Cidade getCidade(String strCidade) throws Exception {
		database = new Database();
		List<Cidade> cidades = database.selectCidade("Select * from tbcidade where LOWER(nome) LIKE LOWER(\'%"+strCidade+"%\');");
		for(Cidade c: cidades) {
			if(comparaCidade(strCidade,c.getNome())) {
				database.close();
				return c;
			}
		}
		
		String xml = api.getXMLCidade(strCidade);
		Cidade[] cidadesAdd = api.xmlToObjectCidade(xml);
		if(cidadesAdd == null) {
			database.close();
			return null;
		}
		for(Cidade c : cidadesAdd) {
			database.insertCidade(c);
		}
		database.close();
		return cidadesAdd[0];
	}

	public List<Previsao> getPrevisao(Cidade cidade) throws Exception {
		int cidadeId = cidade.getId();
		database = new Database();
		List<Previsao> previsoes = database.selectPrevisao("select * from tbprevisao where id="+cidadeId+";");
		
		Calendar calendar = new GregorianCalendar();
		Date today = new Date();
		calendar.setTime(today);	
		SimpleDateFormat formatador = new SimpleDateFormat("MM/dd/yyyy");
		String hoje = formatador.format(today);
		
		for(Previsao previsao: previsoes) {
			if (cidade.getAtualizacao()!= null && cidade.getAtualizacao().equals(hoje)) {
				return previsoes;
			}
			else {
				database.dropPrevisao(previsao.getId());
			}
		}
		database.updateCidade(cidadeId);
		String xml = api.getXMLPrevisao(String.valueOf(cidadeId));
		Previsao [] previsoesAdd = api.xmlToObjectPrevisao(xml);
		
		if(previsoesAdd == null) {
			System.out.println("Previsão não encontrada");
			return null;
		}
		List<Previsao> retorno = new ArrayList<>();
		for(Previsao p : previsoesAdd) {
			p.setId(cidadeId);
			database.insertPrevisao(p);
			retorno.add(p);
		}
		
		return retorno;
	}

	public boolean comparaCidade(String string, String string2) {
		if (string.equals(""))
			return false;
		if (string.toLowerCase() == string2.toLowerCase()) 
			return true;
		if (string2.toLowerCase().contains(string.toLowerCase()))
			return true;		
		return false;
	}
	public boolean dataPassada(Date date, Date date2) {
		return date.before(date2);
	}
	
	public String converteData(String data) {
		String dt [] = data.split("/");
		if (dt.length!=3)
			return "0/0/0";
		else
			return dt[1]+"/"+dt[0]+"/"+dt[2];
	}


}
