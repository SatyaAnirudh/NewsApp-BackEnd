package com.example.News.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.News.dao.NewsDAO;
import com.example.News.entity.News;

@Service
@Transactional
public class NewsServiceImpl implements NewsService{
	
	private NewsDAO newsDAO;

	@Override
	public void addNews(News news) {
		// TODO Auto-generated method stub
		newsDAO.save(news);
	}

	@Override
	public List<News> getAllNews() {
		// TODO Auto-generated method stub
		
		List<News> list=newsDAO.findAll();
		
		return list;
	}

	@Override
	public Optional<News> getNews(String newsId) {
		// TODO Auto-generated method stub
		
		Optional<News> news=newsDAO.findById(newsId);
		
		return news;
	}

	@Override
	public void deleteNews(String newsId) {
		// TODO Auto-generated method stub
		
		newsDAO.deleteById(newsId);
	}

	
	
	
}
