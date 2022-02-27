package com.example.rssfeed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rssfeed.dao.NewsDAO;
import com.example.rssfeed.exception.NewsNotFoundException;
import com.example.rssfeed.model.News;

@Service
@Transactional
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsDAO newsDAO;

	@Override
	public List<String> addNews(List<News> newsList) {
		
		List<String> idList=new ArrayList();
		
		for(News news:newsList) {
			if(newsDAO.getNews(news.getId())==null) {
				String id=newsDAO.addNews(news);
				idList.add(id);
				
			}
		}
		
		return idList;
		
	}

	@Override
	public List<News> getAllNews() {
		
		return newsDAO.getAll();
	}

	@Override
	public News getNews(String newsId) throws Exception {
		
		News news=newsDAO.getNews(newsId);
		
		if(news==null)
			throw new NewsNotFoundException("Invalid newsId");
		
		return news;
	}

	@Override
	public void deleteNews(String newsId) {
		
		newsDAO.deleteNews(newsId);
	}

}
