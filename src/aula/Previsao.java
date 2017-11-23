package aula;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "previsao")
@XmlType(propOrder = {"dia", "tempo", "maxima", "minima", "iuv","id"})
public class Previsao {
	@XmlElement(name = "dia")
	private String dia;
	@XmlElement(name = "tempo")
	private String tempo;
	@XmlElement(name = "maxima")
	private double maxima;
	@XmlElement(name = "minima")
	private double minima;
	@XmlElement(name = "iuv")
	private double iuv;
	
	private int id;
	
	@XmlTransient
	public String getDia(){
		return dia;
	}
	
	public void setDia(String dia) throws ParseException{
		if(dia.equals("null")){
			this.dia = dia;
		}
		else{
			DateFormat formatUS = new SimpleDateFormat("yyyy-MM-dd");
			Date data = formatUS.parse(dia);
			DateFormat formatBR = new SimpleDateFormat("dd/MM/yyyy");
			this.dia = formatBR.format(data);
		}
	}
	
	@XmlTransient
	public String getTempo(){
		return tempo;
	}
	
	public void setTempo(String tempo){
		this.tempo = tempo;
	}
	
	@XmlTransient
	public double getMax(){
		return maxima;
	}
	
	public void setMax(double maxima){
		this.maxima = maxima;
	}
	
	@XmlTransient
	public double getMin(){
		return minima;
	}
	
	public void setMin(double minima){
		this.minima = minima;
	}
	
	@XmlTransient
	public double getIuv(){
		return iuv;
	}
	
	public void setIuv(double iuv){
		this.iuv = iuv;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String toString(){
		return "Id"+getId()+"\nDia: " + getDia() + "\nTempo: " + getTempo() +  "\nIUV: " + getIuv() + "\nMínima: " + getMin() + "\nMáxima: " + getMax();	
	}

}