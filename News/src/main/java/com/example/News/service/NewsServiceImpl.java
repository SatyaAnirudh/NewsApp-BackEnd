package com.example.News.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.News.dao.NewsDAO;
import com.example.News.entity.News;
import com.example.News.exception.NewsNotFoundException;

@Service
@Transactional
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsDAO newsDAO;

	@Override
	public boolean addNews(List<News> newsList) {
		
		for(News news:newsList) {
			if(newsDAO.findById(news.getId()).isPresent()) {
				continue;
			}
			
			newsDAO.save(news);
		}
		
		return true;
		
	}

	@Override
	public List<News> getAllNews() {
		
		return newsDAO.findAll();
	}

	@Override
	public News getNews(String newsId) throws Exception {
		
		News news=null;
		
		if(newsDAO.findById(newsId).isPresent()) {
			news=newsDAO.findById(newsId).get();
		}
		else
			throw new NewsNotFoundException("Invalid newsId");
		
		return news;
	}

	@Override
	public void deleteNews(String newsId) {
		
		newsDAO.deleteById(newsId);
	}

	
	
	
}
