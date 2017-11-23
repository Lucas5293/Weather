package aula;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Api {

	public String getXMLCidade(String cidade) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		
		String charset = java.nio.charset.StandardCharsets.ISO_8859_1.name();
		String resultado = "";
		String urlListaCidade = "http://servicos.cptec.inpe.br/XML/listaCidades?city=%s";
		/* codifica os parâmetros */
		String parametro = String.format(urlListaCidade, URLEncoder.encode(cidade, charset) );
	
		URL url = new URL(parametro);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(url.openStream());
		
		resultado = printDocument(doc);

		return resultado;
	}

	public String getXMLPrevisao(String id) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		String charset = java.nio.charset.StandardCharsets.ISO_8859_1.name();
		String resultado = "";
		String urlListaCidade = "http://servicos.cptec.inpe.br/XML/cidade/7dias/%s/previsao.xml";
		/* codifica os parâmetros */
		String parametro = String.format(urlListaCidade, URLEncoder.encode(id, charset) );
		URL url = new URL(parametro);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(url.openStream());
		
		resultado = printDocument(doc);

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
	
	public static String printDocument(Document doc) throws IOException, TransformerException {
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		transformer.transform(new DOMSource(doc), result);
		return writer.toString();
	}

}

