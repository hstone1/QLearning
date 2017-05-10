import java.util.Scanner;

public class Main2 {
  public static void main(String[] args) {
    DominionLearner best = null;
    double bestV = 0;
    
    Dominion d = new Dominion();
    
    for(int i = 0;i < 1;i++){
      System.out.print("|");
      DominionLearner learn = new DominionLearner(d);
      learn.playLearn(new Deck(), 30000, 0.01);
      double e = learn.test(new Deck(), 1000);
      if(e > bestV) {
        best = learn;
        bestV = e;
        System.out.println(e);
      }
    }
    
    System.out.println("Starting View:");
    Scanner s = new Scanner(System.in);
    while (true) {
      String cmd = s.nextLine().toLowerCase();
      if(cmd.startsWith("train")){
        int trials = 1;
        try{
          trials = Integer.parseInt(cmd.substring(6));
        } catch (Exception e) {};
        
        for(int i = 0;i < trials;i++){
          System.out.print("|");
          DominionLearner learn = new DominionLearner(d);
          learn.playLearn(new Deck(), 30000, 0.01);
          double e = learn.test(new Deck(), 1000);
          if(e > bestV) {
            best = learn;
            bestV = e;
            System.out.println(e);
          }
        }
        System.out.println("Done Training");
      } else if (cmd.startsWith("play")) {
        System.out.println("[c, s, g, e, d, p]");
        best.testPrint(new Deck(), 1);
      } else if (cmd.startsWith("save")) {
        NeuralNetworkIO.save(best.nn, "best.nn");
      } else if (cmd.startsWith("load")) {
        best.nn = NeuralNetworkIO.load("best.nn");
      } else if (cmd.startsWith("test")) {
        System.out.println("Average Score: " + best.test(new Deck(), 5000));
      }
    }
  }
}
