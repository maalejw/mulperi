package com.mulperi.services;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.management.IntrospectionException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.mulperi.models.selections.AttributeSelection;
import com.mulperi.models.selections.FeatureSelection;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * 
 * Client used in sending models and configurations to CaaS.
 *
 */
public class CaasClient {
	
	/**
	 * Sends the Kumbang model to the specified CaaS address
	 * @param modelName
	 * @param modelContent
	 * @param caasAddress
	 * @return response from the CaaS server (success or failure?)
	 * @throws Exception
	 */
	public String uploadConfigurationModel(String modelName, String modelContent, String caasAddress) throws Exception {

		RestTemplate rt = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		String xmlString = modelToXML(modelName, modelContent);

		HttpEntity<String> entity = new HttpEntity<String>(xmlString, headers);

		ResponseEntity<String> response = null;

		System.out.println(entity);

		try {
			response = rt.postForEntity(caasAddress, entity, String.class);
		} catch (HttpClientErrorException e) {
			modelErrorHandling(e);
		}

		String result = response.getBody();
		System.out.println(result);
		return result;

	}

	/**
	 * Sends a request with configuration parameters to CaaS address
	 * @param configurationRequest XML
	 * @param caasAddress
	 * @return a working configuration if possible
	 * @throws Exception
	 */
	public String getConfiguration(String configurationRequest, String caasAddress) throws Exception {

		RestTemplate rt = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		HttpEntity<String> entity = new HttpEntity<String>(configurationRequest, headers);

		ResponseEntity<String> response = null;

		System.out.println(entity);

		try {
			response = rt.postForEntity(caasAddress, entity, String.class);
		} catch (HttpServerErrorException e) {
			selectionErrorHandling(e);
		}

		String result = response.getBody();
		System.out.println(result);

		return result;
	}

	private String modelToXML(String modelName, String modelContent) throws XMLStreamException {
		StringWriter stringWriter = new StringWriter();
		XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
		XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);

		xMLStreamWriter.writeStartDocument();
		xMLStreamWriter.writeStartElement("xml");
		xMLStreamWriter.writeStartElement("model");
		xMLStreamWriter.writeAttribute("name", modelName);
		xMLStreamWriter.writeCharacters(modelContent);
		xMLStreamWriter.writeEndElement();
		xMLStreamWriter.writeEndDocument();

		xMLStreamWriter.flush();
		xMLStreamWriter.close();
		return stringWriter.getBuffer().toString();
	}

	private void modelErrorHandling(HttpClientErrorException e) throws Exception {
		if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
				&& e.getResponseBodyAsString().contains("There are no configurations that satisfy the given model.")) {
			throw new IntrospectionException(e.getResponseBodyAsString());
		}
		if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
				&& e.getResponseBodyAsString().contains("Parsing of model failed.")) {
			throw new DataFormatException(e.getResponseBodyAsString());
		}
		if (e.getStatusCode() != HttpStatus.CREATED) {
			throw new Exception(e.getResponseBodyAsString());
		}
	}

	private void selectionErrorHandling(HttpServerErrorException e) throws Exception {
		if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
			throw new Exception(e.getResponseBodyAsString());
		}
	}

}
