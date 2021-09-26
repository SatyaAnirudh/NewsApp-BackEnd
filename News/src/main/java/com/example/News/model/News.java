package com.example.News.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import javax.persistence.Entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

@Data
public class News {
	

	private String id;
	
	private String siteTitle;
	
	private String siteAddress;
	
	private String newsTitle;
	
	private String newsLink;
	
	private String newsDescription;
	
	private Date publishDate;
	
	

}
