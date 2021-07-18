package com.example.News.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.News.entity.News;
import com.example.News.service.NewsService;
import com.example.News.util.NewsConnection;
import com.example.News.util.XMLHelper;


@RestController
public class RSSController {
	
    //private final String URL = "https://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms";
	 
	 @Autowired
	 private XMLHelper xmlHelper;
	 
	 @Autowired
	 private NewsService newsService;
	    
	 
	 @PostMapping(value="postRssUrl")
	 public ResponseEntity<String> postFeed(@RequestBody String urlPath) {
	   
		 try {
			 String xmlRss=NewsConnection.getRSSXml(urlPath);
			 
			 List<News> newsList= xmlHelper.parse(xmlRss);
			 
			 Boolean bool=newsService.addNews(newsList);
			
			 String res=null;
			 if(bool)
				 res="Sucessfully Saved News to DB";
			 
			 return new ResponseEntity<String>(res, HttpStatus.OK);
			
		 }
		 catch(Exception e) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		 }
		 	 
		 
		 
	 }
	 
	 @GetMapping(value="getNews/{newsId}")
	 public ResponseEntity<News> getNews(@PathVariable String newsId){
		
		 try {
			 News news=newsService.getNews(newsId);
			 
			 return new ResponseEntity<News>(news, HttpStatus.OK);
			 
		 }
		 catch(Exception e) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		 }
		 
	 }
	 
	 @GetMapping(value="getAllNews")
	 public ResponseEntity<List<News>> getAllNews(){
		 
		 List<News> newsList=newsService.getAllNews();
		 
		 return new ResponseEntity<List<News>>(newsList, HttpStatus.OK);
		 
	 }
	 
	 
	 
	 

}
