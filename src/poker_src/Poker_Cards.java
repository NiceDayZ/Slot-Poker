package poker_src;

import java.io.File;

import javax.swing.ImageIcon;


public class Poker_Cards {

		private ImageIcon[][] imageArray = new ImageIcon[14][5];
		private String[] numbers = {".","ace","2","3","4","5","6","7","8","9","10","jack","queen","king"};
		private String[] signs = {".","clubs","diamonds","hearts","spades"};
		
		public Poker_Cards(){

			for(int i=1;i<=13;i++)
				for(int j=1;j<=4;j++)
					imageArray[i][j] = new ImageIcon(("./png/"+numbers[i]+"_of_"+signs[j]+".png"));
			
		}
	
		public ImageIcon getImageFor(int number, int sign){
			return imageArray[number][sign];
		}
}
