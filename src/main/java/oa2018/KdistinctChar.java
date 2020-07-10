package oa2018;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class KdistinctChar {

	public Set<String> kdistinctChar(String input, int K){
		Set<String> res = new HashSet<>();
		if(input==null||input.length()<1||K<=0) return res;
		char[] arr = input.toCharArray();
		HashSet<Character> set = new HashSet<>();
		int l = 0;
		int r = 0;
		while(r<arr.length){
			if(set.contains(arr[r])||r-l+1>K){
				//already in set
				set.remove(arr[l++]);
			}else{
				set.add(arr[r++]);
			}
			//System.out.println("l:"+l);
			//System.out.println("r:"+r);
			//within window always unique characters
			if(r-l+1==K){
				String cand = input.substring(l, r+1);
				if (!res.contains(cand)){
					res.add(cand);
				}
				
			}
			
		}
		
		return res;
		
	}
	public static void main(String[] args) {
		KdistinctChar s = new KdistinctChar();
		System.out.println(s.kdistinctChar("awaglknagawunagwkwagl", 4));
	}

}
