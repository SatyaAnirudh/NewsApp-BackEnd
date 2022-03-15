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
import com.example.rssfeed.model.NewsSite;

@Service
@Transactional
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsDAO newsDAO;

	@Override
	public List<Long> addNews(List<News> newsList) {
		
		List<Long> idList=new ArrayList();
		
		for(News news:newsList) {
			if(newsDAO.getNewsByNewsTitle(news.getNewsTitle())==null) {
				Long id=newsDAO.addNews(news);
				idList.add(id);
			}
		}
		
		return idList;
		
	}

	@Override
	public List<News> getAllNews(Long newsSiteId) {
		
		return newsDAO.getAllNews(newsSiteId);
	}
	
	@Override
	public List<NewsSite> getAllNewsSites() {
		
		List<NewsSite> newsSites=newsDAO.getAllNewsSites();
		
		//newsSites.stream().forEach(site -> );
		
		return newsSites;
	}

	@Override
	public News getNews(String newsId) throws Exception {
		
		News news=newsDAO.getNewsByNewsId(newsId);
		
		if(news==null)
			throw new NewsNotFoundException("Invalid newsId");
		
		return news;
	}

	@Override
	public void deleteNews(String newsId) {
		
		newsDAO.deleteNews(newsId);
	}

}
