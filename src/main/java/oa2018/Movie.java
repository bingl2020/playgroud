package oa2018;

import java.util.List;

public class Movie {
	private int id;
	float rate; 
	List<Movie> similarMovies;
	
	   public List<Movie> getSimilarMovies(){
		   return this.similarMovies;
	   }
	   public Float getRating(){
		   return this.rate;
	   }
	   
	   public int getId(){
		   
		   return this.id;
	   }
}
