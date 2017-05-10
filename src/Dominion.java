
public class Dominion implements LearnableSystem<Deck>{

  @Override
  public Action<Deck>[] getActions(Deck s) {
    Action<Deck>[] arr = new Action[6];
    if (s.deckSize >= 25) {
      return arr;
    } else {
      int gold = s.hand();
      arr[0] = new Action<Deck>(s, new Deck(s, 0));
      if(gold >= 3) {
        arr[1] = new Action<Deck>(s, new Deck(s, 1));
      }
      if(gold >= 6) {
        arr[2] = new Action<Deck>(s, new Deck(s, 2));
      }
      if(gold >= 2) {
        arr[3] = new Action<Deck>(s, new Deck(s, 3));
      }
      if(gold >= 5) {
        arr[4] = new Action<Deck>(s, new Deck(s, 4));
      }
      if(gold >= 8) {
        arr[5] = new Action<Deck>(s, new Deck(s, 5));
      }
      return arr;
    }
  }

  @Override
  public double doAction(Action<Deck> a) {
    double r = a.getEnd().vp() - a.getStart().vp();
    return r;
  }

}
