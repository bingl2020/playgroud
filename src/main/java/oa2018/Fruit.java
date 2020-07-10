package oa2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fruit {
	//check if codeList follow shoppingCart
	//total # of codeList < shoppingCart
	//codeList may have anything
	//all element in codeList must be matched
	public static int checkWinner (List<List<String>> codeList, List<String> shoppingCart){
	    	int x = 0;
	    	int y = 0;
	    	int index = 0;
	    	while (x < codeList.size()){
	    		y = 0;
	    		while(y < codeList.get(x).size() && index < shoppingCart.size()){
	    			if (codeList.get(x).get(y) == shoppingCart.get(index) || codeList.get(x).get(y) == "anything"){
	    				y++;
	    				index++;
	    			}else{
	    				index++;
	    			}
	    		}
	    		if (index == shoppingCart.size() && (x != codeList.size()-1 || y != codeList.get(x).size()-1)){
	    			return 0;
	    		}
	    		x++;
	    	}
	    	return 1;
	    }
		
		
	
	
	public static void main(String[] args) {
		List<List<String>> codeList = new ArrayList<List<String>>();
//		codeList.add(Arrays.asList("apple", "apple"));
//		codeList.add(Arrays.asList("orange", "anything", "orange"));
//		List<String> shoppingCart = Arrays.asList("orange", "apple", "apple","orange", "mango", "orange");
		
		codeList.add(Arrays.asList("apple", "apple"));
		codeList.add(Arrays.asList("orange", "banana", "orange"));
		codeList.add(Arrays.asList("pear", "orange", "grape"));
		
		
		List<String> shoppingCart = Arrays.asList("orange", "apple", "apple","orange", "banana", "orange","pear", "grape");
		System.out.println(checkWinner(codeList,shoppingCart));
		
		
	}
	
	
}
