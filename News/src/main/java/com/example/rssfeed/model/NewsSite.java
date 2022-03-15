package com.example.rssfeed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "NEWS_SITE")
public class NewsSite {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name ="SITE_ID")
	private Long siteId;
	
	@Column(name ="SITE_TITLE")
	private String siteTitle;
	
	@Column(name ="SITE_ADDRESS")
	private String siteAddress;
	
	@Column(name ="RSS_LINK")
	private String rssLink;
	

}
