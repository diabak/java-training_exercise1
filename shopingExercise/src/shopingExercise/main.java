package shopingExercise;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Order o = new Order();
		Product[] p = new Product[o.maxOrder];
		boolean stop_program = false;
		Scanner scan = new Scanner(System.in);
		String productName = "";
		int productPiece = 0;
		double productPrice = 0;
		int productNumber = 0;
		while (!stop_program) {
			System.out.println("What do you want to do?" 
					+ "\n 1. Add new product"
					+ "\n 2. Delete existing product" 
					+ "\n 3. Edit existing product" 
					+ "\n 4. Check the product list" 
					+ "\n 5. Exit");
			switch (scan.next()) {
			case "1":
				if (productNumber < o.maxOrder) {
					System.out.println("Provide name of product:");
					productName = scan.next();
					System.out.println("Provide piece of product:");
					productPiece = scan.nextInt();
					System.out.println("Provide price of product:");
					productPrice = scan.nextDouble();
					p[productNumber] = new Product(productName,productPiece,productPrice);
					productNumber++;
				}
				else
					System.out.println("Not able to add more products!");
				break;
			case "4":
				System.out.println(o.toString(p));
				break;
			case "5":
				stop_program = true;
				break;
			default:
				System.out.println("You select wrong option!");
				break;
			}
			if (!stop_program) {
				System.out.println("Do you want to continue?\n Yes or No");
				String s_question = scan.next();
				if (s_question.toUpperCase().equals("YES")) 
					stop_program = false;
				else if (s_question.toUpperCase().equals("NO")) 
					stop_program = true;
				else
					System.out.println("Wrong answer! We decided for you! You can play again with us :D");
			}
		}
		scan.close();
	    
//		p[0] = new Product("Cukier", 2, 3.55);
//		p[1] = new Product("Maka",1,2.0);
//		o.addProduct(p[1]);
	}
}
