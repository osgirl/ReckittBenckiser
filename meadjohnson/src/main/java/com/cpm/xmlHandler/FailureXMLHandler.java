package com.cpm.xmlHandler;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cpm.xmlGetterSetter.FailureGetterSetter;

public class FailureXMLHandler extends DefaultHandler{
	
	private String elementValue;
	private FailureGetterSetter failureGetterSetter = null;
	
	
	public FailureGetterSetter getFailureGetterSetter()
	{
		return failureGetterSetter;
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		
		failureGetterSetter = new FailureGetterSetter();
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		
		elementValue = new String(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		
		if(qName.equals("STATUS"))
		{
			failureGetterSetter.setStatus(elementValue);
		}
		else if(qName.equals("ERRORMSG"))
		{
			failureGetterSetter.setErrorMsg(elementValue);
		}
	}
}
