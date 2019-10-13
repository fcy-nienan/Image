package com.fcy.Java.XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAX extends DefaultHandler {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory parser=SAXParserFactory.newInstance();
        SAXParser saxParser=parser.newSAXParser();
        SAX sax=new SAX();
        saxParser.parse("test.xml",sax);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.println("qName:"+qName);
        for(int i=0;i<attributes.getLength();i++){
            System.out.println("attributes:"+attributes.getQName(i));
            System.out.println("value:"+attributes.getValue(i));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        System.out.println("end:"+uri+localName+qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        System.out.println("content:"+new String(ch,start,length));
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        super.startPrefixMapping(prefix, uri);
        System.out.println("prefix:"+prefix);
        System.out.println("uri:"+uri);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        super.endPrefixMapping(prefix);
        System.out.println("prefix:"+prefix);
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
