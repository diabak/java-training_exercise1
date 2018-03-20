package customer_zadanie_KIMI;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CustomersPurchaseSortFind {
	public List<Purchase> pch = new ArrayList<Purchase>();

	public void readFile(String fname) {
		File file = new File(fname);
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String[] strArr = sc.nextLine().split(";");
				Purchase one_pch = new Purchase();
				one_pch.id(strArr[0]);
				one_pch.name(strArr[1]);
				one_pch.product(strArr[2]);
				one_pch.how_many(Double.parseDouble(strArr[3]));
				one_pch.price(Double.parseDouble(strArr[4]));
				pch.add(one_pch);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Not found file in your computer");
			e.printStackTrace();
		}
	}

	public void showSortedBy(String str) {
		switch (str) {
		case "Nazwiska":
			System.out.println("Nazwiska:");
			if (!pch.isEmpty()) {
				Collections.sort(pch, new Comparator<Purchase>() {
					@Override
					public int compare(Purchase c1, Purchase c2) {
						return c1.name.compareTo(c2.name);
					}
				});
			}
			break;
		case "Koszty":
			System.out.println("Koszty:");
			if (!pch.isEmpty()) {
				Collections.sort(pch, new Comparator<Purchase>() {
					@Override
					public int compare(Purchase c1, Purchase c2) {
						return (int) (c2.full_cost - c1.full_cost);
					}
				});
			}
			break;
		}

		for (int i = 0; i < pch.size(); i++) {
			pch.get(i).full_name();
			pch.get(i).full_cost();
		}
	}

	public void showPurchaseFor(String id) {
		System.out.println("Klient " + id + ":");
		if (!pch.isEmpty()) {
			for (int i=0; i<pch.size(); i++) {
				if (pch.get(i).id.equals(id)) {
					pch.get(i).full_name();
				}
			}
		}
	}
}
