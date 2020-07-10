package oa2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Label {
	public List<Integer> label(List<String> availableTagList, List<String> targetList){
		//Assume no dup in targetlist
		if(availableTagList==null || targetList==null) return null;
		List<Integer> res = new ArrayList<>();
		res.add(0);
		
		if(availableTagList.size()<1 || targetList.size()<1) return res;
		HashMap<String, Integer> freq = new HashMap<>();
		getFreqMap(freq, targetList); //TODO simple initialization
		System.out.println(freq);
		int match = 0;
		int l = 0;
		int r = 0;
		int minLen = Integer.MAX_VALUE;
		int startIdx = -1;
		while(r<availableTagList.size()){
			String tmpr = availableTagList.get(r);
			Integer c1 = freq.get(tmpr);
			if(c1!=null){
				freq.put(tmpr, c1-1);
				if(c1-1==0) match++;
				
			}
			r++;		
			while(match==targetList.size()){
				//try to find left end
				String tmpl = availableTagList.get(l);
				Integer c2 = freq.get(tmpl);
				if(c2!=null){
					if(r-l<minLen){
						minLen = r-l;
						//one possible solution
						startIdx = l;
					}
					freq.put(tmpl, c2+1);
					if(c2+1>0) match--;
				}
				l++;
			}
			
			
		}
		
		if(minLen!=Integer.MAX_VALUE){
			List<Integer> resValid = new ArrayList<>();
			resValid.add(startIdx);
			resValid.add(startIdx+minLen-1);
			return resValid;
		}else return res;	
		
	}
	
	private void getFreqMap(HashMap<String, Integer> map, List<String> list){
		for(String s: list){
			Integer count = map.get(s);
			if(count==null){
				map.put(s,  1);
			}else{
				map.put(s, count+1);
			}
			
		}
		
	}
	public static void main(String[] args) {
		Label s = new Label();
		List<String> tagList =  Arrays.asList("d","b","d","a");
		System.out.println(tagList);
		List<String> targetList = Arrays.asList("");
		System.out.println(targetList);
		System.out.println(s.label(tagList, targetList));

	}

}
