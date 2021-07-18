package com.example.News.service;

import java.util.List;
import java.util.Optional;

import com.example.News.entity.News;

public interface NewsService {
	
	public void addNews(News news);
	
	public List<News> getAllNews();
	
	public Optional<News> getNews(String newsId);
	
	public void deleteNews(String newsId);

}
