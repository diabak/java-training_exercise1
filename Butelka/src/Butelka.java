
public class Butelka {
	
	private double pojemnosc;
	private double wypelnienie;
	
	public Butelka(double pojemnosc) {
		this.pojemnosc = pojemnosc;
		this.wypelnienie = 0;
	}
	
	public Butelka(double pojemnosc,double wypelnienie) {
		this.pojemnosc = pojemnosc;
		this.wypelnienie = wypelnienie;
	}
	
	double getWypelnienie() {
		return this.wypelnienie;
	}
	
	void wlej(double ileLitrow) throws Exception{
		if (pojemnosc >= (wypelnienie + ileLitrow)) 
			wypelnienie += ileLitrow;
		else
			throw new Exception("Pojemnosc butelki jest za mala. Woda sie wyleje.");
	}
	
	void wylej(double ileLitrow) throws Exception {
		if (wypelnienie > ileLitrow) 
			wypelnienie -= ileLitrow;
		else
			throw new Exception("W butelce jest za malo wody.");
	}
	
	void przelej(Butelka butelka, double ileLitrow) throws Exception {
		this.wylej(ileLitrow);
		butelka.wlej(ileLitrow);
	}

	public static void main(String[] args) {
		Butelka butelka  = new Butelka(0.5, 0.1);
		Butelka butelka2 = new Butelka(0.5);
		try {
			butelka.wlej(0.400);
			butelka2.wlej(0.400);
			butelka.wylej(0.1);
			System.out.println("butelka: " + butelka.getWypelnienie());
			System.out.println("butelka2: " + butelka2.getWypelnienie());
			butelka.przelej(butelka2, 0.3);
			System.out.println("butelka: " + butelka.getWypelnienie());
			System.out.println("butelka2: " + butelka2.getWypelnienie());
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			System.out.println("butelka: " + butelka.getWypelnienie());
			System.out.println("butelka2: " + butelka2.getWypelnienie());
		}
	}

}
