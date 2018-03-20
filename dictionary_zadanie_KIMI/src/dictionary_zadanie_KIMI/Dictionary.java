package dictionary_zadanie_KIMI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
	public List<Word> diction = new ArrayList<Word>();

	public void konstruktor(String fname) {
		File file = new File(fname);
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String file_line = sc.nextLine();
				if (file_line.contains("=")) {
					String[] strArr = file_line.split(" = ");
					Word word = new Word();
					word.haslo(strArr[0]);
					word.definicja(strArr[1]);
					diction.add(word);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Not found file in your computer");
			e.printStackTrace();
		}
	}

	public void lookup(String str) {
		int n_def = 1;
		if (!diction.isEmpty()) {
			Collections.sort(diction, new Comparator<Word>() {
				@Override
				public int compare(Word c1, Word c2) {
					return c1.definicja.compareTo(c2.definicja);
				}
			});
			System.out.println("List of definition of: " + str);
			for (int i = 0; i < diction.size(); i++) {
				if (diction.get(i).haslo.equals(str)) {
					System.out.println(n_def + ". " + diction.get(i).definicja);
					n_def++;
				}
			}
			if (n_def == 1) {
				System.out.println("The word: " + str + " not exist in out dictionary.\n Do you want to add a definition for this word?");
			}
		}
	}

	public void add(String s_haslo, String s_def) {
		boolean b_exist = false;
		for (int i = 0; i < diction.size(); i++) {
			if (diction.get(i).haslo.equals(s_haslo) && diction.get(i).definicja.equals(s_def)) {
				System.out.println("This word exist in our dictionary");
				b_exist = true;
			}
		}
		if (!b_exist) {
			Word word = new Word();
			word.haslo(s_haslo);
			word.definicja(s_def);
			diction.add(word);
		}
	}

	public void delete(String s_haslo_del, String s_def_del) {
		for (int i = 0; i < diction.size(); i++) {
			if (diction.get(i).haslo.equals(s_haslo_del) && diction.get(i).definicja.equals(s_def_del)) {
				diction.remove(i);
				System.out.println("This word is remove from dictionary");
				break;
			}
		}
	}

	public void save_list_to_file(String fileName) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			Writer bw = new BufferedWriter(fw);
		    for (int i=0; i < diction.size(); i++) {
		        bw.write(diction.get(i).haslo + " = " + diction.get(i).definicja);
		        ((BufferedWriter) bw).newLine();
		    }
		    bw.close();
		} catch (IOException e) {
			System.out.println("Problem with a file - not exist or is not in this place");
			e.printStackTrace();
		}
	}

	public void update(String s_haslo_up, String s_def_up, String s_def_new_up) {
		for (int i = 0; i < diction.size(); i++) {
			if (diction.get(i).haslo.equals(s_haslo_up) && diction.get(i).definicja.equals(s_def_up)) {
				diction.get(i).definicja = s_def_new_up;
				System.out.println("This word is updated!");
				break;
			}
		}
	}
}
