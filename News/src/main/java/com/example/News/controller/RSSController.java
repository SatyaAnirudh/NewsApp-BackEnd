package com.example.News.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.News.entity.News;
import com.example.News.util.NewsConnection;
import com.example.News.util.XMLHelper;

@RestController
public class RSSController {
	
    private final String URL = "https://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms";
	 
	 @Autowired
	 private XMLHelper xmlHelper;
	    
	 
	 @GetMapping("/rss")
	 public List<News> getFeed() {
	   
		 String XMLRss=NewsConnection.getRSSXml(URL);

		 List<News> list=xmlHelper.parse(XMLRss);
		 
		 return list;
		 
	 }
	 
	 
	 
	 

}
