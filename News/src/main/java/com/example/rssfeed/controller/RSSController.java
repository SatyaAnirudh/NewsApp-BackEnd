package com.example.rssfeed.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.rssfeed.model.News;
import com.example.rssfeed.model.NewsSite;
import com.example.rssfeed.service.NewsService;
import com.example.rssfeed.util.NewsConnection;
import com.example.rssfeed.util.XMLHelper;


@RestController
public class RSSController {
	
    //private final String URL = "https://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms";
	 
	
	 
	 @Autowired
	 private NewsService newsService;
	 
	    @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedOrigins("*");
	            }
	        };
	    }
	    
	 
	 @PostMapping(value="postRssUrl")
	 public ResponseEntity<List<Long>> postFeed(@RequestBody NewsSite newsSite) {
	   
		 try {
			 
			 List<Long> idList=newsService.addNews(newsSite);
			 
			 return new ResponseEntity<List<Long>>(idList, HttpStatus.OK);

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
		 
		 try {
			 List<News> newsList=newsService.getAllNews();
			 
			 return new ResponseEntity<List<News>>(newsList, HttpStatus.OK);
		 }
		 catch(Exception e) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		 }
		 
	 }
	 
	 @PostMapping(value="getAllNews")
	 public ResponseEntity<List<News>> getAllNewsForNewsSite(@RequestBody NewsSite newsSite){
		 
		 try {
			 List<News> newsList=newsService.getAllNewsForNewsSite(newsSite);
			 
			 return new ResponseEntity<List<News>>(newsList, HttpStatus.OK);
		 }
		 catch(Exception e) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		 }
		 
	 }
	 
	 @GetMapping(value="getAllNewsSites")
	 public ResponseEntity<List<NewsSite>> getAllNewsSites(){
		 
		 try {
			 List<NewsSite> newsSitesList=newsService.getAllNewsSites();
			 
			 return new ResponseEntity<List<NewsSite>>(newsSitesList, HttpStatus.OK);
		 }
		 catch(Exception e) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		 }
		 
	 }
	 
	 
	 
	 

}
