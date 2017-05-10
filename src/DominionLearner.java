import java.util.Arrays;

public class DominionLearner extends Learner<Deck>{

  public DominionLearner(LearnableSystem<Deck> system) {
    super(system);
    // TODO Auto-generated constructor stub
  }
  
  public double test(Deck startingState, int it){
    double reward = 0;
    for(int i = 0;i < it;i++){
      Deck play = startingState;
      while (true) {
        Deck temp = playBest(play);
        if (temp == null) {
          break;
        } else {
          play = temp;
        }
      }
      reward += play.vp();
    }
    return reward / it;
  }

  public void testPrint(Deck startingState, int it) {
    for(int i = 0;i < it;i++){
      Deck play = startingState;
      while (true) {
        Deck temp = playBest(play);
        if (temp == null) {
          break;
        } else {
          play = temp;
          System.out.println(Arrays.toString(temp.deck));
        }
      }
      System.out.println("Victory Points: " + play.vp());
    }
  }
}
