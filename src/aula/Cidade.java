package aula;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cidade")
@XmlType(propOrder = {"nome", "uf", "id","atualizacao"})
public class Cidade {
	@XmlElement(name = "id")
	private Integer id;
	@XmlElement(name = "nome")
	private String nome;
	@XmlElement(name = "uf")
	private String uf;
	
	private String atualizacao;
	
	@XmlTransient
	public int getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	@XmlTransient
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	@XmlTransient
	public String getUf(){
		return uf;
	}
	
	public void setUf(String uf){
		this.uf = uf;
	}

	public String getAtualizacao() {
		return atualizacao;
	}
	
	public void setAtualizacao(String atualizacao) {
		this.atualizacao = atualizacao;
		
	}
	
	public String toString(){
		return "ID: " + getId() + "\nCidade: " + getNome() + "\nUF: " + getUf() + "\nAtualização: " + getAtualizacao();	
	}
	
}
