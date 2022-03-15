package com.example.rssfeed.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rssfeed.model.News;
import com.example.rssfeed.model.NewsSite;

public interface NewsDAO  {
	
	public Long addNews(News news);
	
	public List<News> getAllNews(Long newsSite);
	
	public List<NewsSite> getAllNewsSites();
	
	//public News findByNewsTitle(String newsTitle);
	
	public News getNewsByNewsTitle(String newsTitle);
	
	public News getNewsByNewsId(String newsId);
	
	public void deleteNews(String newsId);
	

}
