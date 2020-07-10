package oa2018;
/*
 * Q1： 丢


，输入一个字符串，其中包括整数，Z，X，或者+。整数代表此轮得分，X：当前成绩是double前面一个分数，+：当前成绩是前两个的和，Z：移除前一个成绩，求最后的总成绩和  一颗栗子： 输入["5", "-2", "4", "Z","X", 9, "+", "+"] output: 27 5 : sum = 5 -2 : sum = 5 - 2 = 3. 1point3acres.com/bbs 4 : sum = 3 + 4 = 7.1point3acres缃� Z : sum = 7 - 4 = 3.鏈枃鍘熷垱鑷�1point3acres璁哄潧 X : sum = 3 + -2 * 2 = -1 (4被移除了，前一个成绩是-2) 9 : sum = -1 + 9 = 8 + : sum = 8 + 9 - 4 = 13 (前两个成绩是9和-4) + : sum = 13 + 9 + 5 = 27 (前两个成绩是5 和 9) 
 * 
 */
import java.util.Deque;
import java.util.LinkedList;
/**
 * 
 * @author Lucy
 *
 */
public class Baseball {

	//Assumption: if no valid input return 0
	public int baseballScore(String[] symbols){
		if(symbols==null || symbols.length<1) return 0;
		//accumulative sum of scores 
		int res = 0;
		//Store score for each throw
		Deque<Integer> stack = new LinkedList<>();
		int currScore = 0;
		for(String s: symbols){
			if(s.equals("Z")){
				Integer preScore = stack.pollFirst();
				
				if(preScore!=null) currScore= 0 - preScore;
				else{
					System.out.println("invalid input");
					return 0;
					
				}
			}else if(s.equals("X")){
				Integer preScore = stack.peekFirst();
				currScore = preScore*2;
				stack.offerFirst(currScore);
				
			}else if(s.equals("+")){
				Integer preScore1 = stack.pollFirst();
				Integer preScore2=  stack.pollFirst();
				currScore = preScore1+preScore2;
				stack.offerFirst(preScore2);
				stack.offerFirst(preScore1);
				stack.offerFirst(currScore);
			}else{//Assume only integer left
				currScore = Integer.parseInt(s);
				stack.offerFirst(currScore);
				
			}
			res+=currScore;
			
		}
		return res;		
	}
	
	private static void debug(String s){
		System.out.println(s);
	}
	
	public static void main(String[] args) {
		Baseball bs = new Baseball();
		int res1 = bs.baseballScore(new String[]{"5", "-2", "4", "Z","X", "9", "+", "+"});
		
		int res2 = bs.baseballScore(new String[]{"5", "-2", "4", "Z","Z", "Z", "Z", "+"});
		debug(res2+"");
		
	}
}
