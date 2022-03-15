package com.example.rssfeed.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.rssfeed.model.News;
import com.example.rssfeed.model.NewsSite;

@Repository
public class NewsDAOImpl implements NewsDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Long addNews(News news) {
		
		entityManager.persist(news);
		
		return news.getNewsId();
	}

	@Override
	public List<News> getAllNews(Long newsSiteId) {
		
		String str="SELECT n FROM News n where fkNewsSiteId.siteId=:newsSiteId order by n.publishDate DESC";
		
		Query query=entityManager.createQuery(str);
		query.setParameter("newsSiteId", newsSiteId);
		
		List<News> newsList=query.getResultList();
		
		return newsList;
	}
	
	
	@Override
	public List<NewsSite> getAllNewsSites() {
		
		String str="FROM NewsSite n";
		
		Query query=entityManager.createQuery(str);
		
		List<NewsSite> newsSitesList=query.getResultList();
		
		return newsSitesList;
	}
	
	@Override
	public News getNewsByNewsTitle(String newsTitle) {
		
		
		News news=null;
		try {
			String str="FROM News n where newsTitle = :newsTitle";
			Query query=entityManager.createQuery(str);
			query.setParameter("newsTitle", newsTitle);
			
			news =(News)query.getSingleResult();
			
		}
		catch(Exception e) {
			
		}
		
		return news;
	}

	@Override
	public News getNewsByNewsId(String newsId) {
		
		News news=entityManager.find(News.class, newsId);
		
		return news;
	}

	@Override
	public void deleteNews(String newsId) {
		
		
	}

	
	
}
