package aula;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Api {

	public String getXMLCidade(String cidade) throws IOException {
		String charset = java.nio.charset.StandardCharsets.ISO_8859_1.name();
		String linha, resultado = "";
		String urlListaCidade = "http://servicos.cptec.inpe.br/XML/listaCidades?city=%s";
		/* codifica os parâmetros */
		String parametro = String.format(urlListaCidade, URLEncoder.encode(cidade, charset) );
		URL url = new URL(parametro);
		URLConnection conexao = url.openConnection(); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
		while((linha = reader.readLine()) != null){
			resultado += linha;
		}
		return resultado;

	}

	public String getXMLPrevisao(String id) throws IOException {
		String charset = java.nio.charset.StandardCharsets.ISO_8859_1.name();
		String linha, resultado = "";
		String urlListaCidade = "http://servicos.cptec.inpe.br/XML/cidade/7dias/%s/previsao.xml";
		/* codifica os parâmetros */
		String parametro = String.format(urlListaCidade, URLEncoder.encode(id, charset) );
		URL url = new URL(parametro);
		URLConnection conexao = url.openConnection(); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
		while((linha = reader.readLine()) != null){
			resultado += linha;
		}
		return resultado;
	}

	public Cidade[] xmlToObjectCidade(String xml) throws JAXBException{
		 StringReader sr = new StringReader(xml);
		 /* a base do XML é uma marcação de nome cidades */
		 JAXBContext context = JAXBContext.newInstance(Cidades.class);
		 Unmarshaller un = context.createUnmarshaller();
		 Cidades cidades = (Cidades) un.unmarshal(sr);
		 return cidades.getCidade();
	}
	
	public Previsao[] xmlToObjectPrevisao(String xml) throws JAXBException{
		StringReader sr = new StringReader(xml);
		 /* a base do XML é uma marcação de nome cidades */
		 JAXBContext context = JAXBContext.newInstance(Previsoes.class);
		 Unmarshaller un = context.createUnmarshaller();
		 Previsoes previsoes = (Previsoes) un.unmarshal(sr);
		 return previsoes.getPrevisao();
	}

}

