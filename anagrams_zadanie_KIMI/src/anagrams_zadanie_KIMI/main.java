package anagrams_zadanie_KIMI;

import java.io.*;
import java.util.*;

public class main {

  public static void main(String[] args) throws FileNotFoundException {
    String home = System.getProperty("user.home");
    String allWords = home + "\\\\Desktop\\\\FREE TIME\\\\Programming\\\\allwords.txt";
    Anagrams an = new Anagrams(allWords);
    for(List<String> wlist : an.getSortedByAnQty()) {
      System.out.println(wlist);
    }
    System.out.println("************************");
    Scanner scan = new Scanner(new File(home, "\\\\Desktop\\\\FREE TIME\\\\Programming\\\\wordsToFind.txt"));
    while(scan.hasNext()) {
      System.out.println(an.getAnagramsFor(scan.next()));
    }
    scan.close();
  }

}
