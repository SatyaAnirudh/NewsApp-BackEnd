package com.example.News.util;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.example.News.model.News;

@Service
public class XMLHelper {
	
	public List<News> parse(String xmlData){
		
		List<News> parsedNews = new ArrayList<>();
		
		try {
			Document document = loadXmlFromString(xmlData);
			
			NodeList channelNodeList=document.getElementsByTagName("channel");
			for(int i=0;i<channelNodeList.getLength();i++) {
				Node channelNode =channelNodeList.item(i);
				Element channelElement = (Element)channelNode;
				
				String siteTitle=channelElement.getElementsByTagName("title").item(0).getTextContent();
				
				String siteLink=channelElement.getElementsByTagName("link").item(0).getTextContent();
				
				NodeList itemNodeList=channelElement.getElementsByTagName("item");
				for(int j=0;j<itemNodeList.getLength();j++) {
					Node itemNode=itemNodeList.item(j);
					Element itemElement=(Element)itemNode;
					
					News news=createNewsItem(itemElement);
					news.setSiteTitle(siteTitle);
					news.setSiteAddress(siteLink);
					parsedNews.add(news);
					
				}
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return parsedNews;
		
		
	}

	private News createNewsItem(Element itemElement) throws ParseException {
		// TODO Auto-generated method stub
		
		News news =new News();
		
		String title=itemElement.getElementsByTagName("title").item(0).getTextContent();
		String link=itemElement.getElementsByTagName("link").item(0).getTextContent();
		String description=itemElement.getElementsByTagName("description").item(0).getTextContent();
		String pubDate=itemElement.getElementsByTagName("pubDate").item(0).getTextContent();
		
		String extractedId=link.substring(link.lastIndexOf("/")+1,link.lastIndexOf("."));
		Date publishDate=convertToDate(pubDate);
		
		news.setId(extractedId);
		news.setNewsDescription(description);
		news.setNewsLink(link);
		news.setNewsTitle(title);
		news.setPublishDate(publishDate);
		
		
		return news;
	}

	private Date convertToDate(String pubDate) throws ParseException {
		// TODO Auto-generated method stub Wed, 07 Jul 2021 15:45:02 IST
		DateFormat dateFormat=new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		return dateFormat.parse(pubDate);
	}

	private Document loadXmlFromString(String xmlData) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		DocumentBuilder documentBuilder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
		Document document=documentBuilder.parse(new InputSource(new StringReader(xmlData)));
		document.getDocumentElement().normalize();
		
		return document;
	}
	
	

}
