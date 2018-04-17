package shopingExercise;

public class Order {
	int maxOrder;
	static int lastOrderNumber = 0;
	int orderNumber;
	Product[] p;
	
	public Order() {
		this.maxOrder = 10;
		p = new Product[this.maxOrder];
	}
	
	public Order(Product[] p, int orderNumber, int maxOrder) {
		this.maxOrder = maxOrder;
		p = new Product[this.maxOrder];
	}

	public void addProduct(Product p) {
		this.orderNumber = lastOrderNumber;
		this.p[orderNumber] = p;
		lastOrderNumber++;
	}
	
	public String toString (Product[] p) {
		String text = "";
		for (Product prod: p) {
			if (prod instanceof Product)
				text = text + prod.toString() + "\n";
		}
		text += "\nResult:\t" + this.totalPrice(p) + "\tz³.";
		return text;
	}
	
	public double totalPrice (Product[] p) {
		double total = 0.00;
		for (Product prod: p) {
			if (prod instanceof Product)
				total += prod.totalPrice();
		}
		return total;
	}
}
