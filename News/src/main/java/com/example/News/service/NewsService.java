package com.example.News.service;

import java.util.List;
import java.util.Optional;

import com.example.News.entity.News;

public interface NewsService {
	
	public boolean addNews(List<News> newsList);
	
	public List<News> getAllNews();
	
	public News getNews(String newsId) throws Exception;
	
	public void deleteNews(String newsId);

}
