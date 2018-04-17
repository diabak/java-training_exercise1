package anagrams_2_zadanie_KIMI;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.stream.Stream;


public class main {

	public static void main(String[] args){
		try{
			InputStream is = new URL("http://www.puzzlers.org/pub/wordlists/unixdict.txt").openConnection().getInputStream();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    Stream<String> stream = reader.lines();
//	 	    stream.forEach(System.out::println);
			System.out.println(stream);
		}
		catch(Exception ex){
			System.out.println("wyjatek");
		}
	}

}
 