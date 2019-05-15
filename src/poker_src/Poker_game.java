package poker_src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;

public class Poker_game extends JFrame{
	
	
	Poker_Hand[] cardHand = new Poker_Hand[6];
	Poker_Hand[] cardSelected = new Poker_Hand[6];
	JButton[] button = new JButton[6];
	JButton checkButton = new JButton();
	JButton betButton = new JButton();
	JPanel panel;
	JLabel earningPanel;
	JLabel textPanel;
	JComboBox betValue;
	
	
	private static boolean selectable = false;
	private static int cardS[] = new int[6];
	private static int totalEarnings = 1000;
	private static int bet=1;
	private static String[] betChoise = {"1","5","10","25","100","250","500","1000","5000","10000"};
	
	public Poker_game(){
		super("Poker with Bear");
		this.setLayout(null);
		totalEarnings = Poker_save.load();
		refresh();
		setPanel();
		setEarningPanel();
		setTextPanel();
		setBetValueComboBox();
		setButtonArray();
		setBetButton();
		setCheckButton();
		repaint();
	}
	
	private void setPanel(){
		panel = new JPanel();
		panel.setBounds(0, 0, 630, 500);
		panel.setBackground(new Color(0,153,0));
		panel.setLayout(null);
		this.setContentPane(panel);
		
	}
	
	private void setEarningPanel(){
		earningPanel = new JLabel("Total: " + String.valueOf(totalEarnings));
		earningPanel.setBounds(400, -75, 200, 200);
		earningPanel.setFont(new Font("Courier", Font.BOLD,26));
		panel.add(earningPanel);
		
	}
	
	private void setTextPanel(){
		textPanel = new JLabel("Poker With Bear");
		textPanel.setBounds(200, -40, 500, 200);
		textPanel.setFont(new Font("Courier", Font.BOLD,26));
		panel.add(textPanel);
		
	}
	
	private void setBetValueComboBox(){
		betValue = new JComboBox(betChoise);
		betValue.setBounds(400, 310, 70, 30);
		betValue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				bet = Integer.parseInt((String) betValue.getSelectedItem());
				if(bet>totalEarnings)
					{bet = 1;
					betValue.setSelectedItem("1");
					}
			}
			
		});
		panel.add(betValue);
		
	}
	
	private void setButtonArray(){

		int x=10,y=100;
		for(int i=1;i<=5;i++){
			button[i] = new JButton();
			button[i].setBounds(x, y, 111, 166);
			x+=121;
			button[i].setIcon(new ImageIcon(new ImageIcon("./png/back.png").getImage().getScaledInstance(111, 166, java.awt.Image.SCALE_SMOOTH)));
			//
			final int ii = i;
			button[i].addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e) {
					if(selectable && cardS[ii]==1){
						button[ii].setBounds(button[ii].getBounds().x, button[ii].getBounds().y-20, button[ii].getBounds().width, button[ii].getBounds().height);
						cardS[ii]=0;
					}
					else if(selectable && cardS[ii]==0){
						button[ii].setBounds(button[ii].getBounds().x, button[ii].getBounds().y+20, button[ii].getBounds().width, button[ii].getBounds().height);
						cardS[ii]=1;
					}
					
				}});
			
			panel.add(button[i]);
		}
	}
	
	private void setCheckButton(){
		checkButton.setText("Check");
		checkButton.setEnabled(false);
		checkButton.setBounds(252, 390, 111, 60);
		checkButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int winValue;
				int number;
				int sign;
				for(int i=1;i<=5;i++){
					if(cardS[i]==1){
						do{
							number = new Random().nextInt(13)+1;
							sign = new Random().nextInt(4)+1;
							cardHand[i] = new Poker_Hand(number,sign,new Poker_Cards().getImageFor(number, sign));
						}while(!checkValid(i));
						button[i].setIcon(new ImageIcon(cardHand[i].getImageIcon().getImage().getScaledInstance(111, 166, java.awt.Image.SCALE_SMOOTH)));
					}else{
						button[i].setBounds(button[i].getBounds().x, button[i].getBounds().y+20, button[i].getBounds().width, button[i].getBounds().height);
					}
				}
				winValue = new Poker_win(cardHand).checkWin();
				totalEarnings += bet*winValue;
				earningPanel.setText("Total: " + String.valueOf(totalEarnings));
				textPanel.setText("You Won: " + String.valueOf(bet*winValue));
				selectable = false;
				checkButton.setEnabled(false);
				betButton.setEnabled(true);
				betValue.setEnabled(true);
				
				if(totalEarnings==0){
					Poker_save.save(100);
					System.exit(0);
				}
			}});
		panel.add(checkButton);
	}

	private void setBetButton(){
		betButton.setText("Bet");
		betButton.setBounds(252, 310, 111, 60);
		betButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				refresh();
				if(bet>totalEarnings){
					betValue.setSelectedItem("1");
					bet=1;
					}
				textPanel.setText("");
				int number;
				int sign;
				for(int i=1;i<=5;i++){
					do{
						number = new Random().nextInt(13)+1;
						sign = new Random().nextInt(4)+1;
						cardHand[i] = new Poker_Hand(number,sign,new Poker_Cards().getImageFor(number, sign));
					}while(!checkValid(i));
					for(int i1=1;i1<=5;i1++) 
						cardSelected[i1] = cardHand[i1];
					
					button[i].setIcon(new ImageIcon(cardHand[i].getImageIcon().getImage().getScaledInstance(111, 166, java.awt.Image.SCALE_SMOOTH)));
				
			}
				totalEarnings -= bet;
				earningPanel.setText("Total: " + String.valueOf(totalEarnings));
				selectable = true;
				betButton.setEnabled(false);
				betValue.setEnabled(false);
				checkButton.setEnabled(true);
			}});
		
		panel.add(betButton);
		
	}
	
	private boolean checkValid(int k){
		for(int i=1;i<k;i++)
			if(cardHand[k].getNumber() == cardHand[i].getNumber() && cardHand[k].getSign() == cardHand[i].getSign())
				return false;
		for(int i=1;i<=5;i++)
			if(cardSelected[i]!=null)
				if(cardHand[k].getNumber() == cardSelected[i].getNumber() && cardHand[k].getSign() == cardSelected[i].getSign())
					return false;
		
		return true;
		
	}
	
	private void refresh(){
		for(int i=1;i<=5;i++){
			cardS[i]=1;
			cardHand[i]=null;
			cardSelected[i]=null;
		}
	
	}
	public static int getTotalEarnings(){
		return totalEarnings;
	}
	
}
