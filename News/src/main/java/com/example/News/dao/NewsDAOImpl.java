package com.example.News.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.News.entity.NewsEntity;
import com.example.News.model.News;


@Repository
public class NewsDAOImpl implements NewsDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public String addNews(News news) {
		
		
		NewsEntity ne=new NewsEntity();
		ne.setId(news.getId());
		ne.setNewsDescription(news.getNewsDescription());
		ne.setNewsLink(news.getNewsLink());
		ne.setNewsTitle(news.getNewsTitle());
		ne.setPublishDate(news.getPublishDate());
		ne.setSiteAddress(news.getSiteAddress());
		ne.setSiteTitle(news.getSiteTitle());
		
		entityManager.persist(ne);
		
		return ne.getId();
	}

	@Override
	public List<News> getAll() {
		
		List<News> newsList= new ArrayList<>();
		
		String str="SELECT n FROM NewsEntity n";
		Query query=entityManager.createQuery(str);
		
		List<NewsEntity> newsEntityList=query.getResultList();
		
		for(NewsEntity ne:newsEntityList) {
			News news=new News();
			news.setId(ne.getId());
			news.setNewsDescription(ne.getNewsDescription());
			news.setNewsLink(ne.getNewsLink());
			news.setNewsTitle(ne.getNewsTitle());
			news.setPublishDate(ne.getPublishDate());
			news.setSiteAddress(ne.getSiteAddress());
			news.setSiteTitle(ne.getSiteTitle());
			
			newsList.add(news);
			
		}
		
		return newsList;
	}

	@Override
	public News getNews(String newsId) {
		
		NewsEntity ne=entityManager.find(NewsEntity.class, newsId);
		
		if(ne==null)return null;
		
		News news=new News();
		
		news.setId(ne.getId());
		news.setNewsDescription(ne.getNewsDescription());
		news.setNewsLink(ne.getNewsLink());
		news.setNewsTitle(ne.getNewsTitle());
		news.setPublishDate(ne.getPublishDate());
		news.setSiteAddress(ne.getSiteAddress());
		news.setSiteTitle(ne.getSiteTitle());
		
		return news;
	}

	@Override
	public void deleteNews(String newsId) {
		
		
	}

	
	
}
