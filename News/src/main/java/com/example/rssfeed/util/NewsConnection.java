package com.example.rssfeed.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class NewsConnection {
	
	
	public static String getRSSXml(String urlPath){
		
		StringBuilder stringBuilder=new StringBuilder();
		InputStream inputStream=null;
		
		try {
			URL url=new URL(urlPath);
			inputStream=url.openStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String line="";
			
			while((line=bufferedReader.readLine())!=null) {
				stringBuilder.append(line).append("\n");
			}
			bufferedReader.close();
			inputStreamReader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return stringBuilder.toString();
		
	}

}
