import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    
    Deck d = new Deck();
    while(d.deckSize < 25){
      int gold = d.hand();
      System.out.println(gold + " gold");
      
      int ind = -1;
      while (ind == -1) {
        System.out.println("Please select a purchase (c,s,g,e,d,p):");
        ind = getInd(s.next(), gold);
      }
      d = new Deck(d, ind);
    }
    System.out.println("Victory Points: " + d.vp());
    
    
  }
  
  public static int getInd(String s, int gold){
    if(s.equalsIgnoreCase("c") && gold >= 0){
      return 0;
    }
    if(s.equalsIgnoreCase("s") && gold >= 3){
      return 1;
    }
    if(s.equalsIgnoreCase("g") && gold >= 6){
      return 2;
    }
    if(s.equalsIgnoreCase("e") && gold >= 2){
      return 3;
    }
    if(s.equalsIgnoreCase("d") && gold >= 5){
      return 4;
    }
    if(s.equalsIgnoreCase("p") && gold >= 8){
      return 5;
    }
    return -1;
    
  }
}