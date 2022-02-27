package com.example.rssfeed.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import javax.persistence.Entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

@Data
@Entity
@Table(name="NEWS")
public class News {
	
	@Id
	@Column(name="NEWS_ID")
	private String id;
	
	@Column(name="SITE_TITLE")
	private String siteTitle;
	
	@Column(name="SITE_ADDRESS")
	private String siteAddress;
	
	@Column(name="NEWS_TITLE")
	private String newsTitle;
	
	@Column(name="NEWS_LINK")
	private String newsLink;
	
	@Column(name="NEWS_DESCRIPTION")
	private String newsDescription;
	
	@Column(name="PUBLISH_DATE")
	private Date publishDate;
	
	

}
