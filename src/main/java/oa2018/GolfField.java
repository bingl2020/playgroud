package oa2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GolfField {
	//because we have to cut the tree in order
	//start from source(xs,ys) to target(xt, yt) bfs to find the shortest path(with obstacle 0)
	//source value < target value and target must be first larger element
	
	//Assumption: start from 0,0
	//Assumption: no dup tree height
	//Assumption: at least one tree]
	/*
	 * 
	 */
    public int cutOffTree(List<List<Integer>> forest) {
        //corner case
    	if(forest==null || forest.size()<1 || forest.get(0)==null|| forest.get(0).size()<1) return -1;
    	List<int[]> pool = new ArrayList<>();
    	for(int i = 0; i<forest.size(); i++){
    		for(int j = 0; j<forest.get(i).size();j++){
    			int height = forest.get(i).get(j);
    			if(height>1) pool.add(new int[]{i, j, height});
    		}
    	}
    	
    	Collections.sort(pool, new Comparator<int[]>(){
    		@Override
    		public int compare(int[] c1, int[] c2){
    			//Assumption: tree height diff won't cause overflow
    			return c1[2]-c2[2];	
    		}
    		
    	});
    	
    	int res = 0;
    	int[] source = new int[2];
    	for(int[] c: pool){
    		int dis = shortestDist(forest, source, c);
    		if(dis<0) return -1;
    		else{
    			res+=dis;
    			source[0] = c[0];
    			source[1] = c[1];
    		}
    		
    	}
    	
    	return res;  	
    }
    
    /*
     * Find the shortest distance from source to target with obstacles in the map
     * Approach : BFS
     * 
     * although put in all 3 property, dont care value
     * @source: 2 ele
     * @target: 3 ele but dont care value
     */
    private int shortestDist(List<List<Integer>> forest, int[] source, int[] target){
    	int m = forest.size();
    	int n = forest.get(0).size();
    	int[] dx = new int[]{-1, 0, 0, 1};
        int[] dy = new int[]{0, -1, 1, 0};
    	Queue<int[]> q = new LinkedList<>();
    	boolean[][] visited = new boolean[m][n];
    	int step = 0;
    	q.offer(source);
    	visited[source[0]][source[1]] = true;
    	while(!q.isEmpty()){
    		int size = q.size();
    		for(int i = 0; i<size; i++){//all possible candidates for the next step
    			int[] curr = q.poll();
    			if(curr[0]==target[0] && curr[1]==target[1]) return step;
    			
    			for(int j=0; j<4; j++){
    				int nr = curr[0]+dx[j];
    				int nc = curr[1]+dy[j];
    				if(nr>=0 && nc>=0 && nr<m && nc<n && !visited[nr][nc] && forest.get(nr).get(nc)!=0){
    						q.offer(new int[]{nr, nc});
    						visited[nr][nc] = true;
    					}
    				}
    				
    			}
    		step++;
    		}
    	
    	return -1;
    		
    }
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
