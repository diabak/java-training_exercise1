package dictionary_zadanie_KIMI;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		String fname = System.getProperty("user.home") + "\\Desktop\\FREE TIME\\Programming\\dictionary.txt";
		Dictionary ddic = new Dictionary();
		ddic.konstruktor(fname);
		boolean stop_program = false;
		Scanner console_start = new Scanner(System.in);
		while (!stop_program) {
			System.out.println("What do you want to do?" 
					+ "\n 1. Lookup" 
					+ "\n 2. Add new word"
					+ "\n 3. Delete existing word" 
					+ "\n 4. Update" 
					+ "\n 5. Save"
					+ "\n 6. Exit");
			switch (console_start.next()) {
			case "1":
				System.out.println("Enter word which you looking for: ");
				ddic.lookup(console_start.next());
				break;
			case "2":
				System.out.println("Enter word which you want to add: ");
				String s_haslo = console_start.next();
				System.out.println("Enter definition of this word: ");
				String s_def = console_start.next();
				ddic.add(s_haslo, s_def);
				break;
			case "3":
				System.out.println("Enter word which you want to delete: ");
				String s_haslo_del = console_start.next();
				ddic.lookup(s_haslo_del);
				System.out.println("Which definition do you want to remove?");
				String s_def_del = console_start.next();
				ddic.delete(s_haslo_del, s_def_del);
				break;
			case "4":
				System.out.println("Enter word which you want to update: ");
				String s_haslo_up = console_start.next();
				ddic.lookup(s_haslo_up);
				System.out.println("Which definition do you want to update?");
				String s_def_up = console_start.next();
				System.out.println("Provide new definition of this word:");
				String s_def_new_up = console_start.next();
				ddic.update(s_haslo_up, s_def_up, s_def_new_up);
				break;
			case "5":
				ddic.save_list_to_file(fname);
				break;
			case "6":
				stop_program = true;
				break;
			default:
				System.out.println("You select wrong option!");
				break;
			}
			if (!stop_program) {
				System.out.println("Do you want to continue?\n Yes or No");
				String s_question = console_start.next();
				if (s_question.toUpperCase().equals("YES")) 
					stop_program = false;
				else if (s_question.toUpperCase().equals("NO")) 
					stop_program = true;
				else
					System.out.println("Wrong answer! We decided for you! You can play again with us :D");
			}
		}
		console_start.close();
	}

}
