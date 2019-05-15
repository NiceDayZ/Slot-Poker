package poker_src;

import java.util.Arrays;

public class Poker_win {
	
	private int numbers[] = new int[6];
	private int signs[] = new int[6];
	
	public Poker_win(Poker_Hand[] hand){
		for(int i=1;i<=5;i++){
			numbers[i]=hand[i].getNumber();
			signs[i]=hand[i].getSign();
		}
		Arrays.sort(numbers);
	}
	
	public int checkWin(){
		if(royalFlush()) return 10000;
		if(straightFlush()) return 5000;
		if(fourOfAKind()) return 200;
		if(fullHouse()) return 40;
		if(flush()) return 45;
		if(straight()) return 45;
		if(threeOfAKind()) return 10;
		if(twoPairs()) return 5;
		if(pair()) return 2;
		return 0;
		
	}
	private boolean royalFlush(){
		if(numbers[1]!=1)
			return false;
		if(numbers[2]!=9)
			return false;
		if(numbers[3]!=11)
			return false;
		if(numbers[4]!=12)
			return false;
		if(numbers[5]!=13)
			return false;
			
		for(int i=1;i<=4;i++)
			if(signs[i]!=signs[i+1])
				return false;
		return true;
		
	}
	private boolean straightFlush(){
		for(int i=2;i<=5;i++)
			if(numbers[i]!=numbers[i-1]+1)
				return false;
		for(int i=1;i<=4;i++)
			if(signs[i]!=signs[i+1])
				return false;
		return true;
	}
	private boolean fourOfAKind(){
		if((numbers[1]!=numbers[2] || numbers[2]!=numbers[3] || numbers[3]!= numbers[4]) && (numbers[2]!=numbers[3] || numbers[3]!=numbers[4] || numbers[4]!= numbers[5]))
			return false;
		return true;
	}
	private boolean fullHouse(){
		if((numbers[1]==numbers[2] && numbers[2]==numbers[3] && numbers[4]==numbers[5]) || (numbers[3]==numbers[4] && numbers[4]==numbers[5] && numbers[1]==numbers[2]))
			return true;
		return false;
	}
	private boolean flush(){
		if(signs[1]==signs[2] && signs[2]==signs[3] && signs[3]==signs[4] && signs[4]==signs[5])
			return true;
		return false;
	}
	private boolean straight(){
		for(int i=2;i<=5;i++)
			if(numbers[i]!=numbers[i-1]+1)
				return false;
		return true;
	}
	private boolean threeOfAKind(){
		if(numbers[1]==numbers[2] && numbers[2]==numbers[3])
			return true;
		else if(numbers[2]==numbers[3] && numbers[3]==numbers[4])
			return true;
		else if(numbers[3]==numbers[4] && numbers[4]==numbers[5])
			return true;
		else
			return false;
	}
	private boolean twoPairs(){
		if(numbers[1]==numbers[2] && numbers[3]==numbers[4])
			return true;
		else if(numbers[1]==numbers[2] && numbers[4]==numbers[5])
			return true;
		else if(numbers[2]==numbers[3] && numbers[4]==numbers[5])
			return true;
		else
			return false;
		
	}
	private boolean pair(){
		if(numbers[1]==numbers[2] && (numbers[1]>10 || numbers[1]==1))
			return true;
		if(numbers[2]==numbers[3] && (numbers[2]>10 || numbers[2]==1))
			return true;
		if(numbers[3]==numbers[4] && (numbers[3]>10 || numbers[3]==1))
			return true;
		if(numbers[4]==numbers[5] && (numbers[4]>10 || numbers[4]==1))
			return true;
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
