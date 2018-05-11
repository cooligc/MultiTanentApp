package com.skc.multitanent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;

public class Test2 {
	static String[] getMovieTitles(String substr) {
        try{
            List<String> title = new ArrayList<String>();
        String data = makeAGetCall(substr,1);
        Gson gson = new Gson();
        Example example = gson.fromJson(data, Example.class);
        
        for(Datum data2 : example.getData()){
            title.add(data2.getTitle().toString());
        }
        
        
        for(int i=2;i<= example.getTotal_pages() ; ++i){
            data = makeAGetCall(substr,i);
            example = gson.fromJson(data, Example.class);
            
        
            for(Datum data3 : example.getData()){
                title.add(data3.getTitle().toString());
            }
        }
        
        Collections.sort(title);
        
        return title.toArray(new String[title.size()]);
            
        }catch(MalformedURLException e){
            
        }catch(IOException e){
            
        }
        return null;
    }
	
	private static String makeAGetCall(String movieTitle, int pageNumber) throws IOException {
        String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title="+movieTitle+"&page="+pageNumber;
        URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
        return response.toString();
    }

	public static void main(String[] args) {

		System.setProperty("http.proxyHost", "web-proxy.sgp.hp.com");
		System.setProperty("http.proxyPort", "8080");
		System.setProperty("https.proxyHost", "web-proxy.sgp.hp.com");
		System.setProperty("https.proxyPort", "8080");
		for(String str : getMovieTitles("spiderman")) {
			System.out.println(str);
		}
	
	}
	static class Datum
	{
	    
	   private  String Poster;
		    
		  private String Title;
		    
		  private String Type;
		    
		  private int Year;
		  private String imdbID;
		public String getPoster() {
			return Poster;
		}
		public void setPoster(String poster) {
			Poster = poster;
		}
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getType() {
			return Type;
		}
		public void setType(String type) {
			Type = type;
		}
		public int getYear() {
			return Year;
		}
		public void setYear(int year) {
			Year = year;
		}
		public String getImdbID() {
			return imdbID;
		}
		public void setImdbID(String imdbID) {
			this.imdbID = imdbID;
		}
		  
	}
	static class Example
	{
	   private String page;
		private int per_page;
		private int total;
		private int total_pages;
	    private List<Datum> data;
		public String getPage() {
			return page;
		}
		public void setPage(String page) {
			this.page = page;
		}
		public int getPer_page() {
			return per_page;
		}
		public void setPer_page(int per_page) {
			this.per_page = per_page;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public int getTotal_pages() {
			return total_pages;
		}
		public void setTotal_pages(int total_pages) {
			this.total_pages = total_pages;
		}
		public List<Datum> getData() {
			return data;
		}
		public void setData(List<Datum> data) {
			this.data = data;
		}
	}
}




