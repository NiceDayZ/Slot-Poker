package poker_src;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Poker_main {

	public static void main(String[] args) {
		Poker_game Obj = new Poker_game();
		Obj.setSize(630,500);
		Obj.setVisible(true);
		Obj.setLocationRelativeTo(null);
		Obj.setResizable(true);
		Obj.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
               Poker_save.save(Poker_game.getTotalEarnings());
            }
        });

	}

}
