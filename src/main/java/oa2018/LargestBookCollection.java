package oa2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UnionFind{
	int k;
	int[] fa = new int[k];
	int[] rank = new int[k];
	public UnionFind(int k){
		this.k = k;
		for (int i=0; i<k; i++){
			fa[i] = i;
		}
	}
	
	public int find(int x){
		if(this.fa[x]!=x){
			this.fa[x] = find(this.fa[x]);
		}
		return this.fa[x];
	}
	
	public void union(int x, int y){
		int xf = find(x);
		int yf = find(y);
		if(xf==yf) {
			return;
		}else if(this.rank[x]<this.rank[y]){
			this.fa[x] = y;
		}else if(this.rank[x]>this.rank[y]){
			this.fa[y] = x;
		}else{
			this.fa[y] = x;
			this.rank[y]++;
		}
		
	}
	
	public int[] getFather(){
		return this.fa;
	}
}

public class LargestBookCollection {
	public List<String> findBookColl(List<List<String>> books){
		//map string to int
		int idx =0;
		HashMap<String, Integer> map = new HashMap<>();
		
		for (List<String> pair: books){
			for (String book: pair){
				if(!map.containsKey(book)){
					map.put(book, idx++);
				}
				
			}
		}
		
		
		
		int cnt = idx;
		String[] revertedMap = new String[cnt];
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			revertedMap[entry.getValue()] = entry.getKey();
		}
		
		
		UnionFind uf = new UnionFind(idx);
		for (List<String> pair: books){
			uf.union(map.get(pair.get(0)), map.get(pair.get(1)));
		}
		
		
		int maxFather = -1;
		int maxChildren = 0;
		int[] res = uf.getFather();
		
		//father: list of children 
		HashMap<Integer, List<Integer>> childrenMap = new HashMap<>();
		for (int i=0; i<cnt; i++){
			List<Integer> child = childrenMap.get(res[i]);
			if(child!=null){
				child.add(i);
			}else{
				child = new ArrayList<>();
				child.add(i);
				childrenMap.put(res[i], child);
			}
			
			if (child.size()>maxChildren){
				maxChildren = child.size();
				maxFather = res[i];
			}
			
		}
		
		
		List<String> res1 = new ArrayList<>();
		
		for (Integer child: childrenMap.get(maxFather)){
			res1.add(revertedMap[child]);
		}
		
		return res1;
		
		
		
		
	} 
}
