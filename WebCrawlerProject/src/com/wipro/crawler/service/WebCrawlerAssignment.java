package com.wipro.crawler.service;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.validator.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("deprecation")
public class WebCrawlerAssignment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CrawlPage("http://www.mit.edu/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void CrawlPage(String URL) throws IOException{
		try{
		if(validateURL(URL)){
			
		
		//Get the page
			
		Document HTMLpage = Jsoup.connect(URL).get();
			
		int counter=0; 

		//Get all links
		Elements externalSites = HTMLpage.select("a[href]");
		for(Element link: externalSites){
			if(link.attr("href").contains("mit.edu"))
			{
				System.out.println(counter+". "+link.attr("href"));
				counter++;
			}

		}
		}else{
			System.out.println("URL is invalid=======");
		}
		}catch(UnknownHostException uh){
			uh.printStackTrace();
			System.out.println("============URL is invalid=====");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean validateURL(String URL){
		
		
		UrlValidator urlValidator = new UrlValidator();
		
		return urlValidator.isValid(URL);
	}

}
