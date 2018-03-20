package customer_zadanie_KIMI;

public class Purchase {
	String id = null;
	String name = null;
	String product = null;
	double how_many = 0;
	double price = 0.0;
	double full_cost = 0.0;
	
	public void id (String custr_id) {
		this.id = custr_id;
	}

	public void name (String custr_name) {
		this.name = custr_name;
	}

	public void product (String custr_product) {
		this.product = custr_product;
	}

	public void how_many (double custr_how_many) {
		this.how_many = custr_how_many;
	}

	public void price (double custr_price) {
		this.price = custr_price;
	}

	public void full_cost() {
		this.full_cost = how_many*price;
	}
	
	public void full_name() {
		System.out.println(id + ";" + name + ";" + product + ";" + how_many + ";" + price);
	}
/*
	public String getName() {
	    return (String) name;
	}

	public double getPrice() {
	    return (double) price;
	}
*/
}
