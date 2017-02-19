package com.wipro.crawler.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.UrlValidator;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("deprecation")
public class WebCrawlerAssignment {

	public static Set<String> URLSet = new HashSet<String>();
	public static String hostName=""; 
	public static JSONObject finalResult= new JSONObject(); 
	public static JSONArray resultSetArray  = new JSONArray(); 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String mainURL = "http://www.novelglow.com/";
		String mainURL = args[0];

		//Get the host to exclude the external sites
		hostName = getHost(mainURL);
		System.out.println("=======hostName==========="+hostName);
		try {
			System.out.println("===========started============");
			CrawlPage(mainURL);
			finalResult.put("FinalResult", resultSetArray);
			System.out.println("===========completed============"+finalResult.toString());
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

				//counter to number the entries
				int counter=0; 

				JSONObject curUrlImagesObj = new JSONObject();
				JSONArray curUrlImagesArray = new JSONArray();


				//Get the images in the current page
				Elements images = HTMLpage.select("img[src]");
				for(Element image: images){

					JSONObject tempObj = new JSONObject();

					//constructing object for each image path

					tempObj.put("image"+counter, image.attr("src"));
					//System.out.println(counter+". "+image.attr("src"));

					//add the object to array
					curUrlImagesArray.add(tempObj);
					counter++;

				}

				//put all the image paths of current page into the object with corresponding URL
				curUrlImagesObj.put(URL, curUrlImagesArray);

				//put this prepared object in the main object
				resultSetArray.add(curUrlImagesObj);



				//Get all links
				Elements externalSites = HTMLpage.select("a[href]");
				for(Element link: externalSites){
					if(!hostName.equals("INVALID")){
						if(link.attr("href").contains(hostName))
						{

							//Find for duplicates.
							// if duplicate is found then do nothing
							// else visit that page also
							if(URLSet.add(link.attr("href"))){
								//System.out.println(link.attr("href"));
								CrawlPage(link.attr("href"));

							}

						}
					}else{
						System.out.println("=============Host couldn't be retrieved===========");
					}


				}


			}else{
				System.out.println("URL is invalid=======");
			}
		}
		catch(UnsupportedMimeTypeException e){
			//uh.printStackTrace();
			System.out.println("============UnsupportedMimeTypeException====="+URL);
		}
		catch(HttpStatusException e){
			//uh.printStackTrace();
			System.out.println("============UnsupportedMimeTypeException====="+URL);
		}catch(UnknownHostException uh){
			System.out.println("============URL is invalid====="+URL);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static boolean validateURL(String URL){


		UrlValidator urlValidator = new UrlValidator();

		return urlValidator.isValid(URL);
	}
	public static String getHost(String URL){

		try {
			URL givenURL = new URL(URL);
			String domain=givenURL.getHost();

			//if domain starts with www then substring the host to contain only DNS
			domain = domain.startsWith("www.") ? domain.substring(4) : domain;
			return domain;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("=============URL is not valid==============");
			return "INVALID";
		}

	}

}
