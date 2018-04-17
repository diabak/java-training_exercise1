package shopingExercise;

import java.text.DecimalFormat;

public class Product {
	String name;
	int piece;
	double price;

	public Product(String name, int piece, double price) {
		this.name = name;
		this.piece = piece;
		this.price = price;
	}
	
	double totalPrice () {
		return piece*price;
	}
	
	public String toString () {
		DecimalFormat df = new DecimalFormat(".##");
		String text = name + "\t\t" + piece + " szt.\t" + df.format(price) + " z³.\t" + df.format(totalPrice()) + " z³.";
		return text;
	}

}
