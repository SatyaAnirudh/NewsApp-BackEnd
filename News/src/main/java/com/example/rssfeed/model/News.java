package com.example.rssfeed.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import javax.persistence.Entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

@Data
@Entity
@Table(name="NEWS")
public class News {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NEWS_ID")
	private Long newsId;
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name ="FK_NEWS_SITE_ID")
	private NewsSite fkNewsSiteId;
	
	@Column(name="NEWS_TITLE")
	private String newsTitle;
	
	@Column(name="NEWS_LINK")
	private String newsLink;
	
	@Column(name="NEWS_DESCRIPTION")
	private String newsDescription;
	
	@Column(name="PUBLISH_DATE")
	private LocalDateTime publishDate;
	
	

}
