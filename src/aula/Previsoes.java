package aula;

import java.text.ParseException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cidade")
@XmlType(propOrder = {"atualizacao","previsao"})
public class Previsoes {
	
	private int id = 0;
	
	@XmlElement
	private String atualizacao;
	
	@XmlElement
	private Previsao[] previsao;
	
	@XmlTransient
	public String getAtualizacao(){
		return atualizacao;
	}
	
	public void setAtualizacao(String atualizacao) throws ParseException{
		this.atualizacao =  atualizacao;
	}
	
	public Previsao[] getPrevisao() {
		return previsao;
	}

}
