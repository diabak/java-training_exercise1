package anagrams_zadanie_KIMI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Anagrams {
	public List<String> words = new ArrayList<String>();
	public List<String> helper_words = new ArrayList<String>();
	public List<String> alphabetic_words = new ArrayList<String>();
	public ArrayList<ArrayList<String>> anagrams_list = new ArrayList<ArrayList<String>>();

	public Anagrams(String allWords) {
		File file = new File(allWords);
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String file_line = sc.nextLine();
				for (String str : file_line.split(" ")) {
					this.words.add(str);
					this.helper_words.add(str);
					this.alphabetic_words.add(str.chars().sorted()
							.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
							.toString());
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Not found file in your computer");
			e.printStackTrace();
		}

		for (int i = 0; i < helper_words.size(); i++) {
			ArrayList<String> anagram_words = new ArrayList<String>();
			for (int j = 0; j < helper_words.size(); j++) {
				if (alphabetic_words.get(j).equals(alphabetic_words.get(i))
						&& !anagram_words.contains(helper_words.get(j)))
					anagram_words.add(helper_words.get(j));
			}
			if (anagram_words != null && !anagrams_list.contains(anagram_words)) {
				anagrams_list.add(anagram_words);
			}
		}
	}

	public ArrayList<ArrayList<String>> getSortedByAnQty() {

		Collections.sort(anagrams_list, new Comparator<ArrayList>() {
			public int compare(ArrayList a1, ArrayList a2) {
				return a2.size() - a1.size(); // assumes you want biggest to smallest
			}
		});

		return anagrams_list;
	}

	public String getAnagramsFor(String next_line) {
		for (ArrayList<String> strArr : anagrams_list) {
			if (strArr.contains(next_line)) {
				strArr.remove(next_line);
				return next_line + ": " + strArr;
			}
		}
		return null;
	}

}
