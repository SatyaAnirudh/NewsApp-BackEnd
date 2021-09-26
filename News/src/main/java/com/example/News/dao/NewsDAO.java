package com.example.News.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.News.entity.NewsEntity;
import com.example.News.model.News;

public interface NewsDAO {
	
	public String addNews(News news);
	
	public List<News> getAll();
	
	public News getNews(String newsId);
	
	public void deleteNews(String newsId);
	

}
