package com.hospital.utilities;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.google.gson.Gson;

public class Utilities
{

	@SuppressWarnings("unchecked")
	public <T> T xmlToObject(String xmlString, Class<?> clazz) throws Exception
	{
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		StreamSource streamSource = new StreamSource(new StringReader(xmlString));
		JAXBElement<T> jxb = (JAXBElement<T>) unmarshaller.unmarshal(streamSource, clazz);
		return jxb.getValue();
	}

	public String objectToXml(Object response)
	{
		StringWriter stringWriter = new StringWriter();
		Result result = new StreamResult(stringWriter);

		String xmlString = "";
		try
		{
			JAXBContext contextObj = JAXBContext.newInstance(response.getClass());
			Marshaller marshallerObj = contextObj.createMarshaller();
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshallerObj.marshal(response, result);
			xmlString = readFromStringWriter(stringWriter.getBuffer().toString());
		}
		catch (Exception e)
		{
		}

		return xmlString;
	}

	private String readFromStringWriter(String inputString)
	{

		StringReader stringReader = new StringReader(inputString);
		StringBuilder builder = new StringBuilder();

		int charsRead = -1;
		char[] chars = new char[100];
		do
		{
			try
			{
				charsRead = stringReader.read(chars, 0, chars.length);
			}
			catch (IOException e)
			{
			}

			// if we have valid chars, append them to end of string.
			if (charsRead > 0)
				builder.append(chars, 0, charsRead);
		}
		while (charsRead > 0);

		return builder.toString();
	}

	public java.sql.Date getCurrentDate()
	{
		return new java.sql.Date(new Date().getTime());
	}

	public Object jsonToObject(String request, Class<?> clazz)
	{
		Gson gson = new Gson();
		return gson.fromJson(request, clazz);
	}

	public String objectToJson(Object request)
	{
		Gson gson = new Gson();

		return gson.toJson(request);
	}

	
	
	
}
