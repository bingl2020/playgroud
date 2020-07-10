package oa2018;
/**
 * Q2：movie network假设有个Movie类，. from: 1point3acres.com/bbs  public class Movie {. 1point3acres.com/bbs    int movieId;.    float rating;    List<Movie> similarMoviesoint3acres缃� }.more info on 1point3acres.com-google 1point3acres 要求找和movie相似的电影中排名前k个的电影（不包括当前movie）。就是找movie的所有neighbor中排名前k的电影。地里有很多关于这道题的讨论，就不多说了
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;



public class MovieNetwork {
	//BFS all movie and put them in a pq of size k
	public List<Movie> find(Movie m, int k){
		List<Movie> res = new ArrayList<>();
		if(m==null || k<1) return res;
		//BFS
		Queue<Movie> q = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		
		PriorityQueue<Movie> minHeap = new PriorityQueue<Movie>(k, new Comparator<Movie>(){
			@Override
			public int compare(Movie m1, Movie m2){
				if (m1.getRating() > m2.getRating()) return 1;
				else if(m1.getRating() < m2.getRating()) return -1;
				else return 0;
			}
			
		});
		
		q.offer(m);
		visited.add(m.getId());
		while(!q.isEmpty()){
			Movie tmp = q.poll();
			for(Movie movie: tmp.getSimilarMovies()){
				if(visited.add(movie.getId())){
					if(minHeap.size()<k){
						minHeap.offer(movie);
					}else{
						if(movie.getRating()>minHeap.peek().getRating()){
							minHeap.poll();
							minHeap.offer(movie);
						}
					}
				}
			}
		}
		//minHeap to arraylist
		while(!minHeap.isEmpty()){
			res.add(minHeap.poll());
			
		}
		
		Collections.reverse(res);
		return res;
		
		
	} 
}
