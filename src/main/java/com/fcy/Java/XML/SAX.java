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
        saxParser.parse("F:\\sheet1.xml",sax);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (!qName.equals("row")||!qName.equals("c")){
            return;
        }
        System.out.println("qName:"+qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!qName.equals("row")||!qName.equals("c")){
            return;
        }
        System.out.println("end:"+uri+localName+qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("content:"+new String(ch,start,length));
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        //        no op
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        //        no op
    }

    @Override
    public void startDocument() throws SAXException {
        //        no op
    }

    @Override
    public void endDocument() throws SAXException {
        //        no op
    }
}
