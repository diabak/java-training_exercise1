package customer_zadanie_KIMI;

public class main {
	  
    public static void main(String[] args) {
      CustomersPurchaseSortFind cpsf = new CustomersPurchaseSortFind();
      String fname = System.getProperty("user.home") + "\\Desktop\\FREE TIME\\Programming\\customers.txt";
      cpsf.readFile(fname);
      cpsf.showSortedBy("Nazwiska");
      cpsf.showSortedBy("Koszty");
      String[] custSearch = { "c00001", "c00002" };

      for (String id : custSearch) {
        cpsf.showPurchaseFor(id);
      }
    }

  }