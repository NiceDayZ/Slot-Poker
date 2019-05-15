package poker_src;
import java.io.*;

public class Poker_save {

	public static void save(int x){
		try(
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("date.ser"))
				){
			oos.writeObject(x);
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		System.exit(0);
	}
	
	public static int load(){
		int x = 1000;
		try(
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("date.ser"))
				){
			x = (Integer) ois.readObject();
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		return x;
	}
}
