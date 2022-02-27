package com.example.rssfeed.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.rssfeed.model.News;

@Repository
public class NewsDAOImpl implements NewsDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public String addNews(News news) {
		
		entityManager.persist(news);
		
		return news.getId();
	}

	@Override
	public List<News> getAll() {
		
		String str="SELECT n FROM NewsEntity n";
		Query query=entityManager.createQuery(str);
		
		List<News> newsList=query.getResultList();
		
		return newsList;
	}

	@Override
	public News getNews(String newsId) {
		
		News news=entityManager.find(News.class, newsId);
		
		if(news==null)return null;
		
		return news;
	}

	@Override
	public void deleteNews(String newsId) {
		
		
	}

	
	
}
