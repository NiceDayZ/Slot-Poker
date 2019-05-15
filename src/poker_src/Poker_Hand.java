package poker_src;

import javax.swing.ImageIcon;


public class Poker_Hand{
	
	private int mNumber;
	private int mSign;
	private ImageIcon mImage;
	
	public Poker_Hand(int number, int sign, ImageIcon image){
		mNumber = number;
		mSign = sign;
		mImage = image;
	}
	
	public int getNumber(){
		return mNumber;
	}
	
	public int getSign(){
		return mSign;
	}
	
	public ImageIcon getImageIcon(){
		return mImage;
	}
	
	public void setNumber(int number){
		mNumber = number;
	}
	
	public void setSign(int sign){
		mSign = sign;
	}
	
	public void setImage(ImageIcon image){
		mImage = image;
	}
	
}





