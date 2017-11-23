package aula;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

public class PrevisoesTest {

	Previsoes previsoes;
	
	@Before
	public void setUp() throws IOException, JAXBException {
		String charset = java.nio.charset.StandardCharsets.ISO_8859_1.name();
		String linha, xml = "";
		String urlListaCidade = "http://servicos.cptec.inpe.br/XML/cidade/7dias/%s/previsao.xml";
		/* codifica os parâmetros */
		String parametro = String.format(urlListaCidade, URLEncoder.encode("4326", charset) );
		URL url = new URL(parametro);
		URLConnection conexao = url.openConnection(); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
		while((linha = reader.readLine()) != null){
			xml += linha;
		}
		StringReader sr = new StringReader(xml);
		 /* a base do XML é uma marcação de nome cidades */
		JAXBContext context = JAXBContext.newInstance(Previsoes.class);
		Unmarshaller un = context.createUnmarshaller();
		previsoes = (Previsoes) un.unmarshal(sr);
	}
	
	@Test
	public void testGetPrevisao() {
		assertNotNull(previsoes.getPrevisao());
	}

}
