package com.example.rssfeed.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rssfeed.dao.NewsDAO;
import com.example.rssfeed.exception.NewsNotFoundException;
import com.example.rssfeed.model.News;
import com.example.rssfeed.model.NewsSite;
import com.example.rssfeed.util.NewsConnection;
import com.example.rssfeed.util.XMLHelper;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO newsDAO;

	@Autowired
	private XMLHelper xmlHelper;

	@Override
	public List<Long> addNews(NewsSite newsSite) {

		List<Long> idList = new ArrayList<Long>();

		String xmlRss = NewsConnection.getRSSXml(newsSite.getRssLink());
		List<News> newsList = xmlHelper.parse(xmlRss);
		newsList.stream().forEach(news -> news.setNewsSite(newsSite));

		for (News news : newsList) {
			if (newsDAO.getNewsByNewsTitle(news.getNewsTitle()) == null) {
				Long id = newsDAO.addNews(news);
				idList.add(id);
			}
		}

		return idList;

	}
	
	@Override
	public List<News> getAllNewsForNewsSite(NewsSite newsSite) {
		
		addNews(newsSite);
		return newsDAO.getAllNewsForNewsSite(newsSite.getSiteId());
	}

	@Override
	public List<News> getAllNews() {

		List<NewsSite> newsSiteList = newsDAO.getAllNewsSites();
		List<News> newsList = new ArrayList<>();
		
		newsSiteList.stream().
				map(newsSite -> getAllNewsForNewsSite(newsSite)).
				forEach(newsList::addAll);
		Collections.sort(newsList);
	
		return newsList;

	}

	@Override
	public List<NewsSite> getAllNewsSites() {

		List<NewsSite> newsSites = newsDAO.getAllNewsSites();

		return newsSites;
	}

	@Override
	public News getNews(String newsId) throws Exception {

		News news = newsDAO.getNewsByNewsId(newsId);

		if (news == null)
			throw new NewsNotFoundException("Invalid newsId");

		return news;
	}

	@Override
	public void deleteNews(String newsId) {

		newsDAO.deleteNews(newsId);
	}



}
