package com.example.News.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.News.entity.News;

public interface NewsDAO extends JpaRepository<News, String> {

}
