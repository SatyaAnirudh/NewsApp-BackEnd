package com.example.rssfeed.service;

import java.util.List;
import java.util.Optional;

import com.example.rssfeed.model.News;
import com.example.rssfeed.model.NewsSite;

public interface NewsService {
	
	public List<Long>  addNews(List<News> newsList);
	
	public List<News> getAllNews(Long newsSite);
	
	public News getNews(String newsId) throws Exception;
	
	public void deleteNews(String newsId);
	
	public List<NewsSite> getAllNewsSites();

}
