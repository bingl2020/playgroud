package oa2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//TODO change to use int[2]
public class MovieShot {
	public List<Integer> minCut(String input){
		List<Integer> res = new ArrayList<>();
		if(input==null || input.length()<1) return res;
		
		HashMap<Character, Interval> map = new HashMap<>();
		char[] arr = input.toCharArray();
		System.out.println(arr);
		for(int i = 0; i< arr.length; i++){
			Interval interval = map.get(arr[i]);
			if(interval!=null){//already has start
				interval.end = i;
			}else{//no start yet
				map.put(arr[i], new Interval(i));
			}
		}
		
		System.out.println(map.entrySet());
		//cast it to be list
		List<Interval> list = new ArrayList<>(map.values());
		Collections.sort(list, new Comparator<Interval>() {
			@Override
			public int compare(Interval i1, Interval i2){
				if(i1.start==i2.start){
					return i1.end-i2.end;
				}else{
					return i1.start-i2.start;
				}
			}
		});
		System.out.println(list);
		List<Interval> tmp = new ArrayList<>();
		//merge intervals
		for(Interval inv : list){
			if(tmp.size()==0||inv.start>tmp.get(tmp.size()-1).end){
				tmp.add(inv);
			}else if(inv.start<=tmp.get(tmp.size()-1).end){
				Interval last = tmp.get(tmp.size()-1);
				last.start = Math.min(last.start, inv.start);
				last.end = Math.max(inv.end, last.end);
			}
			
		}
		
		for(Interval inv: tmp){
			res.add(inv.end-inv.start+1);
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		MovieShot mv = new MovieShot();
		System.out.println(mv.minCut("abacdfeffhijkik"));
		
	}
}

class Interval{
	int start;
	int end;
	public Interval(int start){
		this.start = start;
		this.end = start;
	}
	
	public Interval(int start, int end){
		this.start=start;
		this.end=end;
	}
	
	public String toString(){
		return this.start+":"+this.end;
	}
	

}
